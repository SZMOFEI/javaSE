package com.mofei.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author com.mofei
 * @version 2020/6/8 16:56
 */
public class SqlSessionFactoryUtils {

    //增加两个方法：获取SqlSessionFactory   , 同时openSession（） ，
    // 要求 ，多线程环境下如何实现单例模式 ，
    private static SqlSessionFactory sqlSessionFactory = null;
    private final static Class CLASS_LOCK = SqlSessionFactoryUtils.class;

    public static SqlSessionFactory getSqlSessionFactory() {
        String resource = "mybatis-config.xml";
        InputStream resourceAsStream = null;
        try {
            resourceAsStream = Resources.getResourceAsStream(resource);
        } catch (IOException e) {
            Logger.getLogger(SqlSessionFactoryUtils.class.getName()).log(Level.SEVERE,null,e);
        }
        synchronized (CLASS_LOCK) {
            if (sqlSessionFactory == null) {
                sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
            }
        }
        return sqlSessionFactory;
    }

    public static SqlSession openSqlSession() {
        if (sqlSessionFactory == null) {
            getSqlSessionFactory();
        }
        return sqlSessionFactory.openSession();
    }
}
