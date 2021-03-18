package com.mofei.generator.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * 列的属性
 * 
 * @author mofei
 *
 * @version 2021年01月05日 下午17:32:04
 */
public class ColumnEntity {
	//列名
    private String columnName;
    //列名类型
    private String dataType;
    //列名备注
    private String comments;
    
    //属性名称(第一个字母大写)，如：user_name => UserName
    private String attrName;
    //属性名称(第一个字母小写)，如：user_name => userName
    private String attrname;
    //属性类型
    private String attrType;
    //auto_increment
    private String extra;
    // 表格的固定列： create_user,create_time,update_user,update_time,status,dept_id
    private String tableFixed ="";
    // 非空字段
    private boolean notNull;
    
    private String columnLength;
    
    private String logicDelete="";
    
	public String getColumnName() {
		return columnName;
	}
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}
	public String getDataType() {
		return dataType;
	}
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public String getAttrname() {
		return attrname;
	}
	public void setAttrname(String attrname) {
		this.attrname = attrname;
	}
	public String getAttrName() {
		return attrName;
	}
	public void setAttrName(String attrName) {
		this.attrName = attrName;
	}
	public String getAttrType() {
		return attrType;
	}
	public void setAttrType(String attrType) {
		this.attrType = attrType;
	}
	public String getExtra() {
		return extra;
	}
	public void setExtra(String extra) {
		this.extra = extra;
	}
	public String getTableFixed() {
		return tableFixed;
	}
	public void setTableFixed(String columnName) {
		// 如果是固定列
		if("id".equals(columnName.toLowerCase()))
		{
			return;
		}
		List<String> commonColumnList = new ArrayList<String>();
		commonColumnList.add("org_id");
		commonColumnList.add("tenant_id");
		commonColumnList.add("create_user_id");
		commonColumnList.add("create_user_name");
		commonColumnList.add("create_time");
		commonColumnList.add("last_modify_user_id");
		commonColumnList.add("last_modify_user_name");
		commonColumnList.add("last_modify_time");
		commonColumnList.add("is_deleted");
		commonColumnList.add("deleted_user_id");
		commonColumnList.add("deleted_user_name");
		commonColumnList.add("deleted_time");
		if(commonColumnList.contains(columnName))
		{
			this.tableFixed = "true";
		}
	}
	public String getLogicDelete() {
		return logicDelete;
	}
	public void setLogicDelete(String columnName) {
		/*if("is_deleted".equals(columnName.toLowerCase()))
		{
			this.logicDelete = "true";
		}*/
		logicDelete = "";
	}
	public boolean isNotNull() {
		return notNull;
	}
	public void setNotNull(boolean notNull) {
		this.notNull = notNull;
	}
	/**
	 * @return the columnLength
	 */
	public String getColumnLength() {
		return columnLength;
	}
	/**
	 * @param columnLength the columnLength to set
	 */
	public void setColumnLength(String columnLength) {
		this.columnLength = columnLength;
	}
}
