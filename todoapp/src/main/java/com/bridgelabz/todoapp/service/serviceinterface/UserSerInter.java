package com.bridgelabz.todoapp.service.serviceinterface;

import com.bridgelabz.todoapp.model.User;



/**
 * @author bridgelabz3 Raghava
 * this is UserSerInter interface
 *
 */
public interface UserSerInter 
{
	public boolean registration(User user);
	public User login(String mail, String password);
}
