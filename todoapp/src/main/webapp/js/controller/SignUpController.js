myToDo.controller("SignUpController",function ($scope,  $state, signUpService ,signUpValidationService) {	
	
	signUpValidationService.signUpValidation();
	
     	$scope.signUp = function () {
		console.log("sdfsadf");
		var user = {};
		user.fullName=$scope.fullName;
		user.mobileNumber=$scope.mobileNumber;
		user.email = $scope.email;
		user.password = $scope.password;
		var httpObje = signUpService.signUp(user);
		console.log(user);
		httpObje.then(function (data) {
			console.log(data);
			console.log(data.status);
			if( data.status == "200" ){
					$state.go('Login');
			}
			else
			{
				$scope.emailError = data.data.emailError;
				var message = data.data.message;
				$scope.errorMessage = message;
			}
		})
		.catch( function(error){
			console.log(error);
		});
	}	
});


myToDo.service("signUpService",function ($http) {
	this.signUp = function(user){ 
		return $http({
			url:"http://localhost:8030/todoapp/signUp",
			method:"post",
			data:user
		});
	}
});