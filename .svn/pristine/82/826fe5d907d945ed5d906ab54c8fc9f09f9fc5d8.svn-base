<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../common/common.jsp" %>

<form>
	申请人:<br>
	<input type="text" name="creatername" ng-model="apply.creatername">
	<br><br>
	申请日期:<br>
	<input type="text" name="createrdate" ng-model="apply.createrdate">
	<br><br>
	申请部门:<br>
	<input type="checkbox" ng-true-value="true" ng-false-value="false" ng-model="apply.department.HR"/>HR
	<input type="checkbox" ng-true-value="true" ng-false-value="false" ng-model="apply.department.GA"/>GA
	<input type="checkbox" ng-true-value="true" ng-false-value="false" ng-model="apply.department.IT"/>IT
	<input type="checkbox" ng-true-value="true" ng-false-value="false" ng-model="apply.department.MK"/>MK
	<br><br>
	申请原因:<br>
	<div ng-app="myapp" ng-controller="myCtrl">
	<select ng-init="selectedreason = reasons[0]" ng-model="selectedreason" ng-options="x for x in reasons"></select>
	<br><br>
	用途:<br>
	<select ng-init="selectedpurpose = purposes[0]" ng-model="selectedpurpose" ng-options="x for x in purposes"></select>
	</div>
	<br><br>
	是否急用:<br>
	<input type="radio" name="status" value="1" ng-model="apply.status" />急用<br>
	<input type="radio" name="status" value="0" ng-model="apply.status" />非急用<br>
	<input type="radio" name="status" value="2" ng-model="apply.status" />常规备货<br>
	<br><br>
	<button ng-click="addApply()">Confirm</button>
</form>

	<script>
	var app = angular.module('myapp', []);
	app.controller('myCtrl', function($scope) {
	$scope.reasons = ["生产", "备用"];
	$scope.purposes = ["生产", "备用"];
	});
	</script>
