package com.bridgelabz.todoapp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.omg.CORBA.Request;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

@RestController
public class LogOutController {

	/**
	 * checks if user logged in or not.if user logged in then invalidate the session.
	 * @param resp
	 * @param req
	 * @return String,HttpStatus
	 * @throws JsonProcessingException 
	 */
	@RequestMapping(value="/signOut",method=RequestMethod.GET)
	public ResponseEntity<String> logOut(HttpServletResponse resp, HttpServletRequest req) throws JsonProcessingException
	{
		HttpSession session=req.getSession();
		///if(session.getAttribute("user")!=null)      //checks  if session existed or not if session exist then session will be invalidated
		//{
			System.out.println("coming inside the loout");
			session.invalidate();
			req.getSession();
			ObjectMapper mapper = new ObjectMapper();
			ObjectNode root = mapper.createObjectNode();
			root.put("status", "Logoutsucessfull");
			String data = mapper.writeValueAsString(root);
			return new ResponseEntity<String>(data,HttpStatus.OK);
		//}
		//else{
		//	return new ResponseEntity<String>("please login first  ",HttpStatus.NOT_ACCEPTABLE);
		//}
	}
}
