package com.bridgelabz.todoapp.dao.daoimplementation;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;

import com.bridgelabz.todoapp.dao.daointerface.ToDoDao;
import com.bridgelabz.todoapp.model.ToDo;

public class ToDoDaoImpl implements ToDoDao{

	@Autowired
	private SessionFactory sessionFactory;
	
	private Session session = null;
	
	@Override
	public List<ToDo> getNotes(int UserId) {
		try {
			session = sessionFactory.openSession();
			Transaction transaction = session.beginTransaction();
			
			String hql = "from ToDo where user_id=:userId";
			Query query = session.createQuery(hql);
			query.setParameter("userId", UserId);
			List<ToDo> notes = query.list();
			transaction.commit();
			return notes;
		}
		finally {
			if(session != null)
			session.close();
		}
	
	}

	@Override
	public boolean addNote(ToDo toDo) 
	{
		try {
			session = sessionFactory.openSession();
			Transaction transaction = session.beginTransaction();
			
			session.save(toDo);
			transaction.commit();
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		finally {
			if(session != null) {
				session.close();
			}
		}

	}

	@Override
	public int deleteNote(int id) 
	{
		try {
			session = sessionFactory.openSession();
			Transaction transaction = session.beginTransaction();
			
			String hql = "delete from ToDo where id=:id";
			Query query = session.createQuery(hql);
			query.setParameter("id", id);
			int result = query.executeUpdate();
			transaction.commit();
			return result;
		}
		finally {
			if(session != null) {
				session.close();
			}
		}
	}

	@Override
	public boolean updateNote(ToDo toDo) {
		try {
			session = sessionFactory.openSession();
			Transaction transaction = session.beginTransaction();
			session.update(toDo);
			transaction.commit();
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		finally {
			if(session != null) {
				session.close();
			}
			
		}
	}

	@Override
	public void setColor(ToDo toDo) {

		try{
			session = sessionFactory.openSession();
			Transaction transaction = session.beginTransaction();
			
			session.update(toDo);
			transaction.commit();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			if(session!=null) {
				session.close();
			}
		}
	}
	
	@Override
	public boolean copyToDo(ToDo copy) {
		
		try{
			session = sessionFactory.openSession();
			Transaction transaction = session.beginTransaction();
			session.save(copy);
			transaction.commit();
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		finally {
			if( session != null ) {
				session.close();
			}
		}
		
	}



	@Override
	public void setReminder(ToDo toDo) {
		
		try{
			session = sessionFactory.openSession();
			Transaction transaction = session.beginTransaction();
			session.update(toDo);
			transaction.commit();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			if(session != null ){
				session.close();
			}
		}
	}



	@Override
	public void cancelRemainder(ToDo toDo) {
		
		try{
			session = sessionFactory.openSession();
			Transaction transaction = session.beginTransaction();
			
			session.update(toDo);
			transaction.commit();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			if(session!=null) {
				session.close();
			}
		}
	}

	
	}


