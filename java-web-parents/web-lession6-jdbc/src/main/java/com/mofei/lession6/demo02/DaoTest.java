package com.mofei.lession6.demo02;

import com.mofei.lession6.demo02.dao.UserDao;
import com.mofei.lession6.demo02.domain.User;
import org.junit.Test;

/**
 * @author com.mofei
 * @version 2018/12/30 21:26
 */
public class DaoTest {
    @Test
    public void  test01(){
        UserDao userDao = new UserDao();
        User one = userDao.findOne(1);
        System.out.println("one = " + one);
    }
    @Test
    public void test02() {
        UserDao userDao = new UserDao();
        User user = userDao.find(1);
        System.out.println("user = " + user);
    }
}
