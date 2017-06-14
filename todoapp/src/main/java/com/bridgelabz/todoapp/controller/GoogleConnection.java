package com.bridgelabz.todoapp.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

import com.bridgelabz.todoapp.model.GoogleAccessToken;
import com.bridgelabz.todoapp.model.GoogleUserProfile;

public class GoogleConnection {

	public static final String GOOGLE_APP_ID="53171160237-623f3svhvbu7g49bka3lf8m8m9sd527f.apps.googleusercontent.com";
	public static final String GOOGLE_APP_SECRET="oEQvqyUxx1Z3-QID5o293bmT";
	public static final String REDIRECT_URI="http://localhost:8030/todoapp/googleconnection";
	
	public GoogleConnection() {
		System.out.println(getClass().getSimpleName()+" Created");
		// TODO Auto-generated constructor stub
	}

	public String getGoogleAuthURL(String unid)
	{
		String googleLoginUrl="";
		try{
			googleLoginUrl ="https://accounts.google.com/o/oauth2/v2/auth?client_id="
					+ GOOGLE_APP_ID +"&redirect_uri="
					+ URLEncoder.encode(REDIRECT_URI, "UTF-8")
					+"&state="+unid+"&response_type=code"
					+ "&scope=profile email&approval_prompt=force&access_type=offline";
		}
		catch(UnsupportedEncodingException e)
		{
			e.printStackTrace();
			
		}
		return googleLoginUrl;
	}
	
	public String getAccessToken(String authcode)
	{
   String accessTokenURL = "https://www.googleapis.com/oauth2/v4/token";
		
		ResteasyClient restCall = new ResteasyClientBuilder().build();
		
		ResteasyWebTarget target = restCall.target(accessTokenURL);
		
		Form f = new Form();
		f.param("client_id", GOOGLE_APP_ID);
		f.param("client_secret", GOOGLE_APP_SECRET);
		f.param("redirect_uri",REDIRECT_URI );
		f.param("code", authcode);
		f.param("grant_type","authorization_code");
		
		Response response = target.request().accept(MediaType.APPLICATION_JSON).post(Entity.form(f));
		
		GoogleAccessToken accessToken = response.readEntity(GoogleAccessToken.class);
		
		System.out.println("accesstoken"+accessToken);
		
		restCall.close();
		
		return accessToken.getAccess_token();

	
	}
	
	public GoogleUserProfile getUserProfile(String accessToken)
	{ 
String gmail_user_url= "https://www.googleapis.com/plus/v1/people/me";
		
	
		
		ResteasyClient restCall = new ResteasyClientBuilder().build();
		ResteasyWebTarget target = restCall.target(gmail_user_url);
		
		String headerAuth="Bearer "+accessToken;
		System.out.println("header"+headerAuth);
		Response response = target.request().header("Authorization", headerAuth).accept(MediaType.APPLICATION_JSON).get();
		
		GoogleUserProfile profile = (GoogleUserProfile) response.readEntity(GoogleUserProfile.class);
		restCall.close();
		
		System.out.println(profile.getEmails().get(0).getValue());
		return profile;
	}
}

