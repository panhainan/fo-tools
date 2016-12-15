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

    /**
     * 获取当前用户的所有表信息
     * @param dbParam  数据库连接信息对象
     * @return List<String>
     * @throws SQLException
     */
    List<String> getTablesName(DBParam dbParam) throws SQLException;

    /**
     * 获取表的所有字段信息
     * @param dbParam 数据库连接信息对象，包含表名
     * @return List<DBTableColumn>
     * @throws SQLException
     */
    List<DBTableColumn> getTableColumns(DBParam dbParam) throws SQLException;

}
