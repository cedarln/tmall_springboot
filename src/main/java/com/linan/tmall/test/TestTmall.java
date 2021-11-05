package com.linan.tmall.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class TestTmall {
    public static void main(String args[]) throws SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        //try(){}
        // try-with-resources 语句，称为 ARM 块(Automatic Resource Management) ，自动资源管理。
        // 也就是说，数据流Connection会在 try 执行完毕后 自动 被关闭
        try(Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/tmall_springboot" +
                    "?useUnicode=true&characterEncoding=utf8", "root", "admin");
            Statement s = c.createStatement();
        ){
            for (int i = 1; i <= 10; i++) {
                String sqlFormat = "insert into category values(null, '测试分类%d')";
                String sql = String.format(sqlFormat, i);
                s.execute(sql);
            }
            System.out.println("已经成功创建10条category测试数据！");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
