package com.bridgelabz.todoapp.controller;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.todoapp.model.FaceBookProfile;
import com.bridgelabz.todoapp.model.User;
import com.bridgelabz.todoapp.service.serviceinterface.UserSerInter;

@RestController
public class FaceBookController {

	@Autowired
	FacebookConnection connection;
	@Autowired
	UserSerInter userSerInter;
	
	//first controller comes here
	@RequestMapping(value="loginWithFacebook")
	public void loginWithFaceBook(HttpServletRequest request,HttpServletResponse response) throws IOException
	{
      //it will genrate some uid it will pass to facebook by using setattribute
		String unid =UUID.randomUUID().toString();
		request.getSession().setAttribute("STATE", unid);
		//calling the  url method to get url 
        String fbloginURL=connection.getFbAuthURL(unid);
        System.out.println(fbloginURL);
        response.sendRedirect(fbloginURL);
		return;
	}
	
	@RequestMapping(value="fbconnection")
	public void fromFbRedirect(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		System.out.println("comming after redirect");
		String sessionState=(String) request.getSession().getAttribute("STATE");
		String fbState=request.getParameter("state");
		
		if(sessionState==null ||  !sessionState.equals(fbState))
		{
			System.out.println("state is not matching");
			response.sendRedirect("loginWithFacebook");
			return ;
			
		}
		String error = request.getParameter("error");
		
		if(error!=null&&error.trim().isEmpty())
		{
			System.out.println("getting error");
			response.sendRedirect("Login");
			return;
		}
		
		String authCode=request.getParameter("code");
		String accessToken=connection.getAccessToken(authCode);
		FaceBookProfile profile=connection.getUserProfile(accessToken);
		User user=userSerInter.getUserByEmail(profile.getEmail());
		
		
		if(user==null){
			user = new User();
			user.setFullName(profile.getName());
			user.setEmail(profile.getEmail());
			user.setPassword("");
			userSerInter.registration(user);
			System.out.println("coming inside the user ");
		}
		HttpSession session = request.getSession();
		session.setAttribute("user", user);
		System.out.println(profile.toString());
		response.sendRedirect("http://localhost:8030/todoapp/#!/Home");
		
	}
	
	
	
	
	
		/*$scope.facebookshare=function(todo){
console.log("facebook share")
FB.init({
appId : '1639081702785828',
status: true,
xfbml : true
});
FB.ui({
          method: 'share_open_graph',
          action_type: 'og.shares',
          action_properties: JSON.stringify({
              object : {
                 // your url to share
                 'og:title': todo.title,
                 'og:description': todo.description,
                 /*'og:image': 'http://example.com/link/to/your/image.jpg'
              }
          })
          },
          // callback
          function(response) {
          if (response && !response.error_message) {
              // then get post content
              alert('successfully posted. Status id : '+response.post_id);
          } else {
              alert('Something went error.');
          }
      });
      
};*/
	
	
	
}
	


