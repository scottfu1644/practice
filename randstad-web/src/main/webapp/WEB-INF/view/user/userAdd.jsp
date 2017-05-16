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
					<h3 class="box-title">编辑</h3>
				</div>
				
				<!-- /.box-header -->
				<!-- form start -->
				<form class="form-horizontal">
					<div class="box-body">
						<div class="form-group">
							<label for="inputEmail3" class="col-sm-4 control-label">用户EID</label>
							<div class="col-sm-4">
								<input name="userid" class="form-control" ng-model="user.userid">
							</div>
						</div>
						<div class="form-group">
							<label for="inputEmail3" class="col-sm-4 control-label">名称</label>
						    <div class="col-sm-4">
								<input name="username" class="form-control" ng-model="user.username">
						    </div>
						</div>
						<div class="form-group">
							<label for="inputEmail3" class="col-sm-4 control-label">部门</label>
						    <div class="col-sm-4">
								<select class="form-control" name="depid" ng-model="user.depid" ng-options="udep.id as udep.depName for udep in UDep">
                    			</select>
						    </div>
						</div>
						<div class="form-group">
							<label for="inputEmail3" class="col-sm-4 control-label">性别</label>
						    <div class="col-sm-4">
								<select class="form-control" name="sex" ng-model="user.sex" ng-options="usex.id as usex.name for usex in USex">
                    			</select>
						    </div>
						</div>
						<div class="form-group">
							<label for="inputEmail3" class="col-sm-4 control-label">昵称</label>
						    <div class="col-sm-4">
								<input name="realname" class="form-control" ng-model="user.realname">
						    </div>
						</div>
						<div class="form-group">
							<label for="inputEmail3" class="col-sm-4 control-label">E-mail</label>
						    <div class="col-sm-4">
								<input name="email" class="form-control" ng-model="user.email">
						    </div>
						</div>
						<div class="form-group">
							<label for="inputEmail3" class="col-sm-4 control-label">状态</label>
						    <div class="col-sm-4">
						    	<select class="form-control" name="status" ng-model="user.status" ng-options="ustatus.id as ustatus.name for ustatus in UStatus">
                    			</select>
						    </div>
						</div>
					</div>
					
					<!-- /.box-body -->
					<div class="box-footer">
						<div class="col-sm-6">
							<a href="#/user-index" type="button" class="btn btn-default pull-right">返回</a>
						</div>
						<div class="col-sm-6">
							<button type="submit" class="btn btn-info pull-left" ng-click="addUser()">提交</button>
						</div>
					</div>
				<!-- /.box-footer -->
				</form>
			</div>
		</div>
	</div>
</section>

