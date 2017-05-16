define([ 'app' ], function(app) {

	app.register.controller('addUser',['$scope','$http','$location', function($scope, $http, $location){
		$scope.USex = [ { id : '0', name : '请选择...' }, { id : '1', name : '男' }, { id : '2', name : '女' } ];
		$scope.UStatus = [ { id : 0, name : '请选择...' }, { id : 1, name : '正常' }, { id : 2, name : '未激活' } ];
		$scope.user = {
			'sex' : '0',
			'depid' : 1,
			'status' : 0
		};

		$http({
			method : "GET",
			url : 'department/listDepartment',
		}).then(function(data, status) {
			$scope.UDep = data.data;
		})

		$scope.addUser = function() {
			var add_url = 'user/doUserAdd';
			$http({
				method : "PUT",
				url : add_url,
				data : $scope.user
			}).then(function(data, status) {
				$location.path('/user-index');
			})
		}
    }]);
	
	app.register.controller('deleteUser',['$scope','$http','$location', '$routeParams', function($scope, $routeParams){
		console.log("delete1111");
		console.log($routeParams.id);
		var del_url = 'user/doUserDel';
		$scope.deleteUser = function() {
			$http({
				method : "POST",
				url : del_url,
				data : $routeParams.id
			}).then(function(data, status) {
				$location.path('/user-index');
			})

		}
	}]);
	
	app.register.controller('detailCtrl',['$scope', '$routeParams', function($scope, $routeParams){
		$scope.role = angular.fromJson($routeParams.role);
	}]);
	
	app.register.controller('updateUser',['$scope', '$routeParams', '$http', '$location', function($scope, $routeParams, $http, $location){
		console.log("update1111");
		$scope.user = angular.fromJson($routeParams.user);
		$scope.USex = [{ id: '0', name: '请选择...' },{ id: '1', name: '男' }, { id: '2', name: '女' }];
		$scope.UStatus = [{ id: 0, name: '请选择...' },{ id: 1, name: '正常' }, { id: 2, name: '未激活' }];
		$scope.UserMSG = "";
		$scope.RoleMSG = "";
		
		$http({
			method: "GET",
			url: 'department/listDepartment',
		}).then(function (data){
			$scope.UDep = data.data;
		})
	       
		$http({
			method: "GET",
			url: 'user/userRoles?userId='+$scope.user.id,
		}).then(function (data){
			$scope.UserRoles = data.data.UserRoles;
			$scope.AllRoles = data.data.AllRoles;
			$scope.selected = false;
			$scope.selectedNum = [];
	    	   
			angular.forEach($scope.UserRoles, function(data){
				$scope.selectedNum.push(data.id);
			});
	    	   
			$scope.isChecked = function(id){
				$scope.selected = false;
				angular.forEach($scope.UserRoles, function(data){
					if(data.id == id){
						$scope.selected = true;
					}
				});
				return $scope.selected;
			};
	   	      
			$scope.updateSelection = function($event){  
				var checkbox = $event.target;
				var checked = checkbox.checked;
				var id = $event.target.defaultValue;
				id = parseInt(id, 10);
				if(checked){  
					$scope.selectedNum.push(id);
				}else{  
					var idx = $scope.selectedNum.indexOf(id) ;  
					$scope.selectedNum.splice(idx,1) ;  
				}
			};
		});
		
		$scope.editUserRole = function() {
			var add_url = 'user/editUserRole?userId='+$scope.user.id+'&uRoleIds='+$scope.selectedNum;
			$http.get(add_url).then(
				function success(data){
					console.log("---"+data);
					$scope.RoleMSG = "修改成功";
					
					if(jQuery.globalPlugin.UserId() == $scope.user.id){
						console.log("user eq");
						jQuery.globalPlugin.jsonMenus();
					}
					
				},
				function error(data){
					console.log("=="+data)
					$scope.RoleMSG = "修改失败";
				}
			);
		}
		   
		$scope.updateUser = function(){
			console.log("update user---");
			var update_url = 'user/doUserUpd';
			$http({
				method: "PUT",
				url: update_url,
				data: $scope.user
			}).then(function (data, status){
				if(data.statusText == "OK"){
					console.log("---ok");
					$scope.UserMSG = "修改成功"
				}else{
					$scope.UserMSG = "修改失败"
					console.log(data+"---"+status);
				}
			})       
		}
	}]);
	
	app.register.controller('listUser', ['$scope', '$http', function($scope, $http){
		console.log("userindex test......");
		$scope.count = 0;   
		//变量  
		$scope.p_current = 1;  
		$scope.p_all_page =0;  
		$scope.pages = []; 		
		$scope.selectedNum = [10,25,50,100];
		$scope.pageNums = [1];
		$scope.pageNum = 1;
		$scope.dataNum = $scope.selectedNum[0];
		
		
		$scope.USex = [{ id: '0', name: '请选择...' },{ id: '1', name: '男' }, { id: '2', name: '女' }];
		$scope.UStatus = [{ id: 0, name: '请选择...' },{ id: 1, name: '正常' }, { id: 2, name: '未激活' }];
		   	
		   	
	    //获取总的记录数
	    var AllCount = function(){
	    	$http.get("user/getPageCount")
	    	.then(function success(data){
	    		$scope.count = data.data ;
	    	} ,function error(data){
	    		alert('分页异常！');
	    	});
	    	
	    }
	    //获取数据  
	    var _get = function(page,size,callback){ 
	    	var url = "user/getUserPage?page="+page+"&size="+size;
			$http.get(url)
	        .then(function success(res){  
	        	
	            	$scope.p_all_page =Math.ceil($scope.count/size); 
	                $scope.users=res.data;  
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
//	                console.log($scope.pageNums);
	                reloadPno();  
//	                callback();  
	            },
	            function error(res){
	            	alert('加载失败！');
	            }
	        );   
	    } 
	    
	    //load方法
	    $scope.load_page = function(page){  
	        _get(page,$scope.dataNum,function(){ });  
	    };  
	    
	    //初始化
	    AllCount();
	    $scope.load_page(1);
	    
	    //监听页面显示条数 
	    $scope.$watch('dataNum', function(newValue, oldValue) {
			// 防止第一次就调用load方法
			if (newValue === oldValue)
				return;
			_get(1, newValue, function() {

			});
		});
	    
	    //点击页数
		$scope.$watch('pageNum', function(newValue, oldValue) {
			// 防止第一次就调用load方法
			if (newValue === oldValue)
				return;
			$scope.load_page(newValue);
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
	}]);


});