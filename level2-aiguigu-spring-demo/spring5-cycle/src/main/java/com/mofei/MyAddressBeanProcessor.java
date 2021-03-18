package com.mofei;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * @author mofei
 * @date 2021/3/17 22:28
 */
public class MyAddressBeanProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("正在执行 postProcessBeforeInitialization 方法");
        if (beanName.equals("address")) {
            return bean;
        }
        return null;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("正在执行 postProcessAfterInitialization 方法 bean " + bean);
        return bean;
    }
}
