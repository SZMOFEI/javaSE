package com.mofei.generator.service;

import com.mofei.generator.dao.SysGeneratorDao;
import com.mofei.generator.dao.SysGeneratorOracleDao;
import com.mofei.generator.utils.GenUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipOutputStream;

/**
 * 代码生成器
 * 
 * @author mofei
 *
 * @version 2021年01月05日 下午17:32:04
 */
@Service
public class SysGeneratorService implements InitializingBean {
	@Autowired
	private SysGeneratorOracleDao sysGeneratorDaoO;

	@Autowired
	private SysGeneratorDao sysGeneratorDao;

	@Value("${spring.datasource.driverClassName}")
	private String driverClassName = "";

	private boolean isOracle = false;

	public List<Map<String, Object>> queryList(Map<String, Object> map) {
		if (isOracle) {
			return sysGeneratorDaoO.queryList(map);
		}
		return sysGeneratorDao.queryList(map);
	}

	public int queryTotal(Map<String, Object> map) {
		if (isOracle) {
			return sysGeneratorDaoO.queryTotal(map);
		}
		return sysGeneratorDao.queryTotal(map);
	}

	public Map<String, String> queryTable(String tableName) {
		if (isOracle) {
			return sysGeneratorDaoO.queryTable(tableName);
		}
		return sysGeneratorDao.queryTable(tableName);
	}

	public List<Map<String, String>> queryColumns(String tableName) {
		if (isOracle) {
			return sysGeneratorDaoO.queryColumns(tableName);
		}
		return sysGeneratorDao.queryColumns(tableName);
	}

	public byte[] generatorCode(String[] tableNames) {
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		ZipOutputStream zip = new ZipOutputStream(outputStream);

		for (String tableName : tableNames) {
			// 查询表信息
			Map<String, String> table = queryTable(tableName);
			// 查询列信息
			List<Map<String, String>> columns = queryColumns(tableName);
			// 生成代码
			GenUtils.generatorCode(table, columns, zip, isOracle);
		}
		IOUtils.closeQuietly(zip);
		return outputStream.toByteArray();
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		isOracle = driverClassName.contains("oracle");
	}
}
