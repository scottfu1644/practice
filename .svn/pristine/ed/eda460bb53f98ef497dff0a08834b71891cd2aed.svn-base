<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../common/common.jsp"%> 
	<div class="box">
		<div class="box-header">
			<h3 class="box-title">申请采购的物品信息列表</h3>		
			<div class="row">
				<div class="col-sm-12">
					<div class="pagination pagination-sm no-margin pull-right">					
						<a href="#/product-add" type="button" class="btn btn-info">添加</a>
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
					<table id="example1" class="table table-bordered table-striped">
						<thead>
						<tr>
							<th>ID</th>
							<th>产品编码</th>
							<th>产品名称</th>
							<th>产品规格</th>
							<th>产品附件A</th>
							<th>产品owner</th>
						</tr>
						</thead>
						<tbody>
							<tr ng-repeat="product in products">
								<td>{{product.id}}</td>
								<td>{{product.proCode }}</td>
								<td>{{product.proName}}</td>
								<td>{{product.proSpec}}</td>
								<td>{{product.proFile}}</td>
								<td>{{product.proOwner}}</td>
								<td>
									<a href="#/product-update/{{product}}" type="button" class="btn btn-info">编辑</a>
									<a href="#/product-delete/{{product.id}}" type="button" class="btn btn-info">删除</a>
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
	    				ng-model="$parent.pageNum" 
	    				ng-init="pageNum=pageNums[0]"
	    				ng-options="x for x in pageNums">

	    		</select>
	     页  
				</div>
				<div class="col-sm-5">
					<ul class="pagination pull-right" style="margin: 8px;" >  
	        		<li ng-class="{true:'disabled'}[p_current==1]">
	             		<a href="javascript:void(0);" ng-click="p_index()">首页</a>
	        		</li> 
	        		<li ng-if="p_current!=1">
	             		<a href="javascript:void(0);" ng-click="p_pre()">上一页</a>
	        		</li>   
	        		<li ng-repeat="page in pages" ng-class="{true:'active'}[p_current==page]">
	             		<a href="javascript:void(0);" ng-click="load_page(page)">{{ page }}</a>
	        		</li>  
	        		<li ng-if="p_current!=p_all_page">
	             		<a href="javascript:void(0);" ng-click="p_next()">下一页</a>
	        		</li>   
	       			 <li ng-class="{true:'disabled'}[p_current==p_all_page]">
	             		<a href="javascript:void(0);" ng-click="p_last()">尾页</a>
	        		</li>  
				</ul>
				</div>
			</div>
		</div>
	</div>
	
