<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../common/common.jsp"%> 
<!-- Content Header (Page header) -->
<!-- Main content -->
<section class="content-header">
<h1>
        General Form Elements
        <small>Preview</small>
      </h1>
</section>
<section class="content">
	<!-- Horizontal Form -->
	<div class="row">
		<div class="col-md-12">
          <div class="box box-info">
            <div class="box-header with-border">
              <h3 class="box-title">Horizontal Form</h3>
            </div>

            <!-- /.box-header -->
            <!-- form start -->
            <form class="form-horizontal">
              <div class="box-body">
              	<div class="form-group">
                  <label for="inputEmail3" class="col-sm-4 control-label">ID</label>
                  <div class="col-sm-4">
                    <input name="id" class="form-control" id="inputEmail3" ng-model="apply.id">
                  </div>
                </div>
                <div class="form-group">
                  <label for="inputEmail3" class="col-sm-4 control-label">申请人</label>
                  <div class="col-sm-4">
                    <input name="nickname" class="form-control" id="inputEmail3" ng-model="apply.creatername">
                  </div>
                </div>
                <div class="form-group">
                  <label for="inputEmail3" class="col-sm-4 control-label">申请时间</label>
                  <div class="col-sm-4">
                    <input name="state" class="form-control" id="inputEmail3" ng-model="apply.createrdate">
                  </div>
                </div>
                <div class="form-group">
                  <label for="inputEmail3" class="col-sm-4 control-label">申请部门</label>
                  <div class="col-sm-4">
                    <input name="id" class="form-control" id="inputEmail3" ng-model="apply.department">
                  </div>
                </div>
                <div class="form-group">
                  <label for="inputEmail3" class="col-sm-4 control-label">申请原因</label>
                  <div class="col-sm-4">
                    <input name="nickname" class="form-control" id="inputEmail3" ng-model="apply.reason">
                  </div>
                </div>
                <div class="form-group">
                  <label for="inputEmail3" class="col-sm-4 control-label">用途</label>
                  <div class="col-sm-4">
                    <input name="state" class="form-control" id="inputEmail3" ng-model="apply.purpose">
                  </div>
                </div>
                <div class="form-group">
                  <label for="inputEmail3" class="col-sm-4 control-label">是否急用</label>
                  <div class="col-sm-4">
                    <input name="state" class="form-control" id="inputEmail3" ng-model="apply.purpose">
                  </div>
                </div>
              </div>
              <br><br>
              <!-- /.box-body -->
              <div class="box-footer">
              <div class="col-sm-5">
              <!-- /. -->
              </div>
              <div class="col-sm-2">
                <button type="submit" class="btn btn-default pull-left">Cancel</button>
                <button type="submit" class="btn btn-info pull-right" ng-click="updateApply()">submit</button>
                </div>
              </div>
              <!-- /.box-footer -->
            </form>
          </div>
		</div>
	</div>
</section>

