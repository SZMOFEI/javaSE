package com.example.apollo.quickstart;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableApolloConfig
public class ApolloQuickstartApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApolloQuickstartApplication.class, args);
    }

}
