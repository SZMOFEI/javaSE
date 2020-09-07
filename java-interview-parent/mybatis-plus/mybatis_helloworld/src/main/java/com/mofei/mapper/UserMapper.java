package com.mofei.mapper;

import com.mofei.entity.User;
import com.mofei.entity.UserTO;

import java.util.Map;

/**
 * @author com.mofei
 * @date 2020/6/6 23:52
 */
public interface UserMapper {
    public User getUserById(Long id );

   public void addUser(User user);

   public void updateUser(User user);
   public boolean deleteUser(Long id);

    /**
     * 如何没有声明@param 声明名称， 那么就需要配置 useActualParamName	 =true 属性
     * @param name 名称
     * @param id id
     * @return user
     */
   public User getUserByNameAndId ( String name, long id );
   public User getUserByMap (Map<String ,Object> map);

    User getUserByTO(UserTO to);

    User getUserWithDetail(Long i);
}
