package com.bridgelabz.todoapp.service.serviceimplementation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.bridgelabz.todoapp.dao.daointerface.UserDaoInter;
import com.bridgelabz.todoapp.model.User;
import com.bridgelabz.todoapp.service.serviceinterface.UserSerInter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;


public class UserSerImpl implements UserSerInter 
{

	@Autowired
	private UserDaoInter userDaoInter;
	
	public boolean registration(User user) 
	{
		return  userDaoInter.registration(user);
	}


	

	@Override
	public User getUserByEmail(String email) {
		return userDaoInter.getUserByEmail( email);

	}


	@Override
	public ResponseEntity<String> login(String mail, String password, HttpServletResponse response,
			HttpServletRequest request) throws JsonProcessingException {
		User user= userDaoInter.login(mail, password);
		if(user!=null)
		{
			System.out.println("comin inside the service");
			HttpSession session=request.getSession();
			session.setAttribute("user", user);
			ObjectMapper mapper=new ObjectMapper();
			ObjectNode root=mapper.createObjectNode();
			root.put("status", "sucess");
			String data=mapper.writeValueAsString(root);
		    return new ResponseEntity<>(data,HttpStatus.OK);
		}
		
		else
		{
			HttpSession session=request.getSession();
			session.setAttribute("user", user);
			ObjectMapper mapper=new ObjectMapper();
			ObjectNode root=mapper.createObjectNode();
			root.put("status", "sucess");
			String data=mapper.writeValueAsString(root);
			return new ResponseEntity<String>(data,HttpStatus.NOT_ACCEPTABLE);
		}
	}
	
}
