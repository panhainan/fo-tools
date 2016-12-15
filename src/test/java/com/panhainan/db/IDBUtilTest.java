package com.panhainan.db;

import com.panhainan.db.common.DBParam;
import com.panhainan.db.common.DBTableColumn;
import com.panhainan.db.impl.MySQLUtilImpl;
import com.panhainan.db.impl.OracleUtilImpl;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by FirePan on 2016/12/14.
 */
public class IDBUtilTest {
    @Test
    public void testMySQL() throws SQLException {
        System.out.println("************** MySQL testGetTableFields ***************");
        IDBUtil idbUtil = new MySQLUtilImpl();
        DBParam dbParam = new DBParam(null,"com.mysql.jdbc.Driver","jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=utf-8","root","123456","test");
        List<DBTableColumn> list = idbUtil.getTableFields(dbParam);
        System.out.println(list);
        List<String> tablesName = idbUtil.getTablesName(dbParam);
        System.out.println(tablesName);
    }
    @Test
    public void testOracle() throws  SQLException{
        System.out.println("************** Oracle testGetTableFields ***************");
        IDBUtil idbUtil = new OracleUtilImpl();
        DBParam dbParam = new DBParam(null,"oracle.jdbc.OracleDriver","jdbc:oracle:thin:@10.18.96.182:1521:eshopdb","portaldev","portaldev123","ADDRESS");
        List<DBTableColumn> list = idbUtil.getTableFields(dbParam);
        System.out.println(list);
        List<String> tablesName = idbUtil.getTablesName(dbParam);
        System.out.println(tablesName);
    }
}


