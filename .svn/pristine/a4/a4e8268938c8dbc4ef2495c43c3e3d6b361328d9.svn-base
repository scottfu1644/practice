require.config({
    urlArgs: "bust=" + (new Date()).getTime(),
    baseUrl : "static",
    paths: {

        
        'jquery': 'AdminLTE/plugins/jQuery/jquery-2.2.3.min',
        'jquery-ui': 'js/jquery-ui.min-1.11.4',
        'bootstrap': 'AdminLTE/bootstrap/js/bootstrap.min',
        'raphael': 'js/raphael-min-2.1.0',
        'morris': 'AdminLTE/plugins/morris/morris.min',
        'sparkline': 'AdminLTE/plugins/sparkline/jquery.sparkline.min',
        'jquery-jvectormap': 'AdminLTE/plugins/jvectormap/jquery-jvectormap-1.2.2.min',
        'jquery-jvectormap-world-mill-en': 'AdminLTE/plugins/jvectormap/jquery-jvectormap-world-mill-en',
        'jquery.knob': 'AdminLTE/plugins/knob/jquery.knob',
        'moment': 'js/moment.min-2.11.2',
        'daterangepicker': 'AdminLTE/plugins/daterangepicker/daterangepicker',
        'bootstrap-datepicker': 'AdminLTE/plugins/datepicker/bootstrap-datepicker',
        'bootstrap-wysihtml5': 'AdminLTE/plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.all.min',
        'slimScroll': 'AdminLTE/plugins/slimScroll/jquery.slimscroll.min',
        'fastclick': 'AdminLTE/plugins/fastclick/fastclick',
        'demo': 'AdminLTE/dist/js/demo',
        'dist_app': 'AdminLTE/dist/js/app.min',

        // angular
        'angular': 'js/angularJS/angular',
        'angular-route': 'js/angularJS/angular-route',
        
        // For page1, the service has mentioned in the service files itself
        'routes' : 'js/routes',
        
        //General
        'app': 'js/controllers/app',

		
		//bootstrap-table
		'bootstrap-table' : 'js/bootstrap-table/bootstrap-table.min',//名字不能用bootstrap-table.min，会报错requirejs读取不了.min属性
        'bootstrap-table-zh-CN' : 'js/bootstrap-table/bootstrap-table-zh-CN',
        'bootstrap-editable' : 'js/bootstrap3-editable/js/bootstrap-editable',
        'bootstrap-table-editable' : 'js/bootstrap3-editable/js/bootstrap-table-editable'

		
    },
   
    // Mention the dependencies
    shim: {
    	'controllers':{
    		deps:['angular']
    	},
    	'jquery':{
    		exports: 'jquery'
    	},
        'angular': {
            exports: 'angular'
        },
        'sparkline': {
            deps: ['jquery'],
            exports: 'sparkline'
        },
        'jquery-jvectormap-world-mill-en': {
            deps: ['jquery'],
            exports: 'jquery-jvectormap-world-mill-en'
        },
        'jquery.knob': {
            deps: ['jquery'],
            exports: 'jquery.knob'
        },
        'bootstrap-datepicker': {
            deps: ['bootstrap'],
            exports: 'bootstrap-datepicker'
        },
        'bootstrap-wysihtml5': {
            deps: ['bootstrap'],
            exports: 'bootstrap-wysihtml5'
        },
        'jquery-jvectormap': {
            deps: ['jquery'],
            exports: 'jquery-jvectormap'
        },
        
        'angular-route': {
            deps: ['angular'] ,
            exports: 'angular-route'
        },
        'jquery-ui': {
            deps: ['jquery'],
            exports: 'jquery-ui'
        },
        'angular_ui_bootstrap': {
            deps: ['angular'],
            exports: 'angular_ui_bootstrap'
        },
       'app': {
            deps:['jquery', 'angular' ,'angular-route',  'bootstrap']
        },
        
        'bootstrap':{
        	deps: ['jquery']
        },
        'dist_app':{
        	deps: ['jquery','jquery-ui']
        },
        'slimScroll':{
        	deps: ['jquery']
        },
    	'bootstrap-table':{
    		deps: ['bootstrap'],
        exports: 'bootstrap-table'
    	},
    	'bootstrap-table-zh-CN':{
    		deps: ['bootstrap','bootstrap-table'],
    	exports: 'bootstrap-table-zh-CN'
    	},
    	'bootstrap-editable':{
    		deps: ['bootstrap'],
    	exports: 'bootstrap-editable'
    	},
    	'bootstrap-table-editable':{
    		deps: ['bootstrap','bootstrap-table','bootstrap-editable'],
    		exports: 'bootstrap-table-editable'
    	}
    	

    }
});
define([
    'jquery',
    'angular',
    'angular-route',
    'app',
    'routes',
    'fastclick',
    'dist_app',
    'slimScroll',
    'sparkline',
    'bootstrap',
    'bootstrap-table',
    'bootstrap-table-zh-CN',
    'bootstrap-editable',
    'bootstrap-table-editable'
], function ($, angular) {
	'use strict';	 
	angular.bootstrap(document, ['myapp']);//引导程序

});
