package com.bridgelabz.todoapp.dao.daointerface;

import java.util.List;

import com.bridgelabz.todoapp.model.ToDo;

public interface ToDoDao {
	
	/**
	 * @param id
	 * @return List
	 */
	public List<ToDo> getNotes(int UserId);

	/**
	 * @param toDo
	 * @return true/false
	 */
	public boolean addNote(ToDo toDo);
	/**
	 * @param id
	 * @return int  rows affected
	 */
	public int deleteNote(int id);

	/**
	 * @param toDo
	 * @return true/false
	 */
	public boolean updateNote(ToDo toDo);

	
}
