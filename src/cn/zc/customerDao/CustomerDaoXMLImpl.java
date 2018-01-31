package cn.zc.customerDao;

import java.sql.SQLException;
import java.util.List;

import cn.zc.entity.Customer;
import cn.zc.entity.QueryCriteria;

public class CustomerDaoXMLImpl implements CustomerDao {

	@Override
	public List<Customer> getAllWithCriteria(QueryCriteria criteria) throws SQLException {
		System.out.println("CustomerDaoXMLImpl+getAllWithCriteria");
		return null;
	}

	@Override
	public List<Customer> getAll() throws SQLException {
		System.out.println("getAll");
		return null;
	}

	@Override
	public void add(Customer customer) throws SQLException {
		System.out.println("add");
		
	}

	@Override
	public void deleteById(Integer id) throws SQLException {
		System.out.println("deleteById");
		
	}

	@Override
	public void update(Customer customer) throws SQLException {
		System.out.println("update");
		
	}

	@Override
	public Customer getById(Integer id) throws SQLException {
		System.out.println("getById");
		return null;
	}

	@Override
	public long getCountWithName(String name) throws SQLException {
		System.out.println("getCountWithName");
		return 0;
	}

}
