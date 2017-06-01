myToDo.service("homeService",function($http){
	
this.getNotes = function(){
		return $http({
			url:"http://localhost:8030/todoapp/getNotes",
			method:"GET"
		});
	}

this.addNote=function(toDo){
   	 return $http({
   		 url:"http://localhost:8030/todoapp/addNote",
   		method:"POST",
   		data:toDo
   	 });
   	 } 
   
this.deleteNote = function(id) {
	
	return $http({
		url:"http://localhost:8030/todoapp/deleteNote/"+id,
		method:"POST"
	});
}

this.updateNote = function(toDo) {
	
	return $http({
		url:"http://localhost:8030/todoapp/updateNote",
		method:"POST",
		data:toDo
	});
}

this.copyToDo = function(toDo) {
	return $http({
		url:"http://localhost:8030/todoapp/copyToDo",
		method:"POST",
		data:toDo
	});
}

this.signOut = function() {
	return $http({
		url:"http://localhost:8030/todoapp/signOut",
		method:"POST"
	});
}

this.toToReminder = function(toDo) {
	return $http({
		url:"http://localhost:8030/todoapp/setReminder",
		method:"POST",
		data:toDo
	});
}

this.cancelRemainder = function(toDo) {
	return $http({
		url:"http://localhost:8030/todoapp/cancelRemainder",
		method:"POST",
		data:toDo
	});
}


});