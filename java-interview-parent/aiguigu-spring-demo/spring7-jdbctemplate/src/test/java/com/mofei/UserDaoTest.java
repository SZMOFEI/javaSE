package com.mofei;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Arrays;
import java.util.List;

/**
 * @author mofei
 * @date 2020/9/10 9:26
 */
public class UserDaoTest {
    private ApplicationContext ctx = null;
    private UserDao userDao = null;

    {
        ctx = new ClassPathXmlApplicationContext("spring-jdbc.xml");
        userDao = ctx.getBean("userDaoImpl", UserDao.class);
    }

    @Test
    public void testPageList() {
        int pageRow= 2;
        int pageSize= 5;
        List<User> users = userDao.pageList(5, 2);
        Assert.assertNotNull(users);
        for (User user : users) {
            System.out.println("user = " + user);
        }
    }


    @Test
    public void testSelectList() {
        List<User> users = userDao.selectList();
        Assert.assertNotNull(users);
        for (User user : users) {
            System.out.println("user = " + user);
        }
    }

    @Test
    public void testBatchDeleteById() {
        Object[] objects = {11, 12, 13, 14, 15};
        int i = userDao.batchDeleteById(objects);
        Assert.assertEquals("批量删除ID失败",5,i);
    }

    @Test
    public void testSelectCount() {
        int i = userDao.selectCount();
        Assert.assertNotNull(i);
        System.out.println("i = " + i);
    }

    @Test
    public void testBatchDelete() {
        User user1 = new User(11);
        User user2 = new User(12);
        User user3 = new User(13);
        User user4 = new User(14);
        User user5 = new User(15);
        List<User> users = Arrays.asList(user1, user2, user3, user4, user5);
        int i = userDao.batchDelete(users);
        Assert.assertEquals("批量删除失败", 5, i);
    }

    @Test
    public void testBatchUpdate() {
        User user1 = new User(11, 35, "Muryphy21", "21 murphy@qq.com");
        User user2 = new User(12, 35, "Muryphy22", "22 murphy@qq.com");
        User user3 = new User(13, 35, "Muryphy23", "23 murphy@qq.com");
        User user4 = new User(14, 35, "Muryphy24", "24 murphy@qq.com");
        User user5 = new User(15, 35, "Muryphy25", "25 murphy@qq.com");
        List<User> users = Arrays.asList(user1, user2, user3, user4, user5);
        int i = userDao.batchUpdate(users);
        Assert.assertEquals("批量更新失败", 5, i);
    }

    @Test
    public void testBatchSave() {
        User user1 = new User(1, 23, "Muryphy11", "11 murphy@qq.com");
        User user2 = new User(2, 23, "Muryphy12", "12 murphy@qq.com");
/*        User user3 = new User(13, 23, "Muryphy13", "13 murphy@qq.com");
        User user4 = new User(14, 23, "Muryphy14", "14 murphy@qq.com");
        User user5 = new User(15, 23, "Muryphy15", "15 murphy@qq.com");*/
        List<User> users = Arrays.asList(user1, user2);
        int i = userDao.batchSave(users);
        Assert.assertEquals("批量保存失败", 5, i);
    }

    @Test
    public void testSave() {
        int save = userDao.save(new User(10, 23, "Muryphy009", "murphy@qq.com"));
        Assert.assertEquals("操作失败", 1, save);
    }

    @Test
    public void testDelete() {
        int id = 2;
        int delete = userDao.delete(id);
        Assert.assertEquals("删除用户：" + id + "失败", 2, delete);
    }

    @Test
    public void testUpdate() {
        int muryphy003 = userDao.update(new User(3, 23, "Muryphy", "murphy@qq.com"));
        Assert.assertEquals(1, muryphy003);
    }

    @Test
    public void testSelectById() {
        User byID = userDao.getByID(2);
        System.out.println("byID = " + byID);
        Assert.assertNotNull(byID);
        Assert.assertEquals("查询失败", 1, byID.getId());
    }
}
