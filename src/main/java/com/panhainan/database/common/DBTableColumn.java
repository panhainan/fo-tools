package com.panhainan.database.common;

/**
 * 数据库表的字段类
 * @author 潘海南
 * @date 2016/12/14
 */
public class DBTableColumn {

	private String field;
    private String type;
    private String comments;

    @Override
    public String toString() {
        return "DBTableColumn{" +
                "field='" + field + '\'' +
                ", type='" + type + '\'' +
                ", comments='" + comments + '\'' +
                '}';
    }

    public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}