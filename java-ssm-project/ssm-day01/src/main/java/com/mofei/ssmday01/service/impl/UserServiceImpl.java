package com.mofei.ssmday01.service.impl;

import com.mofei.ssmday01.dao.UserDao;
import com.mofei.ssmday01.entity.User;
import com.mofei.ssmday01.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author mofei
 * @date 2019/1/15 23:05
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    public int insert(User record) {
        return userDao.insert(record);
    }

    public List<User> selectAllUser() {
        return userDao.selectAllUser();
    }
}
