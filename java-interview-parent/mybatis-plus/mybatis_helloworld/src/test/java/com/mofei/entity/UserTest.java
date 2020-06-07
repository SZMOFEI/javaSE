package com.mofei.entity;

import com.mofei.mapper.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

/**
 * @author mofei
 * @date 2020/6/6 23:55
 */
public class UserTest {

    @Test
    public void testGetById() throws IOException {
        SqlSessionFactory sqlSesstionFactory = getSqlSesstionFactory();
        SqlSession sqlSession = sqlSesstionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
//        User userById = mapper.getUserById(1L);
//        User userById = mapper.getUserByNameAndId("JOM",1L);
//        Map<String, Object> map = new HashMap<>();
//        map.put("name","JOM");
//        map.put("id",1);

        UserTO to = new UserTO();
        to.setId(1L);
        to.setName("JOM ");
        User userById = mapper.getUserByTO(to);
        System.out.println("userById = " + userById);
    }

    @Test
    public void testAdd() throws IOException {
        SqlSessionFactory sqlSesstionFactory = getSqlSesstionFactory();
        SqlSession sqlSession = sqlSesstionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User user = new User();
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());
        user.setName("Murphy");
        mapper.addUser(user);
        System.out.println(user.getId());
        sqlSession.commit();
        sqlSession.close();
    }
    @Test
    public void testUpdate() throws IOException {
        SqlSessionFactory sqlSesstionFactory = getSqlSesstionFactory();
        SqlSession sqlSession = sqlSesstionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User userById = mapper.getUserById(3L);
        userById.setGender(1);
        userById.setEmail("mofei@163.com");
        mapper.updateUser(userById);
        sqlSession.commit();
        sqlSession.close();
    }    @Test
    public void testDelete() throws IOException {
        SqlSessionFactory sqlSesstionFactory = getSqlSesstionFactory();
        SqlSession sqlSession = sqlSesstionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        boolean b = mapper.deleteUser(3L);
        Assert.assertTrue(b);
        sqlSession.commit();
        sqlSession.close();
    }

    private SqlSessionFactory getSqlSesstionFactory() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        return sqlSessionFactory;
    }

    //todo 如果传入的是一个Collection List Array 如果处理
    //todo 如果传入的是一个Collection List Array 对象， 又是 如果处理
}