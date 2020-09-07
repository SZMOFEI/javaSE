package com.mofei.config;

import com.mofei.Device;
import com.mofei.Dog;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DeviceConfig {

    @Bean
    @Conditional(Dog.class)
    public Device dog() {
        System.out.println("执行Device 初始化工作");
        return new Device();
    }
}
