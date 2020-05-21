package com.example.demomybatisplusquickstart;

import com.example.demomybatisplusquickstart.entity.User;
import com.example.demomybatisplusquickstart.mapper.UserMapper;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
class DemoMybatisPlusQuickStartApplicationTests {

    @Autowired
    private UserMapper userMapper;
    @Test
    void contextLoads() {
        System.out.println(("----- selectAll method test ------"));

        List<User> user = userMapper.selectList(null);
        Assert.assertNotNull(user);
        user.forEach(System.out::println);

    }

}
