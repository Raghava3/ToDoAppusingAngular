package com.bridgelabz.todoapp.controller;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.todoapp.model.GoogleUserProfile;

@RestController
public class GoogleController {
	@Autowired
	GoogleConnection googleConnection;
	
	@RequestMapping(value="/loginWithGoogle")
public void googleConnection(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		String unid = UUID.randomUUID().toString();
		System.out.println("unid"+unid);
		request.getSession().setAttribute("STATE", unid);
		
		
		String googleLoginURL = googleConnection.getGoogleAuthURL(unid);
		
		System.out.println(googleLoginURL);
		response.sendRedirect(googleLoginURL);
		return;
	}
	

	
	
	// this is  redirect url 
	@RequestMapping(value="googleconnection")
	public void googleRedirectConnnection(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		HttpSession session=request.getSession();
		System.out.println("coming after redirect");
		//getting the attribute what we setted 
		String sessionState=(String) session.getAttribute("STATE");
        //getting the parameter setted by the google 
		//and we check ,if it is same. Then only we can allow further operation   
		String googleState=request.getParameter("state");
		
		System.out.println("sessionstate"+sessionState);
		System.out.println("googlestae"+googleState);
		//if there is problem in session state then we will redirect again to google 
		if(sessionState==null|| !sessionState.equals(googleState))
		{
			System.out.println("coming inside session state");
			response.sendRedirect("loginWithGoogle");
             return;	
          }
		
		String err=request.getParameter("error");
		
		if(err!=null && err.trim().isEmpty())
		{
			System.out.println("coming inside error state");
			response.sendRedirect("Login");
			return;
		}
		
		//getting the authentication code
		String auth =request.getParameter("code");
		
		System.out.println("authcode"+auth);
		
		String accessToken= googleConnection.getAccessToken(auth);
		
		System.out.println("accesstoken"+accessToken);
		
		GoogleUserProfile googleUserProfile=googleConnection.getUserProfile(accessToken);
		System.out.println(googleUserProfile.getId());
		
	}

}
