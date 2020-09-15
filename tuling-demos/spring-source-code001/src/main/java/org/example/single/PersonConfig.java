package org.example.single;

import org.example.single.entity.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;

/**
 * @author mofei
 * @date 2020/9/15 23:50
 */
@ComponentScan(basePackages = "org.example.single")
public class PersonConfig {
    @Bean
    @Lazy
    @Scope(value = "prototype")
    public Person person() {
        return new Person();
    }
}
