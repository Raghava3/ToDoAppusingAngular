myToDo.service("homeService",function($http){
	
this.getNotes = function(){
		return $http({
			url:"http://localhost:8030/todoapp/getNotes",
			method:"GET"
		});
	}
});