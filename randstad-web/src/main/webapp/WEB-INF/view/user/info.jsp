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
		<h2><font color="red">${user.lastName }${user.firstName }</font>发布的流程：</h2>
		<table width="600px;">
			<thead>
				<tr>
					<th>流程实例ID</th>
					<th>流程实例定义ID</th>
					<th>流程实例定义Name</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="processInstance" items="${myProcessInstances}">
					<tr>
						<td>${processInstance.id }</td>
						<td>${processInstance.processDefinitionId }</td>
						<td>${processInstance.processDefinitionName }</td>
					</tr>
				</c:forEach>
			</tbody>
		</table><br><br>
		<h2>可以申请的流程:</h2>
		<table>
			<tr>
				<td>流程ID</td>
				<td>流程名称</td>
				<td>操作</td>
			</tr>
			<c:forEach var="process" items="${processList}" >
				<tr>
					<td>${process.id }</td>
					<td>${process.name }</td>
					<td><a href="../user/applyPage?processDefinitionId=${process.id }&userId=${user.id }">申请</a></td>
				</tr>
			</c:forEach>
		</table><br><br>
		<h2>分配给我的任务:</h2>
		<table>
			<tr>
				<td>任务ID</td>
				<td>任务名称</td>
				<td>操作</td>
			</tr>
			<c:forEach var="userTask" items="${tasks}" >
				<tr>
					<td>${userTask.id }</td>
					<td>${userTask.name }</td>
					<td><a href="../user/managent?userId=${user.id }&taskId=${userTask.id }">处理</a></td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>