package com.mofei.lession6.util;

import java.sql.*;

/**
 * @author mofei
 * @date 2018/12/30 22:07
 */
public class JDBCUtil {
    private static Connection connection = null;

    static String password = "root";
    static String username = "root";
    static String url = "jdbc:mysql://localhost/web-day06";
    static Connection conn = null;

    static {
        System.out.println("静态代码块执行,正在加载数据库驱动!");
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        return conn;
    }

    public static void release(ResultSet resultSet) {

        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void release(ResultSet resultSet, Statement statement) {
        release(resultSet);
        release(statement);
    }

    private static void release(Statement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private static void release(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void release(ResultSet resultSet, Statement statement, Connection conn) {
        System.out.println("关闭资源开始.....");
        release(resultSet, statement);
        release(conn);
        System.out.println("关闭资源结束.....");
    }
}
