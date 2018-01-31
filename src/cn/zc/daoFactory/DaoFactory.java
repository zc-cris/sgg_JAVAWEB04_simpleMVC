package cn.zc.daoFactory;

import java.util.HashMap;
import java.util.Map;

import cn.zc.customerDao.CustomerDao;
import cn.zc.customerDao.CustomerDaoImpl;
import cn.zc.customerDao.CustomerDaoXMLImpl;

/**
 * 专门用来获取dao实现类的工厂(工厂一般都是单例的)
 * @author zc-cirs
 *
 */
public class DaoFactory {
	
	private static DaoFactory daoFactory = null;
	public static DaoFactory getInstance() {
		if(daoFactory == null) {
			daoFactory = new DaoFactory();
		}
		return daoFactory;
	}
	
	private Map<String, CustomerDao> daos = new HashMap<>();
	private DaoFactory() {
		daos.put("jdbc", new CustomerDaoImpl());
		daos.put("xml", new CustomerDaoXMLImpl());
	};
	private String type = null;
	//一开始就在系统启动的时候给type赋值
	public void setType(String type) {
		this.type = type;
	}
	public CustomerDao getCustomerDao() {
		return daos.get(type);
	}
}
