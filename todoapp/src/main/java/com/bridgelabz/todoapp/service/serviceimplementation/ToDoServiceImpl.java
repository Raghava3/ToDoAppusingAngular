package com.bridgelabz.todoapp.service.serviceimplementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.bridgelabz.todoapp.dao.daointerface.ToDoDao;
import com.bridgelabz.todoapp.model.ToDo;
import com.bridgelabz.todoapp.service.serviceinterface.ToDoService;

public class ToDoServiceImpl implements ToDoService {

	@Autowired
	private ToDoDao toDoDao;
	
	@Override
	public List<ToDo> getNotes(int userId) 
	{
		return toDoDao.getNotes(userId);
	}

	@Override
	public boolean addNote(ToDo toDo)
	{
		return toDoDao.addNote(toDo);
	
	}

	@Override
	public int deleteNote(int id)
	{
		return toDoDao.deleteNote(id);
	}

	@Override
	public boolean updateNote(ToDo toDo) {
		return toDoDao.updateNote(toDo);
	}

}
