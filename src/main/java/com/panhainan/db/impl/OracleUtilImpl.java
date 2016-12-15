package com.panhainan.db.impl;

import com.panhainan.db.IDBUtil;
import com.panhainan.db.common.DBParam;
import com.panhainan.db.common.DBTableColumn;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * jdbc操作Oracle数据库工具实现类
 * @author 潘海南
 * @date 2016/12/14
 */
public class OracleUtilImpl extends DBUtilAbtImpl implements IDBUtil {
    @Override
    public List<String> getTablesName(DBParam dbParam) throws SQLException {
        Connection conn = super.getConnection(dbParam);
        PreparedStatement pstmt =null;
        ResultSet rs=null;
        List<String> list = new ArrayList<>();
        try {
            pstmt = conn.prepareStatement("select table_name from user_tables");
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
            pstmt = conn.prepareStatement("select * from user_tab_columns where Table_Name=?");
            pstmt.setString(1,dbParam.getTableName());
            rs = pstmt.executeQuery();
            while (rs.next()) {
                DBTableColumn t = new DBTableColumn();
                t.setField(rs.getString("COLUMN_NAME"));
                t.setType(rs.getString("DATA_TYPE"));
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
