package com.panhainan.db.impl;

import com.panhainan.db.common.DBParam;

import java.sql.*;

/**
 * jdbc操作数据库工具抽象类
 * @author 潘海南
 * @date 2016/12/14
 */
public abstract class DBUtilAbtImpl {


    protected Connection getConnection(DBParam dbParam) {
        try {
            System.out.println("**\tConnection params info ... ");
            System.out.println("**\tdriverClassName : " + dbParam.getDirverClassName());
            System.out.println("**\tusername : " + dbParam.getUserName());
            System.out.println("**\tpassword : " + dbParam.getUserPwd());
            System.out.println("**\turl : " + dbParam.getUrl());
            System.out.println("**\tDatabase connect start ...");
            // DriverManager.registerDriver(new Driver());
            Class.forName(dbParam.getDirverClassName()).newInstance();
            Connection conn = null;
            conn = DriverManager.getConnection(dbParam.getUrl(), dbParam.getUserName(), dbParam.getUserPwd());
            if (conn != null) {
                System.out.println("**\tDatabase connect success : conn = " + conn);
                return conn;
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("**\t**Error**:caused at "
                    + new Throwable().getStackTrace()[0].getMethodName() + "::"
                    + e.getMessage());
        }
        System.out.println("**\t**Error**:Database connect failed !");
        return null;
    }

    protected void closeConnection(Connection conn, PreparedStatement pstm,
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
}
