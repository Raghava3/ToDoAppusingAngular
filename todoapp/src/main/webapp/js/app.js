var myToDo = angular.module('todoapp',  ['ui.router']);
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