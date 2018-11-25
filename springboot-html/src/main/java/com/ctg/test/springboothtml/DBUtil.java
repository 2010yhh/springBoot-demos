package com.ctg.test.springboothtml;

/**
 * @Description: TODO
 * @Author: yanhonghai
 * @Date: 2018/11/21 15:56
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {

    private static final String URL="jdbc:mysql://127.0.0.1:3306/test?characterEncoding=utf-8";
    private static final String USER="root";
    private static final String PASSWORD="root";

    private static Connection conn=null;

    static {
        try {
            //1.加载驱动程序
            Class.forName("com.mysql.jdbc.Driver");
            //2.获得数据库的连接
            conn=DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //将获得的数据库与java的链接返回（返回的类型为Connection）
    public static Connection getConnection(){
        return conn;
    }
    public static void closeConnection(){
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}