package com.bridgelabz.todoapp.model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class FaceBookTokenAccess {

	private String access_token;
	private String token_type;
	private String  exprires_in;
	public String getAccess_token() {
		return access_token;
	}
	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}
	public String getToken_type() {
		return token_type;
	}
	public void setToken_type(String token_type) {
		this.token_type = token_type;
	}
	public String getExprires_in() {
		return exprires_in;
	}
	public void setExprires_in(String exprires_in) {
		this.exprires_in = exprires_in;
	}
	
}
