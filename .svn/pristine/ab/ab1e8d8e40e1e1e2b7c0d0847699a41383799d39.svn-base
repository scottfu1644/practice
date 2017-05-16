<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../common/common.jsp"%> 
	<div class="box">
		<div class="box-header">
			<h3 class="box-title">申请信息列表</h3>		
			<div class="row">
				<div class="col-sm-12">
					<div class="pagination pagination-sm no-margin pull-right">					
						<a href="#/apply-add" type="button" class="btn btn-info">添加</a>
						<a href="#/" type="button" class="btn btn-danger">导出</a>
						<a href="#/" type="button" class="btn btn-warning">导入</a>						
					</div>					
				</div>							
			</div>						
		</div>
		<!-- /.box-header -->
		<div class="box-body">
			<div class="row">
				<div class="col-sm-12">
					<div>
					<table id="example2" class="table table-bordered table-striped">
						<thead>
						<tr>
							<th>ID</th>
							<th>申请人</th>
							<th>申请部门</th>
							<th>申请描述</th>
							<th>申请状态</th>
							<th>申请原因</th>
							<th>用途</th>
							<th>是否急用</th>							
						</tr>
						</thead>
						<tbody>
							<tr ng-repeat="apply in applies">
								<td>{{apply.id}}</td>
								<td>{{apply.userId}}</td>
								<td>{{apply.departmentId}}</td>
								<td>{{apply.applyDes}}</td>
								<td>{{apply.status}}</td>
								<td>{{apply.orgid}}</td>
								<td>{{apply.purposeId}}</td>
								<td>{{apply.needId}}</td>
								<td>
									<a href="#/apply-update/{{apply}}" type="button" class="btn btn-info">编辑</a>
									<a href="#/apply-delete/{{apply.id}}" type="button" class="btn btn-info">删除</a>
                    			</td>
							</tr>
						</tbody>
					</table>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-sm-5">
					<div class="dataTables_info" id="example2_info" role="status" aria-live="polite">显示 1 - 10，共计  50 条记录</div>
				</div>
				<div class="col-sm-7">
					<ul class="pagination pagination-sm no-margin pull-right">
						<li><a href="#">&laquo;</a></li>
						<li><a href="#">1</a></li>
						<li><a href="#">2</a></li>
						<li><a href="#">3</a></li>
                		<li><a href="#">&raquo;</a></li>
					</ul>
				</div>
			</div>
		</div>
	</div>
	
	<script>
	
	</script>