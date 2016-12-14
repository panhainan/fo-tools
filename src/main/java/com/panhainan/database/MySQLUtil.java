package com.panhainan.database;

import com.panhainan.DBUtil;

import java.sql.*;
import java.util.ResourceBundle;

/**
 * Created by FirePan on 2016/12/14.
 */
public class MySQLUtil {

    private final static String driverName = "com.mysql.jdbc.Driver";

    /**
     * 获取数据库连接
     * @param dbUser 用户名
     * @param dbPass 密码
     * @param dbUrl url
     * @return Connection
     */
    private static Connection getConnection(String dbUser,String dbPass,String dbUrl) {
        try {
            System.out.println("**\tConnection params info ... ");
            System.out.println("**\tdriverClassName : " +driverName);
            System.out.println("**\tusername : " +dbUser);
            System.out.println("**\tpassword : " +dbPass);
            System.out.println("**\turl : " +dbUrl);
            System.out.println("**\tDatabase connect start ...");
            // DriverManager.registerDriver(new Driver());
            Class.forName(driverName).newInstance();
            Connection conn = null;
            conn = DriverManager.getConnection(dbUrl, dbUser, dbPass);
            if (conn != null) {
                System.out.println("**\tDatabase connect success : conn = " +conn);
                return conn;
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
            System.out.println("**\t**Error**:caused at "
                    + new Throwable().getStackTrace()[0].getMethodName() + "::"
                    + e.getMessage());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            System.out.println("**\t**Error**:caused at "
                    + new Throwable().getStackTrace()[0].getMethodName() + "::"
                    + e.getMessage());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("**\t**Error**:caused at "
                    + new Throwable().getStackTrace()[0].getMethodName() + "::"
                    + e.getMessage());
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("**\t**Error**:caused at "
                    + new Throwable().getStackTrace()[0].getMethodName() + "::"
                    + e.getMessage());
        }
        System.out.println("**\t**Error**:Database connect failed !");
        return null;
    }

    public static void closeConnection(Connection conn, PreparedStatement pstm,
                                       ResultSet rs) {
        try { // 捕捉异常
            try {
                if (rs != null) { // 当ResultSet对象的实例rs不为空时
                    rs.close(); // 关闭ResultSet对象
                    System.out.println("**\tDatabase rs closed .");
                }
            } finally {
                try {
                    if (pstm != null) { // 当Statement对象的实例stmt不为空时
                        pstm.close(); // 关闭Statement对象
                        System.out.println("**\tDatabase pstm closed .");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (conn != null) { // 当Connection对象的实例conn不为空时
                        conn.close(); // 关闭Connection对象
                        System.out.println("**\tDatabase connect closed .");
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("**\t**Error**:caused at "
                    + new Throwable().getStackTrace()[0].getMethodName() + "::"
                    + e.getMessage());// 输出异常信息
        }
    }

    public static void main(String[] args) {
        Connection conn = getConnection("root","123456","jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=utf-8");
        closeConnection(conn, null, null);
    }
}
