package com.panhainan.database.impl;

import com.panhainan.database.IDBUtil;
import com.panhainan.database.common.DBParam;
import com.panhainan.database.common.DBTableColumn;

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
            pstmt = conn.prepareStatement("select t1.COLUMN_NAME,t1.DATA_TYPE,t2.COMMENTS from (select COLUMN_NAME,DATA_TYPE from user_tab_columns where TABLE_NAME=?) t1 LEFT JOIN (select  COLUMN_NAME,COMMENTS  from   user_col_comments where TABLE_NAME=?) t2 on  t1.COLUMN_NAME=t2.COLUMN_NAME");
            pstmt.setString(1,dbParam.getTableName());
            pstmt.setString(2,dbParam.getTableName());
            rs = pstmt.executeQuery();
            while (rs.next()) {
                DBTableColumn t = new DBTableColumn();
                t.setField(rs.getString("COLUMN_NAME"));
                t.setType(rs.getString("DATA_TYPE"));
                t.setComments(rs.getString("COMMENTS"));
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
