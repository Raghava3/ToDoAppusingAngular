package com.bridgelabz.todoapp.dao.daointerface;

import com.bridgelabz.todoapp.model.User;

/**
 * @author bridgelabz3 Raghava
 * this is UserDaoInter interface
 *
 */
public interface UserDaoInter 
{
	/**
	 * stores in to database
	 * @param user
	 * @return boolean
	 */
	public boolean registration(User user);
	/**
	 * retrieve from database
	 * @param mail
	 * @param password
	 * @return User Object
	 */
	public User login(String mail, String password);

}
