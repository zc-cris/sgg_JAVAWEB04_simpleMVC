package cn.zc.baseDao;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import cn.zc.Utils.JdbcUtil;

/**
 * 基类接口的实现类，主要是对常用的数据库操作进行实现 方便继承这个基类的子类不用重复写相同的增删改查功能了
 * 
 * @author zc-cirs
 *
 * @param <T>
 *            当前dao处理的实体类的类型
 */
public class BaseDaoImpl<T> implements BaseDao<T> {

	private Class<T> clazz;

	/**
	 * 通过父类的构造方法获取子类继承的带泛型的父类的类型，由此得到泛型的class对象
	 */
	public BaseDaoImpl() {
		Type superClass = this.getClass().getGenericSuperclass();
		if (superClass instanceof ParameterizedType) {
			ParameterizedType parameterizedType = (ParameterizedType) superClass;
			Type[] argsTypes = parameterizedType.getActualTypeArguments();
			if (argsTypes != null && argsTypes.length > 0) {
				Type type = argsTypes[0];
				if (type instanceof Class) {
					clazz = (Class<T>) type;
				}
			}
		}
	}

	@Override
	public <E> E queryValue(String sql, Object... parameters) throws SQLException {
		// 执行sql语句前应该先要进行sql语句和参数的判断
		checkSqlAndParams(sql, parameters);
		return (E) JdbcUtil.getQueryRunner().query(sql, new ScalarHandler<>(), parameters);
	}

	@Override
	public List<T> queryList(String sql, Object... parameters) throws SQLException {
		// System.out.println(clazz);
		// 执行sql语句前应该先要进行sql语句和参数的判断
		checkSqlAndParams(sql, parameters);
		return JdbcUtil.getQueryRunner().query(sql, new BeanListHandler<>(clazz), parameters);
	}

	@Override
	public T queryOne(String sql, Object... parameters) throws SQLException {
		// 执行sql语句前应该先要进行sql语句和参数的判断
		checkSqlAndParams(sql, parameters);
		return JdbcUtil.getQueryRunner().query(sql, new BeanHandler<>(clazz), parameters);
	}

	@Override
	public void update(String sql, Object... parameters) throws SQLException {
		// 执行sql语句前应该先要进行sql语句和参数的判断
		checkSqlAndParams(sql, parameters);
		JdbcUtil.getQueryRunner().update(sql, parameters);
	}
	
	/**
	 * 对sql语句和参数先进行检查
	 * @param sql
	 * @param parameters
	 */
	public void checkSqlAndParams(String sql, Object... parameters) {
		if (null == sql || "" == sql) {
			throw new RuntimeException("sql语句为null或者没有内容");
		}
		if (parameters.length > 0) {
			for (Object obj : parameters) {
				if (null == obj) {
					throw new RuntimeException("有参数为null");
				}
			}
		}
	}
}
