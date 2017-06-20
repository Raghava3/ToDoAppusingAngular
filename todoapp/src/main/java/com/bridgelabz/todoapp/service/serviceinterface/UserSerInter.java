package com.bridgelabz.todoapp.service.serviceinterface;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;

import com.bridgelabz.todoapp.model.User;
import com.fasterxml.jackson.core.JsonProcessingException;



/**
 * @author bridgelabz3 Raghava
 * this is UserSerInter interface
 *
 */
public interface UserSerInter 
{
	public boolean registration(User user);
	public ResponseEntity<String> login(String mail, String password,HttpServletResponse response,HttpServletRequest request) throws JsonProcessingException;
	public User getUserByEmail(String email);

}
