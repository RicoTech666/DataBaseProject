package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionTools {
    private static final String URL = "jdbc:mysql://localhost:3306/student_examination_sys?useSSL=false";
    private static final String NAME = "root";
    private static final String PASSWORD = "123456";


     Connection getConnect() {
        try {
            //加载驱动
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("数据库驱动不存在！");
        }

        Connection conn = null;
        try {
            //创建连接
            conn = DriverManager.getConnection(URL, NAME, PASSWORD);
        } catch (SQLException e) {
            System.out.println("数据库连接失败！");
        }

        return conn;
    }

     Statement getStatement(Connection connection) {
        Statement statement = null;
        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            System.out.println("创建Statement失败！");
        }

        return statement;
    }

     ResultSet executeSQL(Statement statement, String sqlQuery) {
        ResultSet rs = null;
        try {
            rs = statement.executeQuery(sqlQuery);
        } catch (SQLException e) {
            System.out.println("查询失败！");
        }

        return rs;
    }

     static void closeConnect(ResultSet rs, Statement st, Connection conn) {
        if (rs != null) {   // 关闭记录集
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (st != null) {   // 关闭声明
            try {
                st.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (conn != null) {  // 关闭连接对象
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


}
