package com.mofei;

import java.util.List;

/**
 * @author mofei
 * @date 2020/9/10 9:19
 */

public interface UserDao {
    User getByID(int id);

    int update(User user);

    int delete(int id);

    int save(User user);

    int selectCount();

    int batchSave(List<User> users);

    int batchUpdate(List<User> users);

    int batchDelete(List<User> users);

    int batchDeleteById(Object[] ints);

    List<User> selectList();

    List<User> pageList(int pageSize, int pageRow);
}
