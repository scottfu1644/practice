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
		<div class="col-md-6">
			<div class="box box-info">
				<div class="box-header with-border">
					<h3 class="box-title">基本资料</h3>&nbsp;&nbsp;&nbsp;{{UserMSG}}
				</div>
				
				<!-- /.box-header -->
				<!-- form start -->
				<form class="form-horizontal">
					<div class="box-body">
						<div class="form-group">
							<label for="inputEmail3" class="col-sm-3 control-label">用户EID</label>
							<div class="col-sm-8">
								<input name="userid" class="form-control" disabled ng-model="user.userid">
							</div>
						</div>
						<div class="form-group">
							<label for="inputEmail3" class="col-sm-3 control-label">名称</label>
						    <div class="col-sm-8">
								<input name="username" class="form-control" id="inputEmail3" ng-model="user.username">
						    </div>
						</div>
						<div class="form-group">
							<label for="inputEmail3" class="col-sm-3 control-label">部门</label>
						    <div class="col-sm-8">
								<select class="form-control" name="depid" ng-model="user.depid" ng-options="udep.id as udep.depName for udep in UDep">
                    			</select>
						    </div>
						</div>
						<div class="form-group">
							<label for="inputEmail3" class="col-sm-3 control-label">性别</label>
						    <div class="col-sm-8">
								<select class="form-control" name="sex" ng-model="user.sex" ng-options="usex.id as usex.name for usex in USex">
                    			</select>
						    </div>
						</div>
						<div class="form-group">
							<label for="inputEmail3" class="col-sm-3 control-label">E-mail</label>
						    <div class="col-sm-8">
								<input name="email" class="form-control" id="inputEmail3" ng-model="user.email">
						    </div>
						</div>
						<div class="form-group">
							<label for="inputEmail3" class="col-sm-3 control-label">状态</label>
						    <div class="col-sm-8">
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
							<button type="submit" class="btn btn-info pull-left" ng-click="updateUser()">提交</button>
						</div>
					</div>
				<!-- /.box-footer -->
				</form>
			</div>
		</div>
		
		
		
		<div class="col-md-6">
			<div class="box box-info">
			
				<div class="box-header with-border">
					<h3 class="box-title">角色分配</h3>&nbsp;&nbsp;&nbsp;{{RoleMSG}}
				</div>
				
				<div class="box-body">
					<div class="direct-chat-messages" style="height: 294px;">
						<div class="input-group" ng-repeat="role in AllRoles">
							<span class="input-group-addon">
								<input type="checkbox" ng-checked="isChecked({{role.id}})" ng-click="updateSelection($event)" value="{{role.id}}">
							</span>
							<input type="test" class="form-control" disabled value="{{role.rolename}}"/>
						</div>
					</div>
				</div>
				
				<div class="box-footer">
					<div class="col-sm-6">
						<a href="#/user-index" type="button" class="btn btn-default pull-right">返回</a>
					</div>
					<div class="col-sm-6">
						<button type="submit" class="btn btn-info pull-left" ng-click="editUserRole()">提交</button>
					</div>
				</div>
				
			</div>
		</div>
		
	</div>
</section>

