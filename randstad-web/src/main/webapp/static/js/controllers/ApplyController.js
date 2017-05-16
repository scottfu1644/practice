
define(['app'], function(app){
	app.register.controller('listApply', ['$scope','$http', function($scope,$http){
		console.log('apply111');
		var listApply_url = "apply/listApply";
		function fetchAllApply(){
			$http.get(listApply_url)
			.then(
				function(data){
				$scope.applies = data.data;
			},
				function(data){
				alert('加载失败！');
			}
			);
		}
		fetchAllApply();
	}]);
    
});