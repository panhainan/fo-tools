package com.panhainan.db;

import com.panhainan.db.common.DBParam;
import com.panhainan.db.common.DBTableField;
import com.panhainan.db.impl.AUtil;
import com.panhainan.db.impl.DBUtilAbtImpl;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by FirePan on 2016/12/14.
 */
public class DBUtilTest {
    public static void main(String[] args) throws SQLException {
        DBUtilAbtImpl dbUtil = new AUtil();
        DBParam dbParam = new DBParam(null,"com.mysql.jdbc.Driver","jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=utf-8","root","123456","test");

        List<DBTableField> list = dbUtil.getTableFields(dbParam,"show full columns from test");
        System.out.println(list);
    }
}


