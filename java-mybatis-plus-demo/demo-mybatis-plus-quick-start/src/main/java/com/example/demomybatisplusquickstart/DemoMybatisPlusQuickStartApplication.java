package com.example.demomybatisplusquickstart;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.demomybatisplusquickstart.mapper")
public class DemoMybatisPlusQuickStartApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoMybatisPlusQuickStartApplication.class, args);
    }

}
