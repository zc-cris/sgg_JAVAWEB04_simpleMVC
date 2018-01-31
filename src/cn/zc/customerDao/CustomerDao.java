package cn.zc.customerDao;

import java.sql.SQLException;
import java.util.List;

import cn.zc.baseDao.BaseDao;
import cn.zc.entity.Customer;
import cn.zc.entity.QueryCriteria;

/**
 * CustomerDao接口，规范Customer具体的业务功能
 * @author zc-cirs
 *
 */
public interface CustomerDao {
	
	/**
	 * 模糊查询所有customers的方法
	 * @return
	 * @throws SQLException
	 */
	public List<Customer> getAllWithCriteria(QueryCriteria criteria) throws SQLException;
	
	/**
	 * 查询所有customer的方法
	 * @return
	 * @throws SQLException 
	 */
	public List<Customer> getAll() throws SQLException;
	
	/**
	 * 添加一个customer的方法
	 * @param customer
	 */
	public void add(Customer customer) throws SQLException;
	
	/**
	 * 根据id删除一个customer的方法
	 * @param id
	 */
	public void deleteById(Integer id) throws SQLException;
	
	/**
	 * 更新一个customer的方法
	 * @param customer
	 */
	public void update(Customer customer) throws SQLException;
	
	/**
	 * 根据id查询一个customer的方法
	 * @param id
	 * @return
	 */
	public Customer getById(Integer id) throws SQLException;
	
	/**
	 * 根据名字查询表中相同名字的记录数
	 * @param name
	 * @return
	 */
	public long getCountWithName(String name) throws SQLException;
}
