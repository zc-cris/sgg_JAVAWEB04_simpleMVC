package cn.zc.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.zc.daoFactory.DaoFactory;

public class InitServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	public void init() throws ServletException {
		String type = "jdbc";			//默认type值是jdbc
		InputStream in = getServletContext().getResourceAsStream("/WEB-INF/classes/daoSwitch.properties");
		Properties pro = new Properties();
		try {
			pro.load(in);
			type = pro.getProperty("type");
			DaoFactory.getInstance().setType(type);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
