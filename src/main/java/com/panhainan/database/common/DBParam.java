package com.panhainan.database.common;

/**
 * jdbc连接数据库所需信息类
 * @author 潘海南
 * @date 2016/12/14
 */
public class DBParam {

    private String dbType;
    private String dirverClassName;
    private String url;
    private String userName;
    private String userPwd;
    private String tableName;

    public DBParam() {
    }

    public DBParam(String dbType, String dirverClassName, String url, String userName, String userPwd, String tableName) {
        this.dbType = dbType;
        this.dirverClassName = dirverClassName;
        this.url = url;
        this.userName = userName;
        this.userPwd = userPwd;
        this.tableName = tableName;
    }

    public String getDbType() {
        return dbType;
    }

    public void setDbType(String dbType) {
        this.dbType = dbType;
    }

    public String getDirverClassName() {
        return dirverClassName;
    }

    public void setDirverClassName(String dirverClassName) {
        this.dirverClassName = dirverClassName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }
}
