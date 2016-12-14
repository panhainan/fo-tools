package com.panhainan.database;

public class DBTableField {

	private String field;
    private String type;
	private String desc;

    @Override
    public String toString() {
        return "DBTableField{" +
                "field='" + field + '\'' +
                ", type='" + type + '\'' +
                ", desc='" + desc + '\'' +
                '}';
    }

    public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}
}