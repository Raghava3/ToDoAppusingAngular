myToDo.service("homeService",function($http){
	
this.getNotes = function(){
		return $http({
			url:"http://localhost:8030/todoapp/getNotes",
			method:"GET"
		});
	}

this.addNotes=function(){
   	 return $hhtp({
   		 url:"http://localhost:8030/todoapp/addNote",
   		 method
   		 
   	 });} 
   
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


});