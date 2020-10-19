package com.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class Ch2EurekaServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(Ch2EurekaServerApplication.class, args);
    }

}
