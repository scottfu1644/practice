
define(['app'], function(app){
    
	   return app.controller('addDepartment', ['$scope','$http','$location', function ($scope, $http, $location) {

		   $scope.addDepartment = function(){
				var add_url = 'department/doDepartmentAdd';
				$http({
			        method: "PUT",
			        url: add_url,
			        data: $scope.department
			    }).then(function (data, status){       	
			    	$location.path('/department-index');
			    })	
			}

	    }]);

	});