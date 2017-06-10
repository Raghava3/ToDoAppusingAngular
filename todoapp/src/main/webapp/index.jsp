<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!--   <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
 --> 
 <link rel="icon" href="images/keep.png" type="image/png" sizes="16x16">
<link rel="stylesheet" href="bower_components/bootstrap/dist/css/bootstrap.min.css">
<link rel="stylesheet" href="bower_components/angular-ui-bootstrap/dist/ui-bootstrap-csp.css">
<link rel="stylesheet" href="bower_components/angular-bootstrap-datetimepicker/src/css/datetimepicker.css">
 
<script src="bower_components/jquery/dist/jquery.min.js" type="text/javascript"></script>
	<script src="bower_components/bootstrap/dist/js/bootstrap.min.js" type="text/javascript"></script>
	<script src="bower_components/moment/moment.js"></script>
	<script src="bower_components/angular/angular.min.js" type="text/javascript"></script>
	<script src="bower_components/angular-ui-router/release/angular-ui-router.min.js" type="text/javascript"></script>
<!-- 	<script src="bower_components/angular-ui-bootstrap/src/modal/modal.js" type="text/javascript"></script>
 -->	<script src="bower_components/angular-ui-bootstrap/dist/ui-bootstrap-tpls.js" type="text/javascript"></script>

	<script src="bower_components/angular-sanitize/angular-sanitize.min.js" type="text/javascript"></script>
	<script src="bower_components/angular-animate/angular-animate.min.js" type="text/javascript"></script>
	<!-- <script src="bower_components/angular-ui-bootstrap/dist/ui-bootstrap.js" type="text/javascript"></script> -->
	<script src="bower_components/angular-bootstrap-datetimepicker/src/js/datetimepicker.js" type="text/javascript"></script>
	<script src="bower_components/angular-bootstrap-datetimepicker/src/js/datetimepicker.templates.js"></script>
	<script src="js/app.js" type="text/javascript"></script>
	
	
	
	
    
	
<!-- this is for css -->
<link rel="stylesheet" href="css/Signup.css">
<link rel="stylesheet" href="css/Login.css">
<link rel="stylesheet" href="css/Home.css">
<link rel="stylesheet" href="css/Trash.css">
<link rel="stylesheet" href="css/Archive.css">
 
<title>ToDoApp</title>
</head>

<body id="Body">
     <div data-ng-app="todoapp">
     <div class="container-fluid">
	<ui-view></ui-view>
	</div>
</body>


<!-- this is for script -->
<script src="js/controller/SignUpController.js" type="text/javascript"></script>
<script src="js/controller/HomeController.js" type="text/javascript"></script>
<script src="js/controller/LoginController.js" type="text/javascript"></script>
<script src="js/controller/LogoutController.js" type="text/javascript"></script>
<script src="js/controller/TrashController.js" type="text/javascript"></script>


<script src="js/service/toDoHomeService.js" type="text/javascript"></script>
<script src="js/validation/toDoHome.js" type="text/javascript"></script>

<script src="js/Signup.js" type="text/javascript"></script> 

</html>