package com.panhainan.db;

import com.panhainan.db.common.DBParam;
import com.panhainan.db.common.DBTableColumn;

import java.sql.SQLException;
import java.util.List;

/**
 * jdbc操作数据库工具接口
 * @author 潘海南
 * @date 2016/12/14
 */
public interface IDBUtil {

    List<String> getTablesName(DBParam dbParam) throws SQLException;

    List<DBTableColumn> getTableFields(DBParam dbParam) throws SQLException;

}
