// lazyload config

define(['app'], function (app) {
    /**
   * jQuery plugin config use ui-jq directive , config the js and css files that required
   * key: function name of the jQuery plugin
   * value: array of the css js file located
   */
 app.constant('JQ_CONFIG', {
    
	  menuTable:      [   
		  					'static/js/bootstrap-table/bootstrap-table.min.js' ,
							'static/js/bootstrap-table/bootstrap-table-zh-CN.js',
							'static/js/bootstrap3-editable/js/bootstrap-editable.js',
							'static/js/bootstrap3-editable/js/bootstrap-table-editable.js',
							'static/js/controllers/MenuManageView.js'
							]
  }

  )
  // oclazyload config
  app.config(['$ocLazyLoadProvider', function($ocLazyLoadProvider) {
      // We configure ocLazyLoad to use the lib script.js as the async loader
      $ocLazyLoadProvider.config({
          debug:  false,
          events: true,
          modules: [
          
              {
                  name: 'menuTable',
                  files: [
                	  'static/js/bootstrap-table/bootstrap-table.min.js' ,
						'static/js/bootstrap-table/bootstrap-table-zh-CN.js',
						'static/js/bootstrap3-editable/js/bootstrap-editable.js',
						'static/js/bootstrap3-editable/js/bootstrap-table-editable.js',
						'static/js/controllers/MenuManageView.js'
                  ]
              }
                        
          ]
      });
  }]);
});