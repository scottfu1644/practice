
define(['app'], function(app){
    
	   return app.controller('deleteDepartment', ['$scope','$http','$location', '$routeParams', function ($scope, $http, $location, $routeParams) {

		   console.log("delete department");
		   console.log($routeParams.id);
		   var del_url = 'department/doDepartmentDel';
		   $scope.deleteDepartment = function(){
		   	$http({
		           method: "POST",
		           url: del_url,
		           data: $routeParams.id
		       }).then(function (data, status){
		       	$location.path('/department-index');
		       })	
		   	
		   }

	    }]);

	});