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
		<form action="usersavegroup" method="post">
			<h2>用户详情</h2>
			<table width="600px;">
				<tbody>
					<tr>
						<td>FirstName</td>
						<td>${user.firstName }</td>
					</tr>
					<tr>
						<td>LastName</td>
						<td>${user.lastName }</td>
					</tr>
					<tr>
						<td>Email</td>
						<td>${user.email }</td>
					</tr>
					<tr>
						<td>Group</td>
						<td>
							<select name="groupId">
								<option value="">请选择...</option>
								<c:forEach var="group" items="${groups}">
									<c:choose>
										<c:when test="${group.id == userGroup.id}">
											<option value="${group.id }" selected="selected">${group.name }</option>
										</c:when>
										<c:otherwise>
											<option value="${group.id }">${group.name }</option>
										</c:otherwise>
									</c:choose>
								</c:forEach>
							</select>
						</td>
					</tr>
				</tbody>
			</table><br>
			<input type="hidden" value="${user.id }" name="userId">
			<input type="submit" value="保存" >
		</form>
		<a href="../process/user">用户</a>
		<a href="../process">流程模板</a>
	</div>
</body>
</html>