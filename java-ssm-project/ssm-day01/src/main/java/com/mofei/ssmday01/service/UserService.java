package com.mofei.ssmday01.service;

import com.mofei.ssmday01.entity.User;

import java.util.List;

/**
 * @author mofei
 * @date 2019/1/15 21:57
 */
public interface UserService  {

    int insert(User record);

    List<User> selectAllUser();
}
