<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../common/common.jsp"%> 
<!-- Content Header (Page header) -->
<!-- Main content -->
<section class="content-header">
	<h1>
		用户信息管理<small>编辑</small>
	</h1>
</section>
<section class="content">
	<!-- Horizontal Form -->
	<div class="row">
		<div class="col-md-12">
			<div class="box box-info">
				<div class="box-header with-border">
					<h3 class="box-title">编辑</h3>&nbsp;&nbsp;&nbsp;{{editStatu}}
				</div>
				<table class="table table-bordered table-striped">
					<thead>
					<tr>
						<th style="width: 10px">ID</th>
						<th style="width: 40px">父节点</th>
						<th>子节点</th>
					</tr>
					</thead>
					<tbody>
						<tr ng-repeat="(menup,menuc) in AllMenus">
							<td>{{$index+1}}</td>
							<td>
								<span>
									<input type="checkbox" ng-checked="isChecked({{menup | splitStr:0:','}})" ng-click="updateSelection($event)" value="{{menup | splitStr:0:','}}"  />{{menup | splitStr:1:','}}
								</span>
							</td>
							<td>
								<span ng-repeat="menucd in menuc">
									<input type="checkbox" ng-checked="isChecked({{menucd.id}})" ng-click="updateSelection($event)" value="{{menucd.id}}" />{{menucd.name}}
								</span>
							</td>
						</tr>
					</tbody>
				</table>
				<div class="box-footer">
					<input type="hidden" name="eRoleId" value="{{RoleId}}"/>
					<input type="hidden" name="eRoleIds" value="{{selectedNum}}"/>
					
					<div class="col-sm-6">
						<a href="#/role-index" type="button" class="btn btn-default pull-right">返回</a>
					</div>
					<div class="col-sm-6">
						<button type="submit" class="btn btn-info pull-left" ng-click="subRole()">提交</button>
					</div>
				</div>
			</div>
		</div>
	</div>
</section>

