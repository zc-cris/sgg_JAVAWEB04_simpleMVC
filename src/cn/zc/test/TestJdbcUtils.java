package cn.zc.test;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.junit.jupiter.api.Test;

import cn.zc.Utils.JdbcUtil;

class TestJdbcUtils {

	/**
	 * 测试数据库连接
	 * 
	 * @throws SQLException
	 */
	@Test
	void testConnection() throws SQLException {
		Connection connection = JdbcUtil.getConnection();
		System.out.println(connection);
	}

	/**
	 * 测试dbutils的queryRunner是否可以获取到
	 * 
	 * @throws SQLException
	 */
	@Test
	void testQueryRunner() throws SQLException {
		QueryRunner queryRunner = JdbcUtil.getQueryRunner();
		System.out.println(queryRunner);
	}

}
