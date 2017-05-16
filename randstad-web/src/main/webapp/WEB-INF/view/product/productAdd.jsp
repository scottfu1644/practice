<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../common/common.jsp" %>

<form>
	产品编码:<br>
	<input type="text" name="code" ng-model="product.code"><br>
	产品名称:<br>
	<input type="text" name="name" ng-model="product.name"><br>
	产品规格:<br>
	<input type="text" name="spec" ng-model="product.spec"><br>
	<br>

	<p>产品附件:</p>
	<form class="form-horizontal" name="form">
		<div class="form-line">
			<label>请选择附件A：</label><span class="small-tip">证书文件只支持A类附件格式，文件大小2M以内</span>
			<div class="choose-file-area">
				<input class="file-name" type="text" readonly="readonly" ng-model="product.fileA"/>
				<a href="javascript:;" class="choose-book">
				<input type="file" name="certificate" nv-file-select uploader="uploader" ng-click="clearItems()"/>浏览
				</a>
			</div>
		</div>
	</form>

	<p>产品owner:</p>
	<div ng-app="app" ng-controller="myCtrl">
		<select ng-model="product.owner" ng-options="x.owner for x in owners"></select>
		<h1>选择: {{product.owner.owner}}</h1>
		<p>具体信息: {{product.owner.url}}</p>
	</div>
	<br>
	<button ng-click="addProduct()">Confirm</button>
</form>

<script>
	var app = angular.module('app', []);
	app.controller('myCtrl', function($scope) {
	$scope.owners = [
	{owner : "zhangsan", url : "good man"},
	{owner : "wangwu", url : "good girl"},
	{owner : "zhaoliu", url : "manager"},
	{owner : "zhangsan2", url : "good man2"},
	{owner : "wangwu2", url : "good girl2"},
	{owner : "zhaoliu2", url : "manager2"}
	];
	});
</script>