package com.bridgelabz.todoapp.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.todoapp.model.User;
import com.bridgelabz.todoapp.service.serviceinterface.UserSerInter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * @author bridgelabz3 Raghava
 * This LoginController class for Login validation  based on the email and password 
 * given by the user, and  it calls login method and returns ResponseEntity Object
 * 
 */

@RestController
public class LoginController {

	@Autowired
	UserSerInter userSerInter;
	
	@RequestMapping(value = "login", method = RequestMethod.POST)
	public ResponseEntity<String> login(@RequestBody Map<String, String> loginMap, HttpServletRequest request,
			HttpServletResponse response) throws JsonProcessingException 
	{
	 return userSerInter.login(loginMap.get("email"), loginMap.get("password"), response,request);//taking user id and password
	}
}
