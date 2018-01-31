package cn.zc.test;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.Test;

import cn.zc.baseDao.BaseDaoImpl;
import cn.zc.customerDao.CustomerDao;
import cn.zc.customerDao.CustomerDaoImpl;
import cn.zc.entity.Customer;
import cn.zc.entity.QueryCriteria;

class TestCustomerDaoImpl {

	private CustomerDao dao = new CustomerDaoImpl();
	
	@Test			//ok
	void testGetAllWithCriteria() throws SQLException {
		QueryCriteria criteria = new QueryCriteria("菜", null, null);
		List<Customer> customers = dao.getAllWithCriteria(criteria);
		System.out.println(customers);
	}
	
	@Test			//ok
	void testGetAll() throws SQLException {
		List<Customer> lists = dao.getAll();
		System.out.println(lists);
	}

	@Test		//ok
	void testAdd() throws SQLException {
		dao.add(new Customer("水菜丽", "日本", "234"));
	}

	@Test			//ok
	void testDeleteById() throws SQLException {
		dao.deleteById(1);
	}

	@Test			//ok
	void testUpdateCustomer() throws SQLException {
		Customer customer = dao.getById(4);
		customer.setAddress("中国");
		dao.update(customer);
	}

	@Test			//ok
	void testGetById() throws SQLException {
		Customer customer = dao.getById(4);
		System.out.println(customer);
	}

	@Test			//ok
	void testGetCountWithName() throws SQLException {
		long count = dao.getCountWithName("水菜丽");
		System.out.println(count);
	}
}
