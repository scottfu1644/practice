define([
    'angular'
], function (angular) {
    'use strict';

    var app =  angular.module('myapp', ['ngRoute']);
    
    app.config(function($locationProvider,$controllerProvider,$compileProvider,$filterProvider,$provide){
    	//这里要特别注意下，AngularJs自1.6版本后对地址做了特别处理,浏览器地址“/"变”%2F”,导致路由不能跳转了,所以在Angular中声明用旧有方式
    	$locationProvider.hashPrefix('');
        //requirejs 动态加载时需要 app注册事件 controller:业务控制，directive 公共函数 例如分页,filter 另起了一个JS
        app.register = {
            controller : $controllerProvider.register,
            directive: $compileProvider.directive,
            service: $provide.service
        };
    });
    
    //index切割后的数组下标;tail切割标记
    app.filter('splitStr', function () {
		  return function (value, index, tail) {
			  if (!value) return '';
			  index = parseInt(index, 10);
			  var newValueArr = value.split(tail);
			  return newValueArr[index];
		  };
	});
    
    return app;
});

