package org.example.annotation.config;

import org.example.annotation.config.filter.MyTypeFilter;
import org.example.annotation.entity.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

/**
 * @author mofei
 * @date 2020/9/15 23:31
 */
@Configuration
//excludeFilters 会排除一些注解或者指定的类 。 目前排除controller注解的bean

//@ComponentScan(basePackages = "org.example.annotation", excludeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Controller.class)})
@ComponentScan(basePackages = "org.example.annotation", excludeFilters = {@ComponentScan.Filter(type = FilterType.CUSTOM, classes = MyTypeFilter.class)})
public class UserConfig {

    @Bean
    public User user() {
        return new User();
    }
}
