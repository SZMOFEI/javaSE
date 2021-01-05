package com.mofei.lession6.demo01;

import java.sql.*;

/**
 * JDBC的入门案例
 * @author com.mofei
 * @version 2018/12/30 18:17
 */
public class Demo01 {
    public static void main(String[] args) {
        //1.注册驱动
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String password = "root";
            String username= "root";
            String url = "jdbc:mysql://localhost/web-day06";
            try {
                Connection conn = DriverManager.getConnection(url, username, password);
                PreparedStatement preparedStatement = conn.prepareStatement("select * from user where id = ?");
                preparedStatement.setInt(1,1);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()){
                    int id = resultSet.getInt("id");
                    String name = resultSet.getString("name");
                    int age = resultSet.getInt("age");
                    System.out.println("age = " + age);
                    System.out.println("id = " + id);
                    System.out.println("name = " + name);
                }
                if (resultSet!=null){
                    resultSet.close();
                }
                if (preparedStatement!=null){
                    preparedStatement.close();
                }
                if (conn!=null){
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        //2.获取连接
        //3.获得statement对象
        //4.执行SQL
        //5.获得结果集
        //6.遍历结果集
    }
}
