package com.bridgelabz.todoapp.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.bridgeit.sample.reference.Test;
import com.bridgelabz.todoapp.model.User;

/**
 * @author bridgelabz3 Raghava 
 * this class is RegValidation implements Validator class
 * and overrides validate method 
 *
 */
public class RegValidation  implements Validator
{

	private Pattern pattern;
	private Matcher matcher;
	
	private static String EMAIL_PATTERN="^[A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"+
	                                      "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	private static String STRING_PATTERN="((?i)[a-z]+(\\s+[a-z]+)*)";
	private static String MOBILE_PATTERN="[0-9]{10}";
	private static String PASSWORD_PATTERN="[a-zA-z0-9]{5,15}";//[a-zA-z0-9]*[@]{5,15}
	
	public boolean supports(Class<?> arg0) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public void validate(Object target, Errors errors) 
	{
		User user=(User)target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors,"fullName", "required.fullname","name required");
		if(user.getFullName()!=null&& !user.getFullName().isEmpty())
		{
			pattern=Pattern.compile(STRING_PATTERN);
			matcher=pattern.matcher(user.getFullName());
			
			if(!matcher.matches())
			{
				errors.rejectValue("fullName","name is required","enter the full name");
			}
		}

		ValidationUtils.rejectIfEmptyOrWhitespace(errors,"email", "required.email","email required");
		if(user.getEmail()!=null&& !user.getEmail().isEmpty())
		{
			pattern=Pattern.compile(EMAIL_PATTERN);
			matcher=pattern.matcher(user.getEmail());
			
			if(!matcher.matches())
			{
				errors.rejectValue("email","email is required","enter the email");
			}
		}
			
		ValidationUtils.rejectIfEmptyOrWhitespace(errors,"mobileNumber", "required.mobilenumber","mobile required");
		if(user.getMobileNumber()!=null&& !user.getMobileNumber().isEmpty())
		{
			pattern=Pattern.compile(MOBILE_PATTERN);
			matcher=pattern.matcher(user.getMobileNumber());
			
			if(!matcher.matches())
			{
				errors.rejectValue("mobileNumber","mobile number is required","enter the mobileNumber");
			}
		}
		
	/*	ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "required password");
		if(user.getPassword()!=null&& !user.getPassword().isEmpty())
		{
			pattern=Pattern.compile(PASSWORD_PATTERN);
			matcher=pattern.matcher(user.getPassword());
			
			if(!matcher.matches())
			{
				errors.rejectValue("password","password  is required","enter the password");
			}
		}*/
		
	}
}
