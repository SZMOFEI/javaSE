package com.example.demoredis;

import com.example.demoredis.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoRedisApplicationTests {

    @Test
    public void contextLoads() {
    }

    /*
        @Autowired
        private RedisTemplate<String, String> redisTemplate;*/
    @Autowired
    private RedisTemplate<String, Object> objectRedisTemplate;

    @Test
    public void testStringInset() {
        String s = "hello";
        objectRedisTemplate.opsForValue().set("key1", s);
    }

    @Test
    public void testStringDelete() {
        objectRedisTemplate.delete("key1");
    }

    @Test
    public void testStringDelete2() {
        objectRedisTemplate.delete("mofei");
    }

    @Test
    public void testInsertObject() {
        User user = new User("123", "mofei", 35);
        objectRedisTemplate.opsForValue().set("mofei", user, 1, TimeUnit.MINUTES);
    }

    @Test
    public void testInsertList() {
        User user = new User("123", "mofei", 35);
        User user2 = new User("456", "lixiao", 35);
        User user3 = new User("456", "lixiao2", 15);
        ArrayList<User> list = new ArrayList<>();
        list.add(user);
        list.add(user2);
        list.add(user3);
        list.parallelStream().forEach(x -> {
            objectRedisTemplate.opsForList().rightPush("mofei", x);
        });
    }

    @Test
    public void testInsertSortSet() {
        Set<User> list = new HashSet<>();
        User user = new User("123", "mofei", 35);
        User user2 = new User("456", "lixiao", 35);
        User user3 = new User("456", "lixiao2", 15);
//        ArrayList<User> list = new ArrayList<>();
        list.add(user);
        list.add(user2);
        list.add(user3);
        objectRedisTemplate.opsForZSet().add("mofei", list, 1);
    }

    @Test
    public void testGetObject() {
        Object mofei = objectRedisTemplate.opsForValue().get("mofei");
        System.out.println("mofei = " + mofei);
    }

    @Test
    public void testGetListObject() {
        List<User> mofei = (List<User>) objectRedisTemplate.opsForValue().get("mofei");
        mofei.forEach(System.out::println);
    }
}
