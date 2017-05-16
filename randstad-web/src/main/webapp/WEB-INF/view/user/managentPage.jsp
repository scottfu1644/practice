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
		<h2>任务详情：</h2>
		<table>
			<tr>
				<td>流程ID</td>
				<td>流程名称</td>
				<td>操作</td>
			</tr>
			<tr>
				<td>${userTask.id }</td>
				<td>${userTask.name }</td>
				<td><a href="../user/complete?userId=${userId }&taskId=${userTask.id }">批准</a></td>
			</tr>
		</table>
	</div>
</body>
</html>