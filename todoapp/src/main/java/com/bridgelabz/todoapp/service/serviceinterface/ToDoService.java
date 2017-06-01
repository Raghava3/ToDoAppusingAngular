package com.bridgelabz.todoapp.service.serviceinterface;

import java.util.List;

import com.bridgelabz.todoapp.model.ToDo;

public interface ToDoService {
	
	/**
	 * to get all notes added for particular user
	 * 
	 * @param id
	 * @return List
	 */
	public List<ToDo> getNotes(int userId);

	/**
	 * @param toDoData
	 * @return true/false
	 */
	public boolean addNote(ToDo toDo);
	/**
	 * @param id
	 * @return int number of rows affected
	 */
	public int deleteNote(int id);
	/**
	 * 
	 * @param toDo
	 * @return true/false
	 */
	public boolean updateNote(ToDo toDo);

	/**
	 * @param toDo
	 */
	public void setColor(ToDo toDo);
	/**
	 * @param copy
	 * @return
	 */
	public boolean copyToDo(ToDo copy);

	/**
	 * @param toDo
	 */
	public void setReminder(ToDo toDo);

	/**
	 * @param toDoId
	 */
	public void cancelRemainder(ToDo toDo);

	
}
