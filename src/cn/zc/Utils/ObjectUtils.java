package cn.zc.Utils;

import java.lang.reflect.InvocationTargetException;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;

/**
 * 
 * @type 封装请求参数的工具类 
 * @description: 
 * @author zc-cirs
 */
public class ObjectUtils {

	public static <T> T getObject(HttpServletRequest request, Class<T> clazz) throws InstantiationException, IllegalAccessException, InvocationTargetException {
		T t = clazz.newInstance();
		BeanUtils.populate(t, request.getParameterMap());
		return t;
	}
}
