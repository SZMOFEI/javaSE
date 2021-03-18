package com.mofei.skyway.user.mapper;

import com.mofei.skyway.user.entity.SkywayUser;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

/**
 * @author mofei
 * @since 2021/1/4 14:46
 */

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class SkywayUserMapperTest {
    @Autowired
    private SkywayUserMapper skywayUserMapper;

    @Test
    public void testAddUser() {
        SkywayUser user = new SkywayUser(UUID.randomUUID().toString(), "MOFEI", "123456");
        skywayUserMapper.insert(user);
    }
}