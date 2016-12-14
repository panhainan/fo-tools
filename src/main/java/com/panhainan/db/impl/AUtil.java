package com.panhainan.db.impl;

import com.panhainan.db.common.DBParam;
import com.panhainan.db.common.DBTableField;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by FirePan on 2016/12/14.
 */
public class AUtil extends DBUtilAbtImpl {

    public List<DBTableField> getTableFields(DBParam dbParam,String sql) {
        Connection conn = super.getConnection(dbParam);
        List<DBTableField> list = new ArrayList<>();
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                DBTableField t = new DBTableField();
                t.setField(rs.getString(1));
                t.setType(rs.getString(2));
                t.setDesc(rs.getString(3));
                list.add(t);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
