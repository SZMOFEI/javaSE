package com.mofei.skyway.user;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(value = {"com.mofei.skyway.user.mapper"})
public class SkywayUserServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SkywayUserServiceApplication.class, args);
    }

}
