package com.mofei.generator.dao;

import java.util.List;
import java.util.Map;

/**
 * 代码生成器
 * 
 * @author mofei
 *
 * @version 2021年01月05日 下午17:32:04
 */
public interface SysGeneratorOracleDao {
	
	List<Map<String, Object>> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	Map<String, String> queryTable(String tableName);
	
	List<Map<String, String>> queryColumns(String tableName);
}
