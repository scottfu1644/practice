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
		<h2>申请 ${processDefinitionId } ${userId }</h2>
		<form action="apply" method="post">
			<%-- <table width="600px;">
				<c:forEach var="user" items="${users}">
					<tr>
						<td><input type="radio" name="candidateId" value="${user.id }"></td>
						<td>${user.lastName },${user.firstName }</td>
					</tr>
				</c:forEach>
			</table> --%>
			<input type="hidden" value="${processDefinitionId }" name="processDefinitionId">
			<input type="hidden" value="${userId }" name="userId">
			<input type="submit" value="提交">
		</form>
	</div>
</body>
</html>