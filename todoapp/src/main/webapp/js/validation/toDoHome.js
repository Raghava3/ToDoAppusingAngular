myToDo.service("homeJavaScript",function(){
	this.homescript=function() {
    $(document).ready(function() {
	$('b').on('click', function(e) {

		if ($(this).hasClass('grid')) {
			$('#container div').removeClass('list').addClass('grid');
		} else if ($(this).hasClass('list')) {

			$('#container div').removeClass('grid').addClass('list');
		}
	});

	
	
	$('#take').on('click', function(event) {

		$("#newNote").show();

	});
	

	
	
	$(document).on('click','#done', function(event) {
		if (event.target.id = "take") {
			return;
		}
		$("#newNote").css("display", "none");
		$("#up").css("display", "none");

	});
	
	
	

	
	$('#sea').on('click', function(event) {

		$("#sea").css("background-color", "white");

	});
	
	
	
	
	
	
	$('#full').on('click', function(event) {

		$("#sea").css("background-color", "#f5b400");
		
		if(event.target.id=="note")
			{
				return;
			}
		
		if( event.target.id=="take" || event.target.id=="Title" || event.target.id=="Note" || event.target.id=="i" )
		{
			return;
		}
		else {
			$("#newNote").css("display", "none");
		}
		

	});
	
});



/*java Script*/


function openNav() {
	document.getElementById("mySidenav").style.width = "250px";
	document.getElementById("nav").style.marginLeft = "250px";
}
function closeNav() {
	document.getElementById("mySidenav").style.width = "0px";
	document.getElementById("nav").style.marginLeft = "0px";
}






function showOption() {
	document.getElementById("show").style.visibility = "visible";
}

function hideOption() {
	document.getElementById("show").style.visibility = "visible";
}







function popUp(title, note, rem) {

	document.getElementById("pop").innerHTML = ("<b>"+title + "</b><br><br>" + note+ "" + rem);
	document.getElementById("pop").style.visibility = "visible";
	document.getElementById("main").style.opacity = 0.3;

	
	setTimeout(function() {
		document.getElementById("pop").style.visibility = "hidden";
		document.getElementById("main").style.opacity = 1;
	}, 1200);

}



function upDate(title, note, rem, id) {
	
	
	console.log(title);
	document.getElementById("updt").innerHTML = ("<b><form action=\"updateNote\" method=\"post\"><input style=\" width: 150px; type=\"text\" value="+title+" name=\"title\">" + "</b><br><br>" +
			"<input style=\" width: 150px;\" type=\"text\" value="+note+" name=\"note\">" + "<br>" + rem +"<input style=\"visibility: hidden;\" type=\"number\" value="+id+" name=\"id\">"+"<br> <input id=\"up\" type=\"submit\" value=\"update\"></form>");
	document.getElementById("updt").style.visibility = "visible";
	
	
}

function design() {
    document.getElementById("onNote").style.boxShadow = "5px 5px 10px  black";
}

function designOut() {
	document.getElementById("onNote").style.boxShadow = "none";
}

function searchDiv()
{
	console.log("inside seach div");
	  document.getElementById("search").style.backgroundColor="white";
}

function serchDivOriginal()
{
	console.log("inside oseach div");
	  document.getElementById("search").style.backgroundColor="#CD8300";
}


function takeDesign() {
	document.getElementById("take").style.boxShadow = "5px 5px 10px  black";
    document.getElementById("newNote").style.boxShadow = "5px 5px 10px  black";
}

function takeDesignOut() {
    document.getElementById("newNote").style.boxShadow = "none";
    document.getElementById("take").style.boxShadow = "none";
}
}}
);