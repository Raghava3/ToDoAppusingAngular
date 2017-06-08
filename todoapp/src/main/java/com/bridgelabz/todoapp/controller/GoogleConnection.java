package com.bridgelabz.todoapp.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class GoogleConnection {

	public static final String GOOGLE_APP_ID="53171160237-623f3svhvbu7g49bka3lf8m8m9sd527f.apps.googleusercontent.com";
	public static final String GOOGLE_APP_SECRET="oEQvqyUxx1Z3-QID5o293bmT";
	public static final String REDIRECT_URI="http://localhost:8030/todoapp/googleconnection";
	
	public String getGoogleAuthURL(String unid)
	{
		String googleLoginUrl="";
		try{
			googleLoginUrl ="https://accounts.google.com/o/oauth2/v2/auth?client_id="
					+ GOOGLE_APP_ID +"&redirect_uri="
					+ URLEncoder.encode(REDIRECT_URI, "UTF-8")
					+"&state="+unid+"&response_type=code"
					+ "&scope=email&approval_prompt=force&access_type=offline";
		}
		catch(UnsupportedEncodingException e)
		{
			e.printStackTrace();
			
		}
		return googleLoginUrl;
	}
}
