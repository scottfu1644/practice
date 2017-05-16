<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../common/common.jsp"%> 
<html>
<head>


    	<script type="text/javascript" 
    			src ="static/js/bootstrap-table/bootstrap-table.min.js"></script>
		<script type="text/javascript" 
				src ="static/js/bootstrap-table/bootstrap-table-zh-CN.js"></script>
		<script type="text/javascript"
				src ="static/js/bootstrap3-editable/js/bootstrap-editable.js"></script>
		<script type="text/javascript"
				src ="static/js/bootstrap3-editable/js/bootstrap-table-editable.js"></script>
		<script type="text/javascript" 
				src="static/js/bootstrap-table/MenuManageView.js"></script>
		<script type="text/javascript" 
				src="static/js/jquerySession.js"></script> 	
</head>
<body>
		<div class="panel-body" style="padding-bottom:0px;">
        <div class="panel panel-default">
            <div class="panel-heading">查找条件</div>
            <div class="panel-body">
                <form id="formSearch" class="form-horizontal">
                    <div class="form-group" style="margin-top:15px">
                        <label class="control-label col-sm-1" for="txt_search_menuname">菜单名称</label>
                        <div class="col-sm-3">
                            <input type="text" class="form-control" id="txt_search_menuname">
                        </div>
                        <label class="control-label col-sm-1" for="txt_search_url">菜单URL</label>
                        <div class="col-sm-3">
                            <input type="text" class="form-control" id="txt_search_url">
                        </div>
                        <div class="col-sm-4" style="text-align:left;">
                            <button type="button" style="margin-left:50px" id="btn_query" class="btn btn-primary">查询</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>       

        <div id="toolbar" class="btn-group" style="float: left;margin-top: 8px;">
            <button id="btn_add" type="button" class="btn btn-default"
            		   data-toggle="myModal"  data-dismiss="modal">
                <span class="glyphicon glyphicon-plus" aria-hidden="true" ></span>增加
            </button>
        </div> 
        <table id="menus" 
        	data-toolbar="#table_toolbar" data-search="true"
        	data-show-refresh="true" data-show-toggle="true"
        	data-show-columns="true" data-show-export="true"
        	data-detail-view="true"  data-minium-count-columns="2"
			data-pagination="true" data-striped="true"
        	data-page-list="[15,25,50,100]"  
        	data-page-size="10" data-show-footer="false"
        	data-side-pagination="client" 
        	data-sort-order="desc"  data-toolbar-align="left">
        </table>
    </div>

<!-- 模态框（Modal） -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
   aria-labelledby="myModalLabel" aria-hidden="true">
   <div class="modal-dialog">
      <div class="modal-content">
         <div class="modal-header">
            <button type="button" class="close"
               data-dismiss="modal" aria-hidden="true">
                  &times;
            </button>
            <h4 class="modal-title" id="myModalLabel">
               添加菜单
            </h4>
         </div>
         <div class="modal-body" >
           <form  name="formAdd" id="formAdd">
           		<table style="width: 100%;">
           			<tr>
           				<td style="width: 40%">
           					<div class="form-group" >
							<label for="menuname">菜单名称</label>
							<input type="text" name="name" id="name" class="form-control" />
							</div>
           				</td>
           				<td style="width: 40%;">
           				<div class="form-group" style="margin-left: 20px;">
							<label for="isView">是否可见</label>
							  <select class="form-control" id="isView">
							      <option value="1">是</option>
							      <option value="2">否</option>
							   </select>
							</div>
           				</td>
           			</tr>
           			<tr>
           				         				
           				<!-- <td>
           					<div class="form-group" >
								<label for="order">菜单级别</label>
									<select class="form-control" name="menuOrder" id="menuOrder" 
											onchange="alert( $('#menuOrder').val());">
								      <option value="1"  selected>1</option>
								      <option value="2">2</option>
								      <option value="3">3</option>
								    </select>
							</div>
           				</td> -->
           				<td>
           					<div class="form-group" >
								<label for="parentId">父级菜单</label>
								<select class="form-control" name="parentId" id="parentId">
							    
							    </select>
							</div>
           				</td>
           			</tr>
           			<tr>
           			<td colspan="2">
           					<div class="form-group" >
							<label for="url">菜单URL</label>
							<input type="text"  id="url" class="form-control" />
							</div>
							</td>
           			</tr>
           			<tr>
           			<td colspan="2">
						<div class="form-group" >
							<label for="code">代码</label>
							<textarea class="form-control" rows="3" id="code"></textarea>
						</div>
					</td>
           			</tr>
 
           		</table>

			</form>
         </div>
         <div class="modal-footer">
            <button type="button" class="btn btn-default"
               data-dismiss="modal">取消
            </button>
            <button type="button" class="btn btn-primary" id="btn_submitForm" data-miss="modal" >
            确定
            </button>
           </div>
            </div>
            </div>
            </div>
       </body>
   </html>