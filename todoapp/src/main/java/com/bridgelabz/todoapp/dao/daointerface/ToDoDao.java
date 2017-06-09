package com.bridgelabz.todoapp.dao.daointerface;

import java.util.List;

import com.bridgelabz.todoapp.model.ToDo;
import com.bridgelabz.todoapp.model.TrashToDo;

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

	/**
	 * @param toDo
	 */
	public void setColor(ToDo toDo);
	/**
	 * @param copy
	 * @return true/false
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

	public boolean  moveToTrash(TrashToDo trashToDo);
	
	/**
	 * @param id
	 * @return List
	 */
	public List<ToDo> getTrashNotes(int UserId);

	
}
