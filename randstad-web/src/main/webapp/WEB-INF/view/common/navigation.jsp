<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../common/common.jsp" %>
<script src='${ctx}/static/AdminLTE/plugins/jQuery/jquery-2.2.3.min.js'></script>
<script type="text/javascript">

jQuery.globalPlugin = {
	showDIV : function(title,content,footer){
		$("#modal-bodyku").html(content);
		$("#myModalLabel").html(title);
		$("#modal-footerq").html(footer);
		$('#myModal').modal('show');
	},
	UserId : function(){
		return $("#sign_userId").val();
	},
	jsonMenus : function(){
		//console.log("123123123123");
		$.ajax({ 
			url: '${ctx}/user/menuJSON',
			type: "GET",
			dataType: 'json', 
			success : function(data){
				var menusData = data;
				$("#menus_data").empty();
				if(menusData.length > 0){
					for(var i = 0; i < menusData.length; i++ ){
						var parent = menusData[i].attr;
						$("#menus_data").append(function(index,html){
							return "<li id='"+parent.id+"' class='treeview'>"+
									"<a href='#'>"+
									"<i class='fa fa-files-o'></i>"+
									"<span>"+parent.name+"</span>"+
									"<span class='pull-right-container'>"+
						              "<i class='fa fa-angle-left pull-right'></i>"+
						            "</span>"+
						            "</a>"+
									"</li>";
						})
						if(menusData[i].children.length > 0){
							var pul = $("#"+parent.id).append("<ul id='c"+parent.id+"' class='treeview-menu'></ul>");
							for(var j = 0; j < menusData[i].children.length; j++){
								var pChildren = menusData[i].children[j].attr;
								$("#c"+parent.id).append(function(index,html){
									return "<li><a href='#"+pChildren.href+"'><i class='fa fa-circle-o'></i>"+pChildren.name+"</a></li>";
								});
							}
						}
					}
				}		
			}
		});
	}
}

//jQuery.globalPlugin.jsonMenus();

</script>
<!-- sidebar: style can be found in sidebar.less -->
    <section class="sidebar">
      <!-- Sidebar user panel -->
      <div class="user-panel">
        <div class="pull-left image">
          <img src="${ctx}/static/AdminLTE/dist/img/user2-160x160.jpg" class="img-circle" alt="User Image">
        </div>
        <div class="pull-left info">
          <p>${userInfo.userName}<input type="hidden" id="sign_userId" value="${userInfo.id}"/></p>
          <a href="#"><i class="fa fa-circle text-success"></i> 在线</a>
        </div>
      </div>
      <!-- sidebar menu: : style can be found in sidebar.less -->
      <ul id="menus_data" class="sidebar-menu">
		<li class="treeview">
          <a href="#">
            <i class="fa fa-files-o"></i>
            <span>工作流</span>
            <span class="pull-right-container">
              <i class="fa fa-angle-left pull-right"></i>
            </span>
          </a>
          <ul class="treeview-menu">
            <li><a href="#/actmod-index"><i class="fa fa-circle-o"></i>流程模板管理</a></li>
          </ul>
        </li>
      </ul>
    </section>
    <!-- /.sidebar -->
    