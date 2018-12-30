package com.mofei.aopdemo.dao;

import org.springframework.stereotype.Component;

/**
 * @author mofei
 * @date 2018/11/24 14:51
 */
@Component
public class IndexDaoImpl implements IndexDao {
    @Override
    public void query() {
        System.out.println("dao main ..... " );
    }
}
