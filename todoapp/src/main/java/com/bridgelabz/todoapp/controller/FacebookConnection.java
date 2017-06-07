package com.bridgelabz.todoapp.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

import com.bridgelabz.todoapp.model.FaceBookProfile;
import com.bridgelabz.todoapp.model.FaceBookTokenAccess;

public class FacebookConnection
{

	public static final String FB_APP_ID = "233416400492726";
	public static final String FB_APP_SECRET = "fdc37d4dd64890480240010d4843e82d";
	public static final String REDIRECT_URI = "http://localhost:8030/todoapp/fbconnection";
	
	public String FB_URL = "https://www.facebook.com/v2.9/dialog/oauth?client_id=%s&redirect_uri=%s&state=%s&response_type=code&scope=public_profile,email";
	public String FB_ACCESS_TOKEN_URL = "https://graph.facebook.com/v2.9/oauth/access_token?client_id=%s&client_secret=%s&redirect_uri=%s&code=%s";
	public String FB_GET_USER_URL = "https://graph.facebook.com/v2.9/me?access_token=%s&fields=id,name,email,first_name,last_name,picture";

 
	static String accessToken="";
	
	public String getFbAuthURL(String unid )
	{
		String fbLoginURL="";
		try{
			fbLoginURL = "http://www.facebook.com/dialog/oauth?" + "client_id="
					+ FB_APP_ID + "&redirect_uri="
					+ URLEncoder.encode(REDIRECT_URI, "UTF-8")
					+"&state="+unid+"&response_type=code"
					+ "&scope=email,public_profile";
		   }
			catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			return fbLoginURL;
	}
	
	public String getAccessToken(String code) throws UnsupportedEncodingException
	{
	String AccessToken_URL	="https://graph.facebook.com/oauth/access_token?"
	       +"client_id="+FB_APP_ID+"&redirect_uri="+URLEncoder.encode(REDIRECT_URI, "UTF-8")
		   +"&client_secret="+FB_APP_SECRET+"&code="+code;

	ResteasyClient restCall=new ResteasyClientBuilder().build();
	ResteasyWebTarget target=restCall.target(AccessToken_URL);
	Response response =target.request().accept(MediaType.APPLICATION_JSON).get();
	FaceBookTokenAccess faceBookTokenAccess=response.readEntity(FaceBookTokenAccess.class);
	restCall.close();
	return faceBookTokenAccess.getAccess_token();
	}
	
	public FaceBookProfile getUserProfile(String accessToken)
	{
	  String getUserURL="https://graph.facebook.com/v2.9/me?access_token="+accessToken+"&fields=id,name,email";
	  ResteasyClient restCall=new ResteasyClientBuilder().build();
	  ResteasyWebTarget target=restCall.target(getUserURL);
	  Response response =target.request().accept(MediaType.APPLICATION_JSON).get();
	  FaceBookProfile profile=response.readEntity(FaceBookProfile.class);
      return profile;
	}
	
}
