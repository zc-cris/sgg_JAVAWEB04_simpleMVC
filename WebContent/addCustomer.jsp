<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
	
	<form action="addCustomer.do" method="post">
		<table>
			<tr>
				<td>姓名：</td>
				<td><input type="text" name="name"
					value="${param.name }" /></td>
			</tr>
			<tr>
				<td>地址：</td>
				<td><input type="text" name="address"
					value="${param.address }" /></td>
			</tr>
			<tr>
				<td>电话：</td>
				<td><input type="text" name="phone"
					value="${param.phone }" /></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="添加用户" /></td>
			</tr>
		</table>
	</form>
</body>
</html>