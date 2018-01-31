package cn.zc.baseDao;

import java.sql.SQLException;
import java.util.List;

/**
 * Dao的基类接口
 * 定义了普遍的对数据库的增删改查操作
 * @author zc-cirs
 *
 */
public interface BaseDao<T> {

	
	/**
	 * 定义了查询特定数据值的方法，例如查询：某一行数据的name，或者是总记录数...
	 * @param sql
	 * @param parameters
	 * @return	指定要查询的数据值
	 * @throws SQLException 
	 */
	<E> E queryValue(String sql, Object...parameters) throws SQLException;
	
	/**
	 * 定义了查询多个实体对象集合的方法，同样使用到了dbUtils工具包
	 * @param sql
	 * @param parameters
	 * @return	多个实体对象的List集合
	 * @throws SQLException 
	 */
	List<T> queryList(String sql, Object...parameters) throws SQLException;
	
	/**
	 * 定义了查询一个实体的操作，也是通过dbUtils来实现
	 * @param sql
	 * @param parameters
	 * @return	实体的一个对象
	 * @throws SQLException 
	 */
	T queryOne(String sql, Object...parameters) throws SQLException;
	
	/**
	 * 定义了增删改操作，通过dbUtils来实现
	 * @param sql
	 * @param parameters
	 * @throws SQLException 
	 */
	void update(String sql, Object... parameters) throws SQLException;
	
}
