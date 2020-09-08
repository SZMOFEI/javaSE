package com.mofei;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

public class MyBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        //因为需要进行过滤
        if ("car".equals(beanName)) {
            System.out.println("正在执行 before postProcessBeforeInitialization  bean = " + bean);
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("正在执行 After postProcessAfterInitialization  bean = " + bean);
        return bean;
    }
}
