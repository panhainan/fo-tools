package com.panhainan.database.impl;

import com.panhainan.database.IDBUtil;
import com.panhainan.database.common.DBParam;
import com.panhainan.database.common.DBTableColumn;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * jdbc操作MySQL数据库工具实现类
 * @author 潘海南
 * @date 2016/12/14
 */
public class MySQLUtilImpl extends DBUtilAbtImpl implements IDBUtil {


    @Override
    public List<String> getTablesName(DBParam dbParam) throws SQLException {
        Connection conn = super.getConnection(dbParam);
        PreparedStatement pstmt =null;
        ResultSet rs=null;
        List<String> list = new ArrayList<>();
        try {
            pstmt = conn.prepareStatement("show tables");
            rs = pstmt.executeQuery();
            while (rs.next()) {
                list.add(rs.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            super.closeConnection(conn,pstmt,rs);
        }
        return list;
    }

    @Override
    public List<DBTableColumn> getTableColumns(DBParam dbParam) {
        Connection conn = super.getConnection(dbParam);
        PreparedStatement pstmt =null;
        ResultSet rs=null;
        List<DBTableColumn> list = new ArrayList<>();
        try {
            pstmt = conn.prepareStatement("show full columns from "+dbParam.getTableName());
            rs = pstmt.executeQuery();
            while (rs.next()) {
                DBTableColumn t = new DBTableColumn();
                t.setField(rs.getString("field"));
                t.setType(rs.getString("type"));
                t.setComments(rs.getString("comment"));
                list.add(t);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            super.closeConnection(conn,pstmt,rs);
        }
        return list;
    }

}
