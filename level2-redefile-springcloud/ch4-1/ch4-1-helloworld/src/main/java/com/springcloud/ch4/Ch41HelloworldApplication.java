package com.springcloud.ch4;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class Ch41HelloworldApplication {

    public static void main(String[] args) {
        SpringApplication.run(Ch41HelloworldApplication.class, args);
    }

}
