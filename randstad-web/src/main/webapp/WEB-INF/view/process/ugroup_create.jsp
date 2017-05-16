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
		<form action="ugroupsave" method="post">
			<h2>新增用户组</h2>
			<table width="600px;">
				<tbody>
					<tr>
						<td>名称</td>
						<td><input type="text" name="gName" value=""></td>
					</tr>
					<tr>
						<td>类型</td>
						<td><input type="text" name="gType" value=""></td>
					</tr>
				</tbody>
			</table><br>
			<input type="submit" value="保存" >
		</form>
		<a href="../process/ugroup">用户组</a>
		<a href="../process">流程模板</a>
	</div>
</body>
</html>