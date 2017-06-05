myToDo.service("signUpValidationService",function(){
this.signUpValidation=function() {
	
		$("#Name_error_message").hide();
        $("#MobileNumber_error_message").hide();
        $("#email_error_message").hide();
        $("#password_error_message").hide();
        $("#reg").hide();
        
        var error_name=false;
        var error_mobilenumber=false;
        var error_email=false;
        var error_password=false;
        
       console.log("coming inside the signupjs")
        $("#reg").focusout(function(){
        	check_username();
        })
        
		
       $("#form_Name").focusout(function(){
       	check_username();
       		
       });
      $("#form_MobileNumber").focusout(function(){
       	check_mobilenumber();
       		
       });
      $("#form_email").focusout(function(){
      		check_email();
      		
      });
      
      $("#form_password").focusout(function(){
      		check_password();
      		
      });
     
     
       function check_username()
       {
    	   console.log("comig inside the username")
    	   var username_length=$("#form_Name").val().length;
    	   if(username_length<5||username_length>20)
    		   {
    		   $("#Name_error_message").html("atleast 5 character	");
    		   $("#Name_error_message").show();
    		   error_name=true;
    		   }
    	   else
    		   {
    		   $("#Name_error_message").hide();
    		   }
       }
       
       
       function check_mobilenumber()
       {
    	   var Mobile_pattern=new RegExp(/^([0-9]{10})$/);
    	   if(Mobile_pattern.test($("#form_MobileNumber").val()))
    		   {
    		   $("#MobileNumber_error_message").hide();
    		   }
    	   else
    		   {
    		   $("#MobileNumber_error_message").html("it should be 10 numbers");
    		   $("#MobileNumber_error_message").show();
    		   error_mobilenumber=true;
    		  }
       } 
       
       
       function check_email()
       {
    	   var email_pattern=new RegExp(/^[+a-zA-Z0-9._-]+@[+a-zA-Z0-9._-]+\.[a-zA-Z]{2,4}$/i);
    	   if(email_pattern.test($("#form_email").val()))
    		   {
    		   $("#email_error_message").hide();
    		   }
    	   else
    		   {
    		   $("#email_error_message").html("invalid.");
    		   $("#email_error_message").show();
    		   error_email=true;
    		  }
       } 
       
       function check_password()
       {
    	   var password_pattern=new RegExp(/^[a-zA-Z0-9]{7}/);
    	   if(password_pattern.test($("#form_password").val()))
    		   {
    		   $("#password_error_message").hide();
    		   }
    	   else
    		   {
    		   $("#password_error_message").html("atleast 7 character");
    		   $("#password_error_message").show();
    		   error_password=true;
    		  }
       }
       
       $("input").keyup(function(){
      	 $("#reg").hide();

      var a= $("#form_Name").val().length;
      var b= $("#form_MobileNumber").val().length;
      var c= $("#form_email").val().length;
      var d= $("#form_password").val().length;
      
      if(a>=5&&b==10&&c>5&&d>=7)
    	  {
    	 $("#reg").show();
    	  }
       });
       
       
}

});

