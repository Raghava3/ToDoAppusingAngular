package com.bridgelabz.todoapp.dao.daoimplementation;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bridgelabz.todoapp.dao.daointerface.ToDoDao;
import com.bridgelabz.todoapp.model.Label;
import com.bridgelabz.todoapp.model.ToDo;
import com.bridgelabz.todoapp.model.TrashToDo;

@Repository
public class ToDoDaoImpl implements ToDoDao{

	@Autowired
	private SessionFactory sessionFactory;
	
/*	private Session session = null;
	*/
	@Override
	public List<ToDo> getNotes(int UserId) {
		Session session=null;
		try {
		session = sessionFactory.openSession();
			
			String hql = "from ToDo where user_id=:userId";
			Query query = session.createQuery(hql);
			query.setParameter("userId", UserId);
			List<ToDo> notes = query.list();
			
			return notes;
		}
		finally 
		{
			if(session != null)
			session.close();
		}
	
	}

	@Override
	public boolean addNote(ToDo toDo) 
	{
		Session session=null;
		try {
			session = sessionFactory.openSession();
			Transaction transaction=session.beginTransaction();
			
			if((toDo.getTitle()==null&&toDo.getNote()==null)||(toDo.getTitle()==""&&toDo.getNote()==""))
			{
			System.out.println("null");
				toDo.setTitle("Empty");
				toDo.setNote("Empty");	
			}
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
		Session session=null;
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
           Session session=null;
		
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

		   Session session=null;
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
		   Session session=null;
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
		   Session session=null;
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
		   Session session=null;
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
	public boolean moveToTrash(TrashToDo trashToDo) {
		   Session session=null;
		System.out.println("coming inside the daoimpl");
		
		try{
			session = sessionFactory.openSession();
			Transaction transaction = session.beginTransaction();
			session.save(trashToDo);
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
	public List<ToDo> getTrashNotes(int UserId) {
		   Session session=null;
		try {
			session = sessionFactory.openSession();
			
			String hql = "from TrashToDo where user_id=:userId";
			Query query = session.createQuery(hql);
			query.setParameter("userId", UserId);
			List<ToDo> notes = query.list();
			
			return notes;
		}
		finally {
			if(session != null)
			session.close();
		}
	}

	@Override
	public int deleteNotePermanently(int id) {
		   Session session=null;
		try {
			  
			System.out.println("coming inside dao");
			
			session = sessionFactory.openSession();
			Transaction transaction = session.beginTransaction();
			
			String hql = "delete from TrashToDo where id=:id";
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
	public boolean addLabel(Label label) {
		   Session session=null;
		try{
			session = sessionFactory.openSession();
			Transaction transaction = session.beginTransaction();
			session.save(label);
			transaction.commit();
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		finally
		{
			if( session != null )
			{
				session.close();
			}	// TODO Auto-generated method stub
			
		}
	}

	@Override
	public List<Label> getLabel(int userId) {
		   Session session=null;
		try {
			session = sessionFactory.openSession();
			
			String hql = "from Label where user_id=:userId";
			Query query = session.createQuery(hql);
			query.setParameter("userId", userId);
			List<Label> label = query.list();
			
			return label;
		}
		finally {
			if(session != null)
			session.close();
		}
	}

	

}

