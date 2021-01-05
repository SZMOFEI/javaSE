package com.mofei.generator.utils;

import com.mofei.generator.entity.ColumnEntity;
import com.mofei.generator.entity.TableEntity;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.WordUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * 代码生成器   工具类
 *
 * @author panjinfu
 * @email 
 * @date 2018年09月11日 下午17:32:04
 */
public class GenUtils {

	public static List<String> getTemplates(boolean isOracle){
		List<String> templates = new ArrayList<String>();
		templates.add("template/Entity.java.vm");
		templates.add("template/Repository.java.vm");
		if (isOracle) {
			templates.add("template/DaoOracle.xml.vm");
		} else {
			templates.add("template/Repository.xml.vm");
		}
		templates.add("template/Service.java.vm");
		templates.add("template/ServiceImpl.java.vm");
		templates.add("template/Controller.java.vm");
		//templates.add("template/list.html.vm");
		//templates.add("template/list.js.vm");
		//templates.add("template/menu.sql.vm");
		//添加模板
		templates.add("template/AddInput.java.vm");
		templates.add("template/QryInput.java.vm");
		templates.add("template/QryOutput.java.vm"); // Rename file name, modify by zhengjiaxing
		templates.add("template/UpdateInput.java.vm");
		
		return templates;
	}
	
	/**
	 * 生成代码
	 */
	public static void generatorCode(Map<String, String> table,
			List<Map<String, String>> columns, ZipOutputStream zip,boolean isOracle){
		//配置信息
		Configuration config = getConfig();
		//表信息
		TableEntity tableEntity = new TableEntity();
		tableEntity.setTableName(null == table.get("tableName")?table.get("TABLENAME"):table.get("tableName"));
		tableEntity.setComments(null == table.get("tableComment")?table.get("TABLECOMMENT"):table.get("tableComment"));
		
		
		if(config.getString("comments").trim().length()>0)
		{
			tableEntity.setComments(config.getString("comments"));
		}
		
		//表名转换成Java类名   modify by panjinfu
		String className =  config.getString("className");
		 if(className.trim().length()==0)
		 {
			 className = tableToJava(tableEntity.getTableName(), config.getString("tablePrefix"));
		 }
		
		tableEntity.setClassName(StringUtils.capitalize(className));
		tableEntity.setClassname(StringUtils.uncapitalize(className));
		
		//列信息
		List<ColumnEntity> columsList = new ArrayList<>();
		int showColumnCount = 0;
		String logicDeleted = "";
		for(Map<String, String> column : columns){
			ColumnEntity columnEntity = new ColumnEntity();
			columnEntity.setColumnName(column.get("columnName"));
			if(column.get("dataType").toUpperCase().equals("DATETIME")) {
				columnEntity.setDataType("TIMESTAMP");
			}else if(column.get("dataType").toUpperCase().equals("INT")) {
				columnEntity.setDataType("INTEGER");
			}else {
				columnEntity.setDataType(column.get("dataType").toUpperCase());
			}
			columnEntity.setComments(column.get("columnComment"));
			columnEntity.setExtra(column.get("extra"));
			columnEntity.setTableFixed(column.get("columnName"));
			columnEntity.setLogicDelete(column.get("columnName"));
			if(!"".equals(columnEntity.getLogicDelete()))
			{
				logicDeleted = "true";
			}
			columnEntity.setNotNull("NO".equals(column.get("nullAble")));
			if("".equals(columnEntity.getTableFixed()))
			{
				showColumnCount++;
			}
			
			//列名转换成Java属性名
			String attrName = columnToJava(columnEntity.getColumnName());
			columnEntity.setAttrName(attrName);
			columnEntity.setAttrname(StringUtils.uncapitalize(attrName));
			
			//列的数据类型，转换成Java类型
			String attrType = config.getString(columnEntity.getDataType(), "unknowType");
			if(attrName.startsWith("Is")  &&  "TINYINT".equals(columnEntity.getDataType().toUpperCase())) {
				attrType = "Boolean";
			}
			columnEntity.setAttrType(attrType);
			
			//是否主键
			if("PRI".equalsIgnoreCase(column.get("columnKey")) && tableEntity.getPk() == null){
				tableEntity.setPk(columnEntity);
			}
			Object obj = column.get("columnLength");
			if(obj != null && !"".equals(obj)) {
				columnEntity.setColumnLength(obj.toString());
			}
			columsList.add(columnEntity);
		}
		tableEntity.setColumns(columsList);
		
		//没主键，则第一个字段为主键
		if(tableEntity.getPk() == null){
			tableEntity.setPk(tableEntity.getColumns().get(0));
		}
		
		//设置velocity资源加载器
		Properties prop = new Properties();  
		prop.put("file.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");  
		Velocity.init(prop);
		
		//封装模板数据
		Map<String, Object> map = new HashMap<>();
		map.put("tableName", tableEntity.getTableName());
		map.put("showColumnCount", showColumnCount);
		map.put("logicDeleted", logicDeleted);
		map.put("comments", tableEntity.getComments());
		map.put("pk", tableEntity.getPk());
		map.put("className", tableEntity.getClassName());
		map.put("classname", tableEntity.getClassname());
		map.put("lowername", tableEntity.getClassname().toLowerCase());
		
		// modify by panjinfu 
		map.put("pathName", getPathName(config.getString("moduleName"), config.getString("smallclass"), tableEntity.getClassname()));
		map.put("pathname", getPathName(config.getString("moduleName"), config.getString("smallclass"), tableEntity.getClassname().toLowerCase()));
		// map.put("pathName", tableEntity.getClassname().toLowerCase());
		map.put("mainPath", config.getString("package"));
		map.put("package", getPackage(config.getString("moduleName"), config.getString("smallclass"),config.getString("package")));
		map.put("columns", tableEntity.getColumns());
		map.put("author", config.getString("author"));
		map.put("email", config.getString("email"));
		map.put("datetime", DateUtils.format(new Date(), DateUtils.DATE_TIME_PATTERN));
		//是否使用lombok
		map.put("uselombok", config.getString("uselombok"));
        VelocityContext context = new VelocityContext(map);
        
        //获取模板列表
		List<String> templates = getTemplates(isOracle);
		for(String template : templates){
			//渲染模板
			StringWriter sw = new StringWriter();
			Template tpl = Velocity.getTemplate(template, "UTF-8");
			tpl.merge(context, sw);
			
			try {
				//添加到zip  modify by panjinfu
				zip.putNextEntry(new ZipEntry(getFileName(template, tableEntity.getClassName(), config.getString("package"), config.getString("moduleName"), config.getString("smallclass"),tableEntity.getClassname())));  
				IOUtils.write(sw.toString(), zip, "UTF-8");
				IOUtils.closeQuietly(sw);
				zip.closeEntry();
			} catch (IOException e) {
				throw new RRException("渲染模板失败，表名：" + tableEntity.getTableName(), e);
			}
		}
	}
	
	
	public static String getPathName(String moduleName,String smallclass, String classname)
	{
		return moduleName +"/"+ smallclass + "/" +classname;
	}
	
	public static String getPackage(String moduleName,String smallclass, String packageName)
	{
		if(moduleName != null && !"".equals(moduleName)) {
		    packageName += "."+ moduleName;
		}
		if(smallclass != null && !"".equals(smallclass)) {
			packageName += "." + smallclass;
		}
		return packageName;
	}
	
	
	/**
	 * 列名转换成Java属性名
	 */
	public static String columnToJava(String columnName) {
		return WordUtils.capitalize(columnName, new char[]{'_'}).replace("_", "");
	}
	public static void main(String[] args) {
		//System.out.println(WordUtils.capitalizeFully("taxiNo", new char[]{'_'}).replace("_", ""));
		//System.out.println(WordUtils.capitalize("taxiNo", new char[]{'_'}).replace("_", ""));
		//System.out.println(WordUtils.capitalize("taxi_no", new char[]{'_'}).replace("_", ""));
	}
	
	/**
	 * 表名转换成Java类名
	 */
	public static String tableToJava(String tableName, String tablePrefix) {
		if(StringUtils.isNotBlank(tablePrefix)){
			tableName = tableName.replace(tablePrefix, "");
		}
		return columnToJava(tableName);
	}
	
	/**
	 * 获取配置信息
	 */
	public static Configuration getConfig(){
		try {
			return new PropertiesConfiguration("generator.properties");
		} catch (ConfigurationException e) {
			throw new RRException("获取配置文件失败，", e);
		}
	}
	
	/**
	 * 获取文件名
	 */
	public static String getFileName(String template, String className, String packageName,String moduleName,String smallclass,String classname){
		String packagePath = "main" + File.separator + "java" + File.separator;
		if(StringUtils.isNotBlank(packageName)){
			packagePath += packageName.replace(".", File.separator) + File.separator;
			// modify by panjinfu
			if(moduleName.trim().length()>0)
			{
				packagePath += moduleName + File.separator;
			}
			
			if(smallclass.trim().length()>0)
			{
				packagePath += smallclass + File.separator;
			}
		}
		
		if(template.contains("Entity.java.vm")){
			return packagePath + "entity" + File.separator + className + ".java";
		}
		
		if(template.contains("Repository.java.vm")){
			return packagePath + "repository" + File.separator + className + "Repository.java";
		}
		
		
		if(template.contains("Repository.xml.vm") || template.contains("DaoOracle.xml.vm")){
			// modify  by panjinfu
			// return packagePath + "dao" + File.separator + className + "Dao.xml";
			String mapperPath = "main" + File.separator + "resources"+ File.separator  + "repository" + File.separator;
			if(moduleName.trim().length()>0)
			{
				mapperPath += moduleName + File.separator;
			}
			
			if(smallclass.trim().length()>0)
			{
				mapperPath += smallclass + File.separator;
			}
			return mapperPath + className + "Repository.xml";
		}
		
		if(template.contains("Service.java.vm")){
			return packagePath + "service" + File.separator + "I" + className + "Service.java";
		}
		
		if(template.contains("ServiceImpl.java.vm")){
			return packagePath + "service" + File.separator + "impl" + File.separator + className + "ServiceImpl.java";
		}
		
		if(template.contains("Controller.java.vm")){
			return packagePath + "controller" + File.separator + className + "Controller.java";
		}
        // modify by panjinfu
		if(template.contains("list.html.vm")){
			return "main" + File.separator + "webapp" + File.separator + "WEB-INF" + File.separator + "view"
					+ File.separator + moduleName + File.separator +  smallclass + File.separator + className.toLowerCase() + ".html";
		}
		// modify by panjinfu
		if(template.contains("list.js.vm")){
			return "main" + File.separator + "webapp" + File.separator + "statics" + File.separator + "js" + File.separator + moduleName + File.separator + smallclass + File.separator + className.toLowerCase() + ".js";
		}

		if(template.contains("menu.sql.vm")){
			return className.toLowerCase() + ".sql";
		}
		
		if(template.contains("AddInput.java.vm")){
			return packagePath + "controller" + File.separator + "dto" + File.separator + className + "AddInput.java";
		}
		if(template.contains("QryInput.java.vm")){
			return packagePath + "controller" + File.separator + "dto" + File.separator + className + "QryInput.java";
		}
		// Rename file name, modify by zhengjiaxing
		if(template.contains("QryOutput.java.vm")){
			return packagePath + "controller" + File.separator + "dto" + File.separator + className + "QryOutput.java";
		}
		if(template.contains("UpdateInput.java.vm")){
			return packagePath + "controller" + File.separator + "dto" + File.separator + className + "UpdateInput.java";
		}
		return null;
	}
}
