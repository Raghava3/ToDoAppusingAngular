myToDo.controller("HomeController", function($scope, $state	,$uibModal,homeService,homeJavaScript)
		{
	
	homeJavaScript.homescript();

	var toDoList = [];
	var user = [];
	var getToDoHtOb = homeService.getNotes();
	getToDoHtOb.then(function(data) {
		
		console.log("comming data "+data.status);
		$scope.user = data.data.user;
		
		if( data.status == 200 ) {
			console.log(data.data.todo);
			$scope.toDoList = data.data.todo;
		}
		else{
			
			$scope.toDoList = null;
		}
		
	}).catch( function(error) {
		$scope.isList = true;
		$state.go('Login');
		console.log(error);
	});
		
	

		
	
	
	
	
	
	
	$scope.isList = true;
	
	this.addNote = function() {
		
		$scope.done = $scope.done ? false :true;
		$scope.IsVisible = $scope.IsVisible ? false :true;
		
	/*if($scope.toDo.title != null && $scope.toDo.note != null || ($scope.toDo.title != "" && $scope.toDo.note != ""))	*/
		if($scope.toDo.title == null && $scope.toDo.note == null || ($scope.toDo.title == "" && $scope.toDo.note == ""))
			{
			console.log("comming inside null ");
			$scope.toDo.title = "Empty";
			$scope.toDo.note = "Empty";
			}
			var addToDoObj = homeService.addNote($scope.toDo);
			addToDoObj.then( function(data) {
				console.log("coming inside add method");
				if( data.status == 200 ) {
					$scope.toDoList.push(data.data.todo);
					console.log(data.data.todo);
					$scope.toDo.title=null;
					$scope.toDo.note=null;
					$scope.toDo.color=null;
					$scope.toDo.remainder=null;
					
				}
				
			}).catch( function(error) {
				console.log(error);
				$state.go('Login');
			});
		
	}
	
	

	this.deleteNote = function(toDo, index) 
	{
		var trashToDo={};
		//this is the changes //
		trashToDo.title=toDo.title;
		trashToDo.note=toDo.note;
		trashToDo.reminder=toDo.reminder;
		trashToDo.color=toDo.color;	
		console.log(trashToDo);
		var moveToTrash=homeService.moveToTrash(trashToDo);
	
		moveToTrash.then(function(data)
		{
		
			if(data.status==200)
				{
				
				var delToDoObj = homeService.deleteNote(toDo.id);
				
				delToDoObj.then(function(data) 
					{
					
					  if( data.status == 200 ) 
					   {
						if(index > -1)
						{
							$scope.toDoList.splice(index, 1);
						}
					   }
					
				}).catch( function(error) 
						{
					$state.go('Login');
				        }
				);
		}}).catch( function(error) 
				{
			$state.go('Login');
		        }
		);
		}
	
	
	
	this.showHide = function() {
		$scope.IsVisible = $scope.done ? true :true;
		$scope.done = $scope.IsVisible ? true :false;
	}
	
	
	
	this.popUp = function(toDo, index) {
		
		var modal = $uibModal.open({
		     templateUrl: "html/popUp.html",
		     arialLabelledBy: "modal-title-bottom",
		     arialLabelledBy: "modal-body-bottom",
		     arialLabelledBy: "modal-footer-bottom",
		     size:'sm',
		     controller:function( $uibModalInstance ){
		    	 this.id = toDo.id;
		    	 this.title = toDo.title;
		    	 this.note = toDo.note;
		    	 this.remainder = toDo.remainder;
		    	 this.upDated = toDo.upDated;
		    	 this.color = toDo.color;
		    	 var $ctrl = this;
		    	 
		    	 
		    	 
		    	this.ok = function () {
		    		console.log("update ok");
					$uibModalInstance.close({id:$ctrl.id, title:$ctrl.title, note:$ctrl.note, remainder:$ctrl.remainder, color:$ctrl.color});
					
				};
				
				this.cancel = function () {
					console.log("update cancle");
				    $uibModalInstance.dismiss('cancel');
				};
		     },
		     
		     controllerAs :"$ctrl"
		});
		
		
		 modal.result.catch(function(error){
	        	console.log("error::",error); 
	        	
	         }).then(function(data){
	        	if(data) {
	        		console.log(data);
	        		$scope.toDoList.splice(index, 1, data);
	        		$scope.updateNote(data);
	        }
	    })
	    
	}
	
	
	$scope.updateNote = function( toDo ) {
		
		toDo.upDated = new Date();
		
		var updToDoObj = homeService.updateNote(toDo);
		
		updToDoObj.then(function(data) {
			if(data.status == 200) {
				
			}
		}).catch(function(error) {
			$state.go('signIn');
		});
	}

	
	this.unarchive=function(toDo,index)
	{
    toDo.archive=false;
	var archToDoObj = homeService.updateNote(toDo);
	archToDoObj.then(function(data)
	{
		if(data.status==200)
		{
			$scope.toDo.splice(index,1,data);
		}
	});
    
	}
	
	
	this.move = function() {
		if($scope.visible){
			console.log("coming inside the move visi")
			$scope.move = {"margin-left":"15%","transition":"0.6s ease"}
		}
		else {
			$scope.move = {"margin-left":"0px","transition":"0.6s ease"}
		}
		
	}
	
	
	
	this.makeCopy = function(toDo) {
		
		var copy = {};
		
		copy.title = toDo.title;
		copy.note = toDo.note;
		copy.remainder = toDo.remainder;
		copy.color = toDo.color;
		
		console.log(toDo);
		var copyToDoObj = homeService.copyToDo(copy);
		
		copyToDoObj.then (function(data) {
			
			if( data.status == 200 ) {
				$scope.toDoList.push(data.data.todoCopy);navbar
			}
		}).catch( function(error) {
			console.log(error);
			$state.go('Login');
		});
	}
	
	
	
	
	this.toToReminder = function(toDo,index, day) {
		
		console.log("coming toToReminder");
		var today = new Date();
		var httpObjRem ;
		
		if( day == "Today" ) {
			today.setHours(20, 00, 00);
            console.log(today);
            toDo.remainder = today;
            console.log(toDo);
            httpObjRem = homeService.toToReminder(toDo);
		}
		else if( day == "Tomorrow") {
			 var tomorrow = new Date(today);
			tomorrow.setDate(tomorrow.getDate() + 1);
			tomorrow.setHours(08, 00, 00);
            toDo.remainder = tomorrow;
            httpObjRem = homeService.toToReminder(toDo);
		}
		else if( day == "Next-Week") {
			var nextWeek = new Date(today);
			nextWeek.setDate(nextWeek.getDate() + 7)
			nextWeek.setHours(08, 00, 00);
            toDo.remainder = nextWeek;
            httpObjRem = homeService.toToReminder(toDo);
		}
		httpObjRem.then(function(data) {
		    
		    if ( data.data.status == 200 ) {
		        console.log(data);
		       
		    }
		}).catch(function(error) {
		    console.log(error);
		    $state.go('Login');
		})
		
	}
	
	
	this.cancelRemainder = function(todo) {
		console.log(todo.remainder);
		todo.remainder = null;
		console.log(todo.remainder);
		var cancelObj = homeService.cancelRemainder(todo);
		
		cancelObj.then(function(data) {
			
			if( data.status == 200 ) {
			}
		}).catch(function(error) {
			console.log(error);
			$state.go('SignUp');
		})
	}
	
	
	this.setColor = function(todo, color) {
		todo.color = color;
		console.log(todo+" "+color);
		var colorObj = homeService.setColor(todo);
		
		colorObj.then(function(data) {
			
			if( data.status == 200 ) {
			}
		}).catch(function(error) {
			console.log(error);
			$state.go('Login');
		})
	}
	
// this function to bring  shadow around head while scrolling up
	$(window).scroll(function(){
	    if ($(window).scrollTop() >= 30) {
	    	
	       $('#navbar').addClass('shadow-header');
	    }
	    else {
	       $('#navbar').removeClass('shadow-header');
	    }
	});
	
	
	function fbLogoutUser() {
	    FB.getLoginStatus(function(response) {
	    	console.log("coming inside logout");
	        if (response && response.status === 'connected') {
	            FB.logout(function(response) {
	                document.location.reload();
	            });
	        }
	    });
	}

	
	this.trash=function()
	{
		$state.go("Trash");
	}
	
	
	
	
	this.pinUp = function(toDo) {
		toDo.pinup = true;
		var pinObj = homeService.pinUp(toDo);
		console.log("coming inside pinup");
		pinObj.then( function(data) {
			if( data.status == 200 ){
			}
		})
		
	}
	
	this.unPinUp = function(toDo) {
		console.log("coming inside the controller");
		toDo.pinup = false;
		var unpinObj = homeService.unPinUp(toDo);
		
		unpinObj.then( function(data) {
			if( data.status == 200 )
			{
			}
		})
	}
	
	this.archiveNote=function(toDo)
	{
		console.log("coming inside adding to arcive");
		toDo.archive=true;
	 var arcObj=	homeService.pinUp(toDo);
		
	}
	this.unArchiveNote=function(toDo)
	{
		toDo.archive=false;
		var arcObj=	homeService.pinUp(toDo);
		
	}
	
	this.archive=function()
	{
		console.log("coming inside archive");
		$state.go("Archive");
	}
	$scope.archiveToHome=function()
    {
		console.log("coming inside trashtogohome");
		$state.go("Home");
	}

$scope.archiveToTrash=function()
{
	console.log("coming inside trashtogotrash");
	$state.go("Trash");
}


this.createNewLables = function() {
	
	var modal = $uibModal.open({
	     templateUrl: "html/createLables.html",
	    	 
	    	 controller:function( $uibModalInstance ){
		    	
		    	 var $ctrl = this;
		    	this.ok = function () {
		    		console.log("update ok");
					$uibModalInstance.close({id:$ctrl.id, title:$ctrl.title, note:$ctrl.note, remainder:$ctrl.remainder, color:$ctrl.color});
					
				};
				
				this.cancel = function () {
					console.log("update cancle");
				    $uibModalInstance.dismiss('cancel');
				};
		     },
		     
		     controllerAs :"$ctrl"
		});    	 
	    	 
	    	 
   
    
}

this.addLabel=function()
{
	var addToDoObj = homeService.addLabel($scope.label);
    console.log("coming inside the log");
    $createNewLables.ok();
	addToDoObj.then( function(data)
	{
		
	});
	
}




});
