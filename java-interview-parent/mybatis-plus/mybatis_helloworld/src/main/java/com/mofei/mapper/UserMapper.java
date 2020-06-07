package com.mofei.mapper;

import com.mofei.entity.User;
import com.mofei.entity.UserTO;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * @author mofei
 * @date 2020/6/6 23:52
 */
public interface UserMapper {
    public User getUserById(Long id );

   public void addUser(User user);

   public void updateUser(User user);
   public boolean deleteUser(Long id);

   public User getUserByNameAndId (@Param("name") String name, @Param("id") long id );
   public User getUserByMap (Map<String ,Object> map);

    User getUserByTO(UserTO to);
}
