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
	console.log("coming insige logjs")
	return $http({
		url:"http://localhost:8030/todoapp/signOut",
		method:"GET"
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

this.setColor=function(toDo)
{
	return $http({
		url:"http://localhost:8030/todoapp/setColor",
		method:"POST",
		data:toDo
	});
	
}


this.moveToTrash=function(toDo)
{
	console.log("coming inside service trash");
    return $http({
    	url:"http://localhost:8030/todoapp/moveToTrash",
    	method:"POST",
       data:toDo
    })	
}

this.getTrashNotes = function(){
	return $http({
		url:"http://localhost:8030/todoapp/getTrashNotes",
		method:"GET"
	});
}
this.pinUp = function(toDo) {
	return $http({
		url:"http://localhost:8030/todoapp/updateNote",
		method:"POST",
		data:toDo
	});
	
}
this.unPinUp = function(toDo) {
	console.log("coming inside the service");
	return $http({
		url:"http://localhost:8030/todoapp/updateNote",
		method:"POST",
		data:toDo
	});
}
this.deleteNotePermanently= function(id) {
	console.log("coming inside the service");
	return $http({
		url:"http://localhost:8030/todoapp/deleteNotePermanently/"+id,
		method:"POST"
	
	});
}

this.restore=function(toDo)
{
	toDo.id=toDo.id;
	toDo.title=toDo.title;
	toDo.note=toDo.note;
	toDo.reminder=toDo.reminder;
	toDo.color=toDo.color;
	toDo.pinup=false;
	toDo.archive=false;
	 return $http({
   		 url:"http://localhost:8030/todoapp/updateNote",
   		method:"POST"
   	 });
}




});