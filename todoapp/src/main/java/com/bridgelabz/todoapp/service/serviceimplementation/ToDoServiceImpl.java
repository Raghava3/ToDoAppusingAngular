package com.bridgelabz.todoapp.service.serviceimplementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bridgelabz.todoapp.dao.daointerface.ToDoDao;
import com.bridgelabz.todoapp.model.Label;
import com.bridgelabz.todoapp.model.ToDo;
import com.bridgelabz.todoapp.model.TrashToDo;
import com.bridgelabz.todoapp.service.serviceinterface.ToDoService;

@Service
public class ToDoServiceImpl implements ToDoService {

	@Autowired
	private ToDoDao toDoDao;
	
	@Override
	public List<ToDo> getNotes(int userId) 
	{
		return toDoDao.getNotes(userId);
	}

	@Override
	@Transactional
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

	@Override
	public void setColor(ToDo toDo) {

		
		toDoDao.setColor(toDo);
		
	}
	@Override
	public boolean copyToDo(ToDo copy) {
		
		return toDoDao.copyToDo(copy);
	}

	@Override
	public void setReminder(ToDo toDo) {
		
		toDoDao.setReminder(toDo);
	}

	@Override
	public void cancelRemainder(ToDo toDo) {
		
		toDoDao.cancelRemainder(toDo);
	}

	@Override
	public boolean moveToTrash(TrashToDo trashToDo) {
		System.out.println("coming inside the service");
		return toDoDao.moveToTrash(trashToDo);
		
	}

	@Override
	public List<ToDo> getTrashNotes(int userId) {
		 return toDoDao.getTrashNotes(userId);
	}

	@Override
	public int deleteNotePermanently(int id) 
	{
		System.out.println("coming inside the service impl");
		
		return toDoDao.deleteNotePermanently(id);
	}

	@Override
	public boolean addLabel(Label label) {// TODO Auto-generated method stub
		return toDoDao.addLabel(label);
	}

	@Override
	public List<Label> getLabel(int userId) {
		return toDoDao.getLabel(userId);
	}


	

}
