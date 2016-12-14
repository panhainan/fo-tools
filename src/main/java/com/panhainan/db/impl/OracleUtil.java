package com.panhainan.db.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * Created by FirePan on 2016/12/14.
 */
public class OracleUtil {
    static ResourceBundle rbundle = ResourceBundle.getBundle("db");
    private static String driverName = rbundle.getString("oracle.driverClassName");
    private static String dbUser = rbundle.getString("oracle.username");
    private static String dbPass = rbundle.getString("oracle.password");
    private static String dbUrl = rbundle.getString("oracle.url");
    /**
     * @date 2015-4-8
     * @TODO 获取数据库连接
     * @return Connection
     */
    public static Connection getConnection() {
        try {
            System.out.println("**\tRead db.properties info ... ");
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
    public static void main(String[] args) {
        Connection conn = getConnection();
    }
}
