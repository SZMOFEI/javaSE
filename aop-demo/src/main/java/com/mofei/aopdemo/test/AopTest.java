package com.mofei.aopdemo.test;

import com.mofei.aopdemo.config.AspectConfig;
import com.mofei.aopdemo.dao.IndexDao;
import com.mofei.aopdemo.dao.IndexDaoImpl;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author mofei
 * @date 2018/11/24 14:54
 */
public class AopTest {
    public static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AspectConfig.class);
        IndexDao bean = context.getBean(IndexDaoImpl.class);
        bean.query();
    }
}
