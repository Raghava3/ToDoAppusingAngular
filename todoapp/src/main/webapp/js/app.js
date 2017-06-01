var myToDo = angular.module('todoapp',  ['ui.router', 'ngSanitize', 'ngAnimate', 'ui.bootstrap', 'ui.bootstrap.datetimepicker']);
myToDo.config(function ($stateProvider, $urlRouterProvider) {
  
  $stateProvider
 
  .state('Login',{
	    url:"/Login",
	    templateUrl:"html/Login.html",
	    controller:"LoginController"
	  })
  
	  
  
  
  .state('SignUp',{
	  url:"/SignUp",
    templateUrl:"html/Signup.html",
    controller:"SignUpController"
  })
  
  
     .state('Home',{
        url:"/Home",
       templateUrl:"html/Home.html",
	  })
	  
  $urlRouterProvider.otherwise('/Login');
  
  

});

myToDo.directive('newcontenteditable', [function() {
    return {
        require: '?ngModel',
        scope: {
        },
        link: function(scope, element, attrs, ctrl) {
        	
            // view -> model (when div gets blur update the view value of the model)
            element.bind('blur', function() {
                scope.$apply(function() {
                    ctrl.$setViewValue(element.html());
                });
            });
            
            // model -> view
            ctrl.$render = function() {
                element.html(ctrl.$viewValue);
            };
            
            // load init value from DOM
            //ctrl.$render();
            
            scope.$on('$destroy', function() {
                element.unbind('blur');
                element.unbind('paste');
                element.unbind('focus');
            });
        }
    };
}]);
