define([ 'app' ], function(app) {
	
	app.register.controller('addRole', ['$scope', '$http', '$location', function($scope, $http, $location){
		$scope.addRole = function() {
			var add_url = 'role/doRoleAdd';

			$http({
				method : "PUT",
				url : add_url,
				data : $scope.role
			}).then(function(data, status) {
				$location.path('/role-index');
			})
		}
	}]);
	
	app.register.controller('deleteRole', ['$scope', '$http', '$location', '$routeParams', function($scope, $http, $location, $routeParams){
		console.log($routeParams.id);
		var del_url = 'role/doRoleDel';
		$scope.deleteRole = function() {
			$http({
				method : "POST",
				url : del_url,
				data : $routeParams.id
			}).then(function(data, status) {
				$location.path('/role-index');
			})
		}
	}]);
	
	app.register.controller('detailCtrl', ['$scope', '$routeParams', function($scope, $routeParams){
		$scope.role = angular.fromJson($routeParams.role);
	}])
	
	app.register.controller('listRole', ['$scope', '$http', function($scope, $http){
		$scope.count = 0;
		// 变量
		$scope.p_current = 1;
		$scope.p_all_page = 0;
		$scope.pages = [];
		$scope.selectedNum = [ 10, 25, 50, 100 ];
		$scope.pageNums = [ 1 ];
        $scope.pageNum = 1;
        $scope.dataNum = $scope.selectedNum[0];
		// 获取总的记录数
		var AllCount = function() {
			$http.get("role/getPageCount")
					.then(function success(data) {
						$scope.count = data.data;
					}, function error(data) {
						alert('分页异常！');
					});

		}
		// 获取数据
		var _get = function(page, size, callback) {
			var url="role/getRolePage?page=" + page + "&size=" + size;
			$http.get(url)
				.then(
					function success(res) {

						$scope.p_all_page = Math.ceil($scope.count / size);
						$scope.role = res.data;
						$scope.p_current = page;
						$scope.start = ($scope.p_current - 1) * size + 1;
						if (page == $scope.p_all_page) {
							$scope.end = $scope.start + $scope.count
									- (page - 1) * size - 1;
						} else {
							$scope.end = $scope.start + size - 1;
						}

						// 清空pageNums数组
						$scope.pageNums.length = 0;
						for (var i = 0; i < $scope.p_all_page; i++) {
							$scope.pageNums.push(i+1);
						}
						reloadPno();
						//callback();
					}, function error(res) {
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

		// // 监听下拉框显示数据，加载表单数据 ，n为下拉框的选中值
		// $scope.$watch('dataNum', function(n) {
		// 	// $scope.pageNum = 1 ; //初始化跳转页面下拉框
		// 	_get(1, n, function() {
        //
		// 	});
		// });
		// // 跳转页面
        //
		// $scope.$watch('pageNum', function(n) {
		// 	$scope.load_page(n);
        //
		// });
        //
		// // 单选按钮选中
		// $scope.select = function(id) {
		// 	alert(id);
		// }
		// 首页
		$scope.p_index = function() {
			$scope.load_page(1);
		}
		// 上一页
		$scope.p_pre = function() {
			$scope.load_page($scope.p_current - 1);
		}
		// 下一页
		$scope.p_next = function() {
			$scope.load_page($scope.p_current + 1);
		}
		// 尾页
		$scope.p_last = function() {
			$scope.load_page($scope.p_all_page);
		}
		// 加载某一页
		$scope.load_page = function(page) {
			_get(page, $scope.dataNum, function() {
			});
		};
		// 初始化页码
		var reloadPno = function() {
			var disLen = 0;

			if ($scope.p_all_page < 5) {
				disLen = $scope.p_all_page;
			} else {
				disLen = 5;
			}

			$scope.pages = calculateIndexes($scope.p_current,
					$scope.p_all_page, disLen);
		};
		// 分页算法
		var calculateIndexes = function(current, length, displayLength) {
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
	
	app.register.controller('updateRole', ['$scope', '$routeParams', '$http', '$location', function($scope, $routeParams, $http, $location){
		$scope.role = angular.fromJson($routeParams.role);
		$scope.updateRole = function() {
			var update_url = 'role/doRoleUpd';
			$http({
				method : "PUT",
				url : update_url,
				data : $scope.role
			}).then(function(data, status) {
				$location.path('/role-index');
			})
		}
	}]);
	
	app.register.controller('roleMenuEdit', ['$scope', '$routeParams', '$http', '$location', function($scope, $routeParams, $http,$location){
		$scope.RoleId = $routeParams.roleId;
		$scope.selected = false;
		$scope.selectedNum = [];
		$scope.editStatu = "";
		
		$http({
	       method: "GET",
	       url: 'role/menuByRole?roleId='+$scope.RoleId,
		}).then(function (data, status){
			
			$scope.Menus = data.data.menus;
			$scope.AllMenus = data.data.allMenus;
			$scope.MenusParent = data.data.menusParent;
			
			angular.forEach($scope.Menus, function(data){
				$scope.selectedNum.push(data.id);
			});
			
		});
		
		$scope.subRole = function(){
			var add_url = 'role/editRole?eRoleId='+$scope.RoleId+"&eRoleIds="+$scope.selectedNum;
			$http.get(add_url).then(
				function success(data){
					$scope.editStatu = "保存成功";
					jQuery.globalPlugin.jsonMenus();
				},
				function error(data){
					console.log("=="+data)
					$scope.editStatu = "保存失败";
				}
			);
		}
		
		$scope.isChecked = function(id){
			$scope.selected = false;
			angular.forEach($scope.Menus, function(data){
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
	    };$scope.RoleId = $routeParams.roleId;
		$scope.selected = false;
		$scope.selectedNum = [];
		$scope.editStatu = "";
		
		$http({
	       method: "GET",
	       url: 'role/menuByRole?roleId='+$scope.RoleId,
		}).then(function (data, status){
			
			$scope.Menus = data.data.menus;
			$scope.AllMenus = data.data.allMenus;
			$scope.MenusParent = data.data.menusParent;
			
			angular.forEach($scope.Menus, function(data){
				$scope.selectedNum.push(data.id);
			});
			
		});
		
		$scope.subRole = function(){
			var add_url = 'role/editRole?eRoleId='+$scope.RoleId+"&eRoleIds="+$scope.selectedNum;
			$http.get(add_url).then(
				function success(data){
					$scope.editStatu = "保存成功";
					jQuery.globalPlugin.jsonMenus();
				},
				function error(data){
					console.log("=="+data)
					$scope.editStatu = "保存失败";
				}
			);
		}
		
		$scope.isChecked = function(id){
			$scope.selected = false;
			angular.forEach($scope.Menus, function(data){
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
	}]);
	
	app.register.controller('listRoleMenu', ['$scope', '$routeParams', '$http', '$location', function($scope, $routeParams, $http, $location){
		console.log("listRoleMenu");
		$scope.RoleId = $routeParams.roleId;
		$scope.count = 0;
		// 变量
		$scope.p_current = 1;
		$scope.p_all_page = 0;
		$scope.pages = [];
		$scope.selectedNum = [ 10, 25, 50, 100 ];
		$scope.pageNums = [ 1 ];
		$scope.pageNum = 1;
		$scope.dataNum = $scope.selectedNum[0];

		// 获取总的记录数
		var AllCount = function() {
			$http.get("role/menuByRoleCount?roleId=" + $scope.RoleId).then(
					function success(data) {
						$scope.count = data.data;
					}, function error(data) {
						alert('分页异常！');
					});

		}
		// 获取数据
		var _get = function(page, size, callback) {
			var url = "role/menuByRolePage?roleId=" + $scope.RoleId + "&page="
					+ page + "&size=" + size;
			$http.get(url).then(
					function success(res) {

						$scope.p_all_page = Math.ceil($scope.count / size);
						$scope.roleMenu = res.data;
						$scope.p_current = page;
						$scope.start = ($scope.p_current - 1) * size + 1;
						if (page == $scope.p_all_page) {
							$scope.end = $scope.start + $scope.count
									- (page - 1) * size - 1;
						} else {
							$scope.end = $scope.start + size - 1;
						}

						// 清空pageNums数组
						$scope.pageNums.length = 0;
						for (var i = 0; i < $scope.p_all_page; i++) {
							$scope.pageNums.push(i + 1);
						}
						// console.log($scope.pageNums);
						reloadPno();
						// callback();
					}, function error(res) {
						alert('加载失败！');
					});
		}

		// load方法
		$scope.load_page = function(page) {
			_get(page, $scope.dataNum, function() {
			});
		};

		// 初始化
		AllCount();
		$scope.load_page(1);

		// 监听页面显示条数
		$scope.$watch('dataNum', function(newValue, oldValue) {
			// 防止第一次就调用load方法
			if (newValue === oldValue)
				return;
			_get(1, newValue, function() {

			});
		});

		// 点击页数
		$scope.$watch('pageNum', function(newValue, oldValue) {
			// 防止第一次就调用load方法
			if (newValue === oldValue)
				return;
			$scope.load_page(newValue);
		});

		// 首页
		$scope.p_index = function() {
			$scope.load_page(1);
		}
		// 上一页
		$scope.p_pre = function() {
			$scope.load_page($scope.p_current - 1);
		}
		// 下一页
		$scope.p_next = function() {
			$scope.load_page($scope.p_current + 1);
		}
		// 尾页
		$scope.p_last = function() {
			$scope.load_page($scope.p_all_page);
		}

		// 初始化页码
		var reloadPno = function() {
			var disLen = 0;

			if ($scope.p_all_page < 5) {
				disLen = $scope.p_all_page;
			} else {
				disLen = 5;
			}

			$scope.pages = calculateIndexes($scope.p_current,
					$scope.p_all_page, disLen);
		};

		// 分页算法
		var calculateIndexes = function(current, length, displayLength) {
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