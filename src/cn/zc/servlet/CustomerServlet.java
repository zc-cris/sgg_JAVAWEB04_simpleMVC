package cn.zc.servlet;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.List;

import javax.management.RuntimeErrorException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.zc.Utils.JdbcUtil;
import cn.zc.Utils.ObjectUtils;
import cn.zc.customerDao.CustomerDao;
import cn.zc.customerDao.CustomerDaoImpl;
import cn.zc.daoFactory.DaoFactory;
import cn.zc.entity.Customer;
import cn.zc.entity.QueryCriteria;

public class CustomerServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	// 通过工厂模式来获取dao实现类
	private CustomerDao customerDao = DaoFactory.getInstance().getCustomerDao();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 所有get请求也用doPost方法来处理
		doPost(request, response);
	}

	/**
	 * 根据servletPath得到方法名，通过反射调用对应的方法			//ok
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1.获取serlvetPath：/add.do或者 /update.do
		String servletPath = request.getServletPath();
		// 2.对servletPath进行处理，得到方法名：add或者是update
		String methodName = servletPath.substring(1);
		methodName = methodName.substring(0, methodName.indexOf("."));
		// 3.通过反射调用该servlet里的方法
		try {
			Method method = getClass().getDeclaredMethod(methodName, HttpServletRequest.class,
					HttpServletResponse.class);
			method.invoke(this, request, response);
		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			response.sendRedirect("error.jsp");
		}
	}

	/**
	 * 添加新用户 //ok
	 * 
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws Exception 
	 */
	private void addCustomer(HttpServletRequest request, HttpServletResponse response) throws IOException, Exception {
			request.setCharacterEncoding("utf-8");
			String name = request.getParameter("name");	// 前台传来的数据要么是字符串，要么是""
			String address = request.getParameter("address");
			String phone = request.getParameter("phone");
			if("" == name || ""== address || "" == phone) {
				errorMessage(request, response, "用户信息不完整", "/addCustomer.jsp");
				return;
			}
			try {
				int phoneNum = Integer.parseInt(phone);
			} catch (Exception e) {
				errorMessage(request, response, "电话号码有非数字组成", "/addCustomer.jsp");
				return;
			}
			
			long count = customerDao.getCountWithName(name);
			if (count > 0) {
				errorMessage(request, response, "已经有叫"+name+"的用户了", "/addCustomer.jsp");
				return;
			}
			Customer customer = ObjectUtils.getObject(request, Customer.class);
			customerDao.add(customer);
			response.sendRedirect("success.jsp");
	}
	
	//对用户传来的参数进行判断,不满足要求就执行的方法(应该由前端进行判断)		//ok
	public void errorMessage(HttpServletRequest request, HttpServletResponse response, String message, String urlPath)
			throws ServletException, IOException {
		request.setAttribute("message", message);
		request.getRequestDispatcher(urlPath).forward(request, response);
	}

	/**
	 * 跟新用户信息需要先查询(因为显示的用户信息和可修改的信息很有可能不一致)  //ok
	 * @param request
	 * @param response
	 * @throws SQLException 
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void getCustomerById(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		String idStr = request.getParameter("id");		//get请求(超链接形式)传来的id需要进行检验
		String path = "/error.jsp";
		int id = 0;
		try {
			id = Integer.parseInt(idStr);
			Customer customer = customerDao.getById(id);
			if(customer != null) {
				request.setAttribute("customer", customer);
				path = "/updateCustomer.jsp";
			}
		} catch (NumberFormatException e) {
		}
		//如果错误直接到error.jsp，那么就req如果顺利执行，request域里一定有customer属性
		request.getRequestDispatcher(path).forward(request, response);
		
	}
	/**
	 * 根据前台传来的数据进行更新				//ok
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 * @throws SQLException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	private void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, InstantiationException, IllegalAccessException, InvocationTargetException {
		request.setCharacterEncoding("utf-8");
		String oldName = request.getParameter("oldName");
		String name = request.getParameter("name");
		String id = request.getParameter("id");			//隐藏域中的值不用检查
		String address = request.getParameter("address");
		String phone = request.getParameter("phone");
		//需要保证oldName必须为要修改的用户的名字,因为如果更新失败返回到更新页面时，名字应该为最开始要更改的用户的原始名字
		Customer customer = new Customer(Integer.parseInt(id), oldName, address, phone);
		System.out.println(customer);
		request.setAttribute("customer", customer);
		//最好前台用ajax校验
		if("" == name || ""== address || "" == phone) {
			errorMessage(request, response, "用户信息不完整", "/updateCustomer.jsp");
			return;
		}
		try {
			int phoneNum = Integer.parseInt(phone);
		} catch (Exception e) {
			errorMessage(request, response, "电话号码有非数字组成", "/updateCustomer.jsp");
			return;
		}
		//先比较最开始要修改的用户的名字和当前的名字是否一致(如果相同，说明用户修改的不是名字，这时直接进行修改即可)
		//由于在mysql中英文字母不区分大小写，所以这里需要用equalsIgnoreCase方法来进行字符串相等判断
		if(oldName.equalsIgnoreCase(name)) {			
			updateSuccess(response, name, customer);
			return;
		}
		long count = customerDao.getCountWithName(name);
		if(count>0) {
			errorMessage(request, response, "已经有叫"+name+"的用户了", "/updateCustomer.jsp");
			return;
		}
		updateSuccess(response, name, customer);
	}

	/**
	 * 校验成功后的数据更新操作		//ok
	 * @param response
	 * @param name
	 * @param customer
	 * @throws SQLException
	 * @throws IOException
	 */
	public void updateSuccess(HttpServletResponse response, String name, Customer customer)
			throws SQLException, IOException {
		//需要把oldName变为要修改的名字(数据库没有这个要修改的名字)
		customer.setName(name);
		customerDao.update(customer);
		response.sendRedirect("success.jsp");
	}

	/**
	 * 根据id删除数据 //ok
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// request.setCharacterEncoding("utf-8"); //因为根据id删除，前台传来的数据必须是数字，不用设置编码格式
		String idStr = request.getParameter("id");
		int id = 0;
		try {
			id = Integer.parseInt(idStr); // 巧妙利用try...catch(...){}解析错误直接重定向到query.do
			customerDao.deleteById(id);
		} catch (Exception e) {
		}
		response.sendRedirect("query.do");
	}

	/**
	 * 模糊查询 //ok
	 * 
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 * @throws SQLException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	private void query(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, InstantiationException, IllegalAccessException, InvocationTargetException {
		
			request.setCharacterEncoding("utf-8");
//			String name = request.getParameter("name");
//			String address = request.getParameter("address");
//			String phone = request.getParameter("phone");
			// 前端传来的字符串参数如果用户不输入默认是""
			// System.out.println(address == null); false
			// System.out.println(address == ""); true
			// 将前端传来的数据封装为criteria对象
			QueryCriteria criteria = ObjectUtils.getObject(request, QueryCriteria.class);
			List<Customer> customers = customerDao.getAllWithCriteria(criteria);
			request.setAttribute("customers", customers);
			request.getRequestDispatcher("/index.jsp").forward(request, response);
	}

	/**
	 * 重定向回首页 ok
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void home(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.sendRedirect("index.jsp");
	}

}
