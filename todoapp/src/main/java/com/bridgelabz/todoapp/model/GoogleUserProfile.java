package com.bridgelabz.todoapp.model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class GoogleUserProfile {

	private String id;
	private String displayName;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	@Override
	public String toString() {
		return "GoogleUserProfile [id=" + id + ", displayName=" + displayName + "]";
	}
	
	
}
