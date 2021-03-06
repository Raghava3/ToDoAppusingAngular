package com.bridgelabz.todoapp.model;

import java.util.Date;
import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class GoogleUserProfile {
	private String id;
	private String displayName;
	private Date birthday;
	private String gender;
	private List<GoogleEmails> emails;
	private GoogleImage image;
	
	public GoogleImage getImage() {
		return image;
	}
	public void setImage(GoogleImage image) {
		this.image = image;
	}
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
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public List<GoogleEmails> getEmails() {
		return emails;
	}
	public void setEmails(List<GoogleEmails> emails) {
		this.emails = emails;
	}
	
	@Override
	public String toString() {
		return "GmailProfile [id=" + id + ", displayName=" + displayName + ", emails=" + emails + "]";
	}
	

	
	
}
