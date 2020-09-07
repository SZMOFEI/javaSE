package com.mofei.config;

import com.mofei.Dog;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
//@ComponentScan(basePackages = "com.mofei")
public class DogConfig {

    @Bean
    public Dog dog() {
        return new Dog();
    }
}
