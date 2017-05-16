<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../common/common.jsp"%> 
<div class="box">
	<div class="box-header">
		<h3 class="box-title">流程模板管理</h3>		
		<div class="row">
			<div class="col-sm-12">
				<div class="pagination pagination-sm no-margin pull-right">					
					<a href="javascript:void(0);" type="button" class="btn btn-info" ng-click="createMod()">添加</a>
					<!-- <a href="#/" type="button" class="btn btn-danger">导出</a>
					<a href="#/" type="button" class="btn btn-warning">导入</a> -->
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
						<th>模板名称</th>
						<th>模板KEY</th>
						<th>状态</th>
						<th>创建时间</th>
						<th>操作</th>							
					</tr>
					</thead>
					<tbody>
						<tr ng-repeat="model in actModel">
							<td>{{model.id}}</td>
							<td>{{model.name}}</td>
							<td>{{model.key}}</td>
							<td></td>
							<td>{{model.createTime | date:'yyyy-MM-dd HH:mm:ss'}}</td>
							<td>
								<a href="process-editor/modeler.html?modelId={{model.id}}" type="button" target="_blank" class="btn btn-info">设计</a>
                   			</td>
						</tr>
					</tbody>
				</table>
				</div>
			</div>
		</div>
		<div class="row">
				<div class="col-sm-5">
					<div class="dataTables_info" id="example1_info" role="status" aria-live="polite">
									第 {{start}} - {{end}} 条记录  ，共{{count}}条记录---- 显示 
				<select ng-model="dataNum" ng-options="x for x in selectedNum" ng-init="dataNum=selectedNum[0]">				
                </select>条记录
					</div>
				</div>
				<div class='col-sm-2'>
					
	    第 
	    		<select style="width:35px;height:24px;margin:0px 2px;" 
	    				ng-model="pageNum" 
	    				ng-init="pageNum=pageNums[0]"
	    				ng-options="x for x in pageNums">

	    		</select>
	     页  
				</div>
				<div class="col-sm-5">
					<ul class="pagination pull-right" style="margin: 8px;" >  
	        		<li ng-class="{true:'disabled'}[p_current==1]" ng-if="count>0">
	             		<a href="javascript:void(0);" ng-click="p_index()">首页</a>
	        		</li> 
	        		<li ng-if="p_current!=1">
	             		<a href="javascript:void(0);" ng-click="p_pre()">上一页</a>
	        		</li>   
	        		<li ng-repeat="page in pages" ng-class="{true:'active'}[p_current==page]">
	             		<a href="javascript:void(0);" ng-click="load_page(page)">{{ page }}</a>
	        		</li>  
	        		<li ng-if="p_current!=p_all_page && count>0">
	             		<a href="javascript:void(0);" ng-click="p_next()">下一页</a>
	        		</li>   
	       			 <li ng-class="{true:'disabled'}[p_current==p_all_page]" ng-if="count>0">
	             		<a href="javascript:void(0);" ng-click="p_last()">尾页</a>
	        		</li>  
				</ul>
				</div>
			</div>
	</div>
</div>