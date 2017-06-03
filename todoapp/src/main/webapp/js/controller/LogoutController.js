myToDo.controller("logoutController", function($scope, $state,homeService){
	
	this.signOut = function() {
			
			var signoutObj = homeService.signOut();
			
			signoutObj.then( function(data) {
				if( data.status == 200 ){
					$state.go('Login');
				}
				else 
				{
					$state.go('SignUp');
			     }
			});
		
	}
});