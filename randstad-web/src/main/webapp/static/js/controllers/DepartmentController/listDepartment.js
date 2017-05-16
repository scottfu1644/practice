
define(['app'], function(app){
    
	   return app.controller('listDepartment', ['$scope','$http', function ($scope ,$http ) {

		   $scope.count = 0;   
		    //变量  
		    $scope.p_current = 1;  
		    $scope.p_all_page =0;  
		    $scope.pages = []; 		
		    $scope.selectedNum = [10,25,50,100];
		    $scope.pageNums = [1];
		    //用$patent是因为ng-repeat下的ng-model的作用域跟外部的作用域没有关系，所以得在ng-model中设置全局作用域
		    $scope.$parent.pageNum = 1;
		    //获取总的记录数
		    var AllCount = function(){
		    	$http.get("department/getPageCount")
		    	.then(function success(data){
		    		$scope.count = data.data ;
		    	} ,function error(data){
		    		alert('分页异常！');
		    	});
		    	
		    }
		    AllCount();
		    //获取数据  
		    var _get = function(page,size,callback){ 
		    	console.log(1);
		        $http.get("department/getDepartmentPage?page="+page+"&size="+size)
		        .then(function success(res){  
		        	
		            	$scope.p_all_page =Math.ceil($scope.count/size); 
		                $scope.departments=res.data;  
		                $scope.p_current = page;
		                $scope.start = ($scope.p_current - 1) * size + 1;
		                if(page == $scope.p_all_page){
		                	 $scope.end = $scope.start + $scope.count - (page - 1)*size - 1;
		                }else{
		                	$scope.end = $scope.start + size - 1;
		                }
		               
		                //清空pageNums数组
		                $scope.pageNums.length = 0 ;		               
		                for(var i = 0 ; i < $scope.p_all_page ; i++){
		                	$scope.pageNums.push(i+1) ;
		                }
		                console.log($scope.pageNums);
		                reloadPno();  
//		                callback();  
		            },
		            function error(res){
		            	alert('加载失败！');
		            }
		        );   
		    } 
		   //监听下拉框显示数据，加载表单数据 ，n为下拉框的选中值 

		    $scope.$watch('dataNum' ,function(n){
		    	$scope.$parent.pageNum = 1;	//初始化跳转页面下拉框 
		    	  //console.log(2);
		    	 _get(1,n,function(){  
				        
				    });
			   });
		   //跳转页面
		    
		    $scope.$watch('$parent.pageNum' ,function(n){ 
		    		 //console.log('n=' + n);
			    	 $scope.load_page(n);
		    	 
			   });
		    	    
  
		    //首页  
		    $scope.p_index = function(){  
		        $scope.load_page(1);  
		    } 
		    //上一页
		    $scope.p_pre = function(){  
		        $scope.load_page($scope.p_current-1);
		    } 
		    //下一页
		    $scope.p_next = function(){  
		        $scope.load_page($scope.p_current+1); 
		    } 
		    //尾页  
		    $scope.p_last = function(){  
		        $scope.load_page($scope.p_all_page);  
		    }  
		    //加载某一页  
		    $scope.load_page = function(page){  
		        _get(page,$scope.dataNum,function(){ });  
		    };  
		    //初始化页码  
		    var reloadPno = function(){  
		    	 var disLen = 0;
		    		    
		    	 if( $scope.p_all_page < 5){
		    		 disLen = $scope.p_all_page ;
		    	 }else{
		    		 disLen = 5;
		    	 }
		    
		          $scope.pages=calculateIndexes($scope.p_current,$scope.p_all_page,disLen);  
		        };  
		//分页算法  
		var calculateIndexes = function (current, length, displayLength) {  
		   var indexes = [];  
		   var start = Math.round(current - displayLength / 2);  
		   var end = Math.round(current + displayLength / 2);  
		    if (start <= 1) {  
		        start = 1;  
		        end = start + displayLength - 1;  
		       if (end >= length - 1) {  
		           end = length - 1;  
		        }  
		    }  
		    if (end >= length - 1) {  
		       end = length;  
		        start = end - displayLength + 1;  
		       if (start <= 1) {  
		           start = 1;  
		        }  
		    }  
		    for (var i = start; i <= end; i++) {  
		        indexes.push(i);  
		    }  
		    return indexes;  
		  };  

			//删除
			  $scope.deleteDepartment = function(departmentId){
				  $http({
			           method: "POST",
			           url: 'department/doDepartmentDel',
			           data: departmentId
			       }).then(function (data, status){
			    	   AllCount();
			    	   $scope.load_page($scope.p_current);
			       })
			  };

	    }]);

	});

