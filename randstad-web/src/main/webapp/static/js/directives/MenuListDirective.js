/**
 * Created by zhazhiho on 2017-03-27.
 */
define(['app'],function (app) {
   app.register.directive('menuTip' ,function(){
       return {
           restrict: 'A',
           link: function(scope, element , attrs){
               var timerMenu;
               timerMenu = setTimeout(function () {
                   function operateFormatter(value, row, index) {
                       return [
                           '<a class="remove" href="javascript:void(0)" title="Remove">',
                           '<i class="glyphicon glyphicon-remove"></i>',
                           '</a>'
                       ].join('');
                   }

                   window.operateEvents = {

                       'click .remove': function (e, value, row, index) {

                           $.ajax({
                               type: 'get',
                               url: 'menu/delCombinateMenu.action',
                               data: {"id": row.id},
                               dataType: 'json',
                               error: function (XMLHttpRequest, textStatus, errorThrown) {

                                   alert("error!");
                               },
                               success: function (data) {
                                   // getParentMenu();

                               }
                           });
                       }
                   };
                   $(element).bootstrapTable({
                       columns: [{
                           field: 'id',
                           title: '菜单编号',
                           align: 'center',
                           valign: 'middle',
                       }, {
                           field: 'name',
                           title: '菜单名称',
                           align: 'center',
                           valign: 'middle',
                           editable: true
                       }, {
                           field: 'url',
                           title: '菜单URL',
                           align: 'center',
                           valign: 'middle',
                           editable: true
                       }, {
                           field: 'parentId',
                           title: '父级菜单',
                           align: 'center',
                           valign: 'middle',
                           editable: true
                       }, {
                           field: 'order',
                           title: '菜单级别',
                           align: 'center',
                           valign: 'middle',
                           editable: true
                       }, {
                           field: 'isView',
                           title: '是否可见',
                           editable: true
                       }, {
                           field: 'code',
                           title: '代码',
                           editable: true
                       }, {
                           field: 'state',
                           title: '操作',
                           align: 'center',
                           valign: 'middle',
                           events: operateEvents,
                           formatter: operateFormatter
                       }],
                       onEditableSave: function (field, row, oldValue, $el) {
                           var menu = row;
                           //alert(menu);
                           $.ajax({
                               type: "post",
                               url: "menu/updateMenu.action",
                               dataType: 'json',
                               data: JSON.stringify(menu),
                               contentType: "application/json;charset=utf-8",
                               success: function (data, status) {
                                   //getParentMenu();
                                   if (status == "success") {
                                       alert("编辑成功");
                                   }
                               },
                               error: function () {
                                   alert("Error");
                               },
                               complete: function () {

                               }

                           });
                       },
                       filter: true,
                       onExpandRow: function (index, row, $detail) {
                           var InitSubTable = function (index, row, $detail) {
                               var parentid = row.id;
                               var cur_table = $detail.html('<table></table>').find('table');
                               $(cur_table).bootstrapTable({
                                   onEditableSave: function (field, row, oldValue, $el) {
                                       var menu = row;
                                       //alert(menu);
                                       $.ajax({
                                           type: "post",
                                           url: "menu/updateMenu.action",
                                           dataType: 'json',
                                           data: JSON.stringify(menu),
                                           contentType: "application/json;charset=utf-8",
                                           success: function (data, status) {
                                               //getParentMenu();
                                               if (status == "success") {
                                                   alert("编辑成功");
                                               }
                                           },
                                           error: function () {
                                               alert("Error");
                                           },
                                           complete: function () {

                                           }

                                       });
                                   },
                                   striped: true,
                                   clickToSelect: true,
                                   detailView: true,
                                   columns: [{
                                       field: 'id',
                                       title: '菜单编号',
                                       align: 'center',
                                       valign: 'middle'
                                   }, {
                                       field: 'name',
                                       title: '菜单名称',
                                       editable: true
                                   }, {
                                       field: 'url',
                                       title: '菜单URL',
                                       editable: true
                                   }, {
                                       field: 'parentId',
                                       title: '父级菜单',
                                       editable: true
                                   }, {
                                       field: 'order',
                                       title: '菜单级别',
                                       editable: true
                                   }, {
                                       field: 'isView',
                                       title: '是否可见',
                                       editable: true
                                   }, {
                                       field: 'code',
                                       title: '代码',
                                       editable: true
                                   }, {
                                       field: 'state',
                                       title: '操作',
                                       align: 'center',
                                       valign: 'middle',
                                       events: operateEvents,
                                       formatter: operateFormatter
                                   }],
                                   //无线循环取子表，直到子表里面没有记录
                                   onExpandRow: function (index, row, $Subdetail) {
                                       InitSubTable(index, row, $Subdetail);
                                   }
                               });
                               $.ajax({
                                   type: 'get',
                                   url: 'menu/getChildrenMenu.action',
                                   data: {"strParentID": parentid},
                                   dataType: 'json',
                                   error: function (XMLHttpRequest, textStatus, errorThrown) {
                                       alert("error!");
                                   },
                                   success: function (data) {
                                       $(cur_table).bootstrapTable('load', data);

                                   }
                               });
                           };
                           InitSubTable(index, row, $detail);
                       }
                   });
                   function getParentMenu(){
                       $.ajax({
                           type: 'get' ,
                           url: 'menu/getParentMenu.action',
                           dataType:'json' ,
                           error: function(XMLHttpRequest , textStatus , errorThrown){
                               alert("error!");
                           },
                           success: function(data){
                               $("#menus").bootstrapTable('load' ,data);
                           }
                       });
                   }
                   getParentMenu();
                   $("#btn_submitForm").click(function(){
                       var menu = {};
                       var name = $("#name").val();
                       var url = $("#url").val();
                       var parentId = $("#parentId").val();
                       var isView =　$("#isView").val();
                       var code = $("#code").val();
                       menu.name =  name ;
                       menu.url = url ;
                       menu.parentId = parentId ;
                       menu.isView = isView ;
                       menu.code = code ;
                       console.log(menu);
                       $.ajax({
                           type: 'post' ,
                           url: 'menu/addMenu.action',
                           dataType:'json' ,
                           //contentType: "application/x-www-form-urlencoded",
                           data: JSON.stringify(menu),
                           contentType: "application/json;charset=utf-8",
                           error: function(XMLHttpRequest , textStatus , errorThrown){
                               alert("error!");
                           },
                           success: function(data){
                               //$('#name').html(' ');
                               //$('#formAdd').reset();
                               $("#name").val('');
                               $("#url").val('');
                               $("#code").val('');
                               $('#myModal').modal('hide');
                               //$("#myModal").on("hidden.bs.model",function(e){$(this).removeData();});
                               getParentMenu();
                           }
                       });
                   });
                   $('#btn_add').click(function(){

                       $.ajax({
                           type: 'get' ,
                           url: 'menu/getAllMenu.action',
                           dataType:'json' ,
                           error: function(XMLHttpRequest , textStatus , errorThrown){
                               alert("error!");
                           },
                           success: function(data){
                               $("#parentId").empty();
                               $("<option value='0'>无</option>").appendTo($("#parentId"));
                               $.each(eval(data), function(i, pro) {
                                   $("<option value='" + pro.id + "'>" + pro.name + "</option>").appendTo($("#parentId"));
                               });
                               $('#myModal').modal();
                           }
                       });

                   });

                   $("#btn_query").click(function(){
                       var menu={};
                       var name = $("#txt_search_menuname").val();
                       var url = $("#txt_search_url").val();
                       menu.name = name ;
                       menu.url = url;
                       $.ajax({
                           type: 'post' ,
                           url: 'menu/queryMenu.action',
                           dataType:'json' ,
                           //contentType: "application/x-www-form-urlencoded",
                           data: JSON.stringify(menu),
                           contentType: "application/json;charset=utf-8",
                           error: function(XMLHttpRequest , textStatus , errorThrown){
                               alert("error!");
                           },
                           success: function(data){
                               //$('#name').html(' ');
                               //$('#formAdd').reset();
                               //$("#txt_search_menuname").val('');
                               //$("#txt_search_url").val('');
                               $("#menus").bootstrapTable('load' , data);
                           }
                       });
                   });
                   clearTimeout(timerMenu);
               }, 10);
           }
       };
   });
});