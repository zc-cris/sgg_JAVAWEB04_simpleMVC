<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="cn.zc.entity.Customer,java.util.List"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="jquery/jquery-1.8.2.js"></script>
<script type="text/javascript">
	//删除信息之前需要提醒一下
	$(function() {
		$(".delete").click(function() {
			//获取到当前行的用户名字信息
			var content = $(this).parent().parent().find("td:eq(1)").text();
			var flag = confirm("确定是要删除" + content + "的信息吗？");
			return flag;
		});
	});
</script>
<title>Insert title here</title>
</head>
<body>
	<form action="query.do" method="post">
		<table>
			<tr>
				<td>姓名：</td>
				<td><input type="text" name="name" /></td>
			</tr>
			<tr>
				<td>地址：</td>
				<td><input type="text" name="address" /></td>
			</tr>
			<tr>
				<td>电话：</td>
				<td><input type="text" name="phone" /></td>
			</tr>
			<tr>
				<td><input type="submit" value=" 查询" /></td>
				<td><a href="addCustomer.jsp">添加用户</a></td>
			</tr>
		</table>
	</form>

	<hr>
	<c:if test="${!empty requestScope.customers }">
	
	<table border="1" cellpadding="10" cellspacing="0">
		<tr>
			<th>编号</th>
			<th>姓名</th>
			<th>住址</th>
			<th>电话</th>
			<th>操作</th>
		</tr>

		<c:forEach items="${requestScope.customers }" var="customer">
		<tr>
			<td>${customer.id }</td>
			<td>${customer.name }</td>
			<td>${customer.address }</td>
			<td>${customer.phone }</td>
			<td>
				<c:url value="/getCustomerById.do" var="editurl">
					<c:param name="id" value="${customer.id }"></c:param>
				</c:url>
			<a href="${editurl }">更新</a>
			<c:url value="/delete.do" var="deleteurl">
					<c:param name="id" value="${customer.id }"></c:param>
				</c:url>
				&nbsp;<a class="delete" href="${deleteurl }">删除</a>
				</td>
		</tr>
		</c:forEach>
	</table>
	</c:if>
</body>
</html>