define(['app'], function (app) {
    return app.controller('updateDepartment', ['$scope', '$routeParams', '$http', '$location', function ($scope, $routeParams, $http, $location) {
        $scope.department = angular.fromJson($routeParams.department);
        $scope.updateDepartment = function () {
            var update_url = 'department/doDepartmentUpd';
            $http({
                method: "PUT",
                url: update_url,
                data: $scope.department
            }).then(function (data, status) {
                $location.path('/department-index');
            })
        }

        $scope.addDep = function (user) {
            var depid = $scope.department.id;
            var addDepUrl = 'users/addDep?userid=' + user.id + '&depid=' + depid;
            $http({
                method: "GET",
                url: addDepUrl,
                // data: {"userid":user.id, "depid":depid}
            }).then(function () {
                $scope.inDepUser.push(user);
                $scope.noDepUser.splice($scope.noDepUser.indexOf(user), 1);
            })
        }

        $scope.removeDep = function (user) {
            var removeDepUrl = 'users/removeDep';
            $http({
                method: "POST",
                url: removeDepUrl,
                data: user
            }).then(function () {
                $scope.inDepUser.splice($scope.inDepUser.indexOf(user), 1);
                $scope.noDepUser.push(user);
            })
        }

        var noDepUserUrl = 'users/selectNoneDep';
        $http({
            method: "GET",
            url: noDepUserUrl
        }).then(function (res) {
            $scope.noDepUser = res.data;
        })

        var inDepUserUrl = 'users/selectByDepId?depid=' + $scope.department.id;
        console.log($scope.department.id);
        $http({
            method: "GET",
            url: inDepUserUrl
        }).then(function (res) {
            $scope.inDepUser = res.data;
        })
    }]);
});