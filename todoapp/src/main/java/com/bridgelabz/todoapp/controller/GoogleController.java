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
import com.bridgelabz.todoapp.model.User;
import com.bridgelabz.todoapp.service.serviceinterface.UserSerInter;

@RestController
public class GoogleController {
	@Autowired
	GoogleConnection googleConnection;
	@Autowired
	private UserSerInter userSerInter;
	
	
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
		
		String sessionState=(String) session.getAttribute("STATE");
		String googleState=request.getParameter("state");
		
		System.out.println("sessionstate"+sessionState);
		System.out.println("googlestae"+googleState);
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
		
		String auth =request.getParameter("code");
		String accessToken= googleConnection.getAccessToken(auth);
		GoogleUserProfile profile= googleConnection.getUserProfile(accessToken);
		
		User user = userSerInter.getUserByEmail(profile.getEmails().get(0).getValue());
		
		if(user==null){
			user = new User();
			user.setFullName(profile.getDisplayName());
			user.setEmail(profile.getEmails().get(0).getValue());
			user.setPassword("");
			user.setImage(profile.getImage().getUrl());
			userSerInter.registration(user);
		}
		session.setAttribute("user", user);
		response.sendRedirect("http://localhost:8030/todoapp/#!/Home");
		
	}
}
