package com.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class Ch2EurekaClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(Ch2EurekaClientApplication.class, args);
    }

}
