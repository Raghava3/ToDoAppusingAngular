package com.bridgelabz.todoapp.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GoogleController {
	@Autowired
	GoogleConnection googleConnection;
	
	@RequestMapping(value="/loginWithGoogle")
	public void loginWithGoogle(HttpServletRequest request,HttpServletResponse response) throws IOException
	{	try 
		{
			System.out.println("coming inside loginwithgoogle");
			String unid=UUID.randomUUID().toString();
			request.getSession().setAttribute("STATE", unid);
			String googleLoginURL=googleConnection.getGoogleAuthURL(unid);
			System.out.println(googleLoginURL);
			response.sendRedirect(googleLoginURL);
		}
	 catch (UnsupportedEncodingException e) 
	{
		e.printStackTrace();
	}
	}
	@RequestMapping(value="googleconnection")
	public void googleConnnection()
	{
		System.out.println("coming after redirect");
	}

}
