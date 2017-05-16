
define(['app'], function (app) {
    'use strict';
     app.config(['$routeProvider','$controllerProvider',function($routeProvider,$controllerProvider){
    	
    	 /* 根据url的变化加载内容 */
    	 $routeProvider
        .when('/actmod-index', {
            controller: 'listActMod' ,
            templateUrl: 'process/actModMgr',
            resolve:{
            	url:ctrlRegister(['js/controllers/ActivitiMgrController'])
            }
        });
    	
    	function ctrlRegister (ctrlModule) {
            return function ($q) {
                var defer = $q.defer();
                // 加载该控制器，并将返回值赋给controller，返回值一般是一个控制器函数
                require(ctrlModule, function (controller) {
                    // 将返回值注册为名称为ctrlName的控制器
                    $controllerProvider.register(controller);
                  //这里要执行加载js，我们使用$q的方法阻塞执行
                    //定义了一个方法，这个方法接受一个路径名称或者包含路径名称的数组
                    //使用$q的方式异步执行
                    //这里的话应该是这么理解的，使用require的方式加载文件，通过require的相应callback
                    //响应了$q的执行结果事件resolve
                    defer.resolve();
                });
                // 完成注册
                return defer.promise;
            }
        }
                     
    }]);
});