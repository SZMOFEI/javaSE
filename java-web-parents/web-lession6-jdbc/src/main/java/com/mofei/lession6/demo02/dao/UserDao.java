package com.mofei.lession6.demo02.dao;

import com.mofei.lession6.demo02.domain.User;
import com.mofei.lession6.util.JDBCUtil;

import java.sql.*;

/**
 * @author com.mofei
 * @date 2018/12/30 21:18
 */
public class UserDao {

    public User findOne(int id) {
        //1.注册驱动
        User user = null;
        String password = "root";
        String username = "root";
        String url = "jdbc:mysql://localhost/web-day06";
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, username, password);
            PreparedStatement preparedStatement = conn.prepareStatement("select * from user where id = ?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int userId = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                user = new User();
                user.setAge(age);
                user.setId(userId);
                user.setName(name);
                System.out.println("age = " + age);
                System.out.println("id = " + userId);
                System.out.println("name = " + name);
            }
            if (resultSet != null) {
                resultSet.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    public User find(int id) {
        Connection connection = JDBCUtil.getConnection();
        String sql = "select * from user where id = ?";
        User user = null;
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                user = new User();
                user.setName(resultSet.getString("name"));
                user.setId(resultSet.getInt("id"));
                user.setAge(resultSet.getInt("age"));
            }
            JDBCUtil.release(resultSet, ps, connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

}
