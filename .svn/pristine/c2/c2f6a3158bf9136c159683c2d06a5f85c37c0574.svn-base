<%@ page session="true" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
pageContext.setAttribute("base",request.getContextPath());
String path = request.getContextPath();
String base = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
String baseURL = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/a";
pageContext.setAttribute("baseURL",baseURL);
%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="zh-CN" lang="zh-CN" prefix="og: http://ogp.me/ns#">
<head>
    <title></title>
</head>
<body>
	<form action="${baseURL}/user/login" method="post" name="loginForm">
		<table>
			<tr>
				<td>用户名:</td>
				<td><input type="text" name="userName" /></td>
			</tr>
			<tr>
				<td>密码:</td>
				<td><input type="password" name="password" /></td>
			</tr>
			
			<tr>
				<td></td>
				<td><button onclick="document.loginForm.submit();">登&nbsp;&nbsp;&nbsp;录</button></td>
			</tr>
		</table>
	</form>
</body>
</html>
