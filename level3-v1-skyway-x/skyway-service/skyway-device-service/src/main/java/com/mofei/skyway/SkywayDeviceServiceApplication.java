package com.mofei.skyway;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(value = {"com.mofei.skyway.device.mapper"})
public class SkywayDeviceServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SkywayDeviceServiceApplication.class, args);
    }

}
