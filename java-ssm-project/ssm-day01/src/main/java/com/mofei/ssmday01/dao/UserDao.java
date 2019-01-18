package com.mofei.ssmday01.dao;

import com.mofei.ssmday01.entity.User;

import java.util.List;

/**
 * @author mofei
 * @date 2019/1/15 21:36
 */
public interface UserDao {
    int deleteByPrimaryKey(Integer userId);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    List<User> selectAllUser();
}
