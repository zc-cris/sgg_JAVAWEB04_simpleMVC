<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="cn.zc.entity.Customer"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<span style="color: red">
		${requestScope.message }
	</span>

	<!-- 通过后台处理，如果可以进入到这个页面，那么customer肯定不会为null -->

	<form action="update.do" method="post">
		<!-- 隐藏域用来传递用户id和当前用户的名字 -->
		<input type="hidden" name="id"
			value="${requestScope.customer.id }" /> <input
			type="hidden" name="oldName"
			value="${requestScope.customer.name }" />
		<table>
			<tr>
				<td>姓名：</td>
				<td><input type="text" name="name"
					value="${requestScope.customer.name }" /></td>
			</tr>
			<tr>
				<td>地址：</td>
				<td><input type="text" name="address"
					value="${requestScope.customer.address }" /></td>
			</tr>
			<tr>
				<td>电话：</td>
				<td><input type="text" name="phone"
					value="${requestScope.customer.phone }" /></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="修改用户" /></td>
			</tr>
		</table>
	</form>

</body>
</html>