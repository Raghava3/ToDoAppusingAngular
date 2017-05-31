package com.bridgelabz.todoapp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.todoapp.model.User;
import com.bridgelabz.todoapp.service.serviceinterface.UserSerInter;
import com.bridgelabz.todoapp.validator.RegValidation;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * @author bridgelabz3 Raghava
 * This RegistrationController class for user registration  
 * it registration method and returns ResponseEntity Object
 * import java.util.regex.Matcher;
 */
@RestController
public class SignUpController
{

	@Autowired
	private UserSerInter userSerInter;
	@Autowired
	private RegValidation regValidation;
	/** 
	 * Takes the data from user and validate the data if given data is valid then update to database
	 * @param user
	 * @param bindingResult
	 * @param req
	 * @param resp
	 * @return String,HttpStatus 
	 * @throws JsonProcessingException 
	 */
	@RequestMapping(value="/signUp", method=RequestMethod.POST)
	public ResponseEntity<String> signUp(@RequestBody User user,BindingResult bindingResult, HttpServletRequest req,HttpServletResponse resp) throws JsonProcessingException
	{
		System.out.println("inside signup");
		
		HttpSession session=req.getSession();
		session.invalidate();                               //doing sessin invalidate otherwise it will add . ask sir 
		regValidation.validate(user, bindingResult);       //calling validate method 
		if(bindingResult.hasErrors()){
			
			
			ObjectMapper mapper=new ObjectMapper();
			ObjectNode root=mapper.createObjectNode();
			
			root.put("status", "alredy user is there");
			
			String data=mapper.writeValueAsString(root);
			
			
			return new ResponseEntity<String>(data,HttpStatus.NOT_ACCEPTABLE);
		}
		else{
		 boolean  result=userSerInter.registration(user); //calling registration method
		
		 if(result){
			ObjectMapper mapper=new ObjectMapper();
			ObjectNode root=mapper.createObjectNode();
			root.put("status","sucess");
			String data=mapper.writeValueAsString(root);
			
			
			return new ResponseEntity<String>(data, HttpStatus.OK);
		 }
		 else {
			 
			    ObjectMapper mapper=new ObjectMapper();
				ObjectNode root=mapper.createObjectNode();
				root.put("status", "error");
				String data=mapper.writeValueAsString(root);
				return new ResponseEntity<String>(data,HttpStatus.NOT_ACCEPTABLE);
			 
		 }
		}
}
}
