myToDo.controller("TrashController", function($scope, $state,homeService)
		{
	
	
	console.log("coming inside the  trash controllerasfdasdfdfs");
	var trashToDoList = [];
	
	
	var getToDoHtOb = homeService.getTrashNotes();
	getToDoHtOb.then(function(data) {
		
		console.log("comming data "+data.status);
		$scope.user = data.data.user;
		
		if( data.status == 200 ) {
			console.log(data.data.todo);
			$scope.trashToDoList = data.data.trashtodo;
		}
		else{
			
			$scope.trashToDoList = null;
		}
		
	}).catch( function(error) {
		$scope.isList = true;
		$state.go('Login');
		console.log(error);
	});
	
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

	
	
	$scope.trashToHome=function()
	{
		console.log("coming inside trashtogohome");
		$state.go("Home");
	}
	
	
	
	
	
	$scope.move = function() {
	
		console.log("comin iside the move trash");
		if($scope.visible){
			console.log("comin iside the move visible");

			$scope.move = {"margin-left":"15%","transition":"0.6s ease"}
		}
		else {
			$scope.move = {"margin-left":"0px","transition":"0.6s ease"}
		}
		
	}
		}
		);