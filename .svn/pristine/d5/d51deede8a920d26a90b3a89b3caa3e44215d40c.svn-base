<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
table,table td,table th {
	border: 1px solid gray;
	border-collapse: collapse;
}

a {
	height: 30px;
	line-height: 30px;
	border: 1px solid black;
	background: gray;
	color: white;
	text-decoration: none;
	padding: 3px;
	font-weight: bold;
}
</style>
</head>
<body>

	<div style="margin: 0 auto; width: 600px; padding-top: 50px;">
		<h2>用户</h2>
		<table width="600px;">
			<thead>
				<tr>
					<th>ID</th>
					<th>FirstName</th>
					<th>LastName</th>
					<th>Email</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="user" items="${user}">
					<tr>
						<td><a href="../process/userdetail?userId=${user.id }">${user.id }</a></td>
						<td>${user.firstName }</td>
						<td>${user.lastName }</td>
						<td>${user.email }</td>
						<td><a href="">编辑</a><a>删除</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table><br>
		<a href="usercreate">新增用户</a>
		<a href="../process">流程模板</a>
	</div>
</body>
</html>