package cn.zc.customerDao;

import java.sql.SQLException;
import java.util.List;

import cn.zc.baseDao.BaseDaoImpl;
import cn.zc.entity.Customer;
import cn.zc.entity.QueryCriteria;

/**
 * CustomerDao接口功能的具体实现
 * @author zc-cirs
 *
 */
public class CustomerDaoImpl extends BaseDaoImpl<Customer> implements CustomerDao{

	@Override
	public List<Customer> getAllWithCriteria(QueryCriteria criteria) throws SQLException {
		String sql = "select id,name,address,phone from tb_customers where name like"
				+ " ? and address like ? and phone like ?";
		//需要修改封装条件对象criteria的getter方法：使其返回值前后被%包裹（模糊查询）
		return queryList(sql, criteria.getName(),criteria.getAddress(),criteria.getPhone());
	}
	@Override
	public List<Customer> getAll() throws SQLException {
		String sql = "select id,name,address,phone from tb_customers";
		//调用父类的查询全部数据方法
		return queryList(sql);
	}

	@Override
	public void add(Customer customer) throws SQLException {
		String sql = "insert into tb_customers (name,address,phone) values (?,?,?)";
		//调用父类的更新操作
		update(sql, customer.getName(),customer.getAddress(),customer.getPhone());
	}

	@Override
	public void deleteById(Integer id) throws SQLException {
		String sql = "delete from tb_customers where id=?";
		update(sql, id);
	}

	@Override
	public void update(Customer customer) throws SQLException {
		String sql = "update tb_customers set name=?,address=?,phone=? where id = ?";
		update(sql, customer.getName(),customer.getAddress(),customer.getPhone(),customer.getId());
	}

	@Override
	public Customer getById(Integer id) throws SQLException {
		String sql = "select id,name,address,phone from tb_customers where id=? ";
		return queryOne(sql, id);
	}

	@Override
	public long getCountWithName(String name) throws SQLException {
		String sql = "select count(id) from tb_customers where name = ?";
		return queryValue(sql, name);
	}
}
