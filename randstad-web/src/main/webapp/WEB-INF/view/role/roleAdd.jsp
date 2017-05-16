<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../common/common.jsp" %>
<body>
	<form>
		角色名称:<br>
    	<input type="text" name="rolename" ng-model="role.rolename"><br>
    	角色描述:<br>
    	<input type="text" name="roledesc" ng-model="role.roledesc">
     	<br><br>
    	<button ng-click="addRole()">Confirm</button>
	</form>

</body>
</html>