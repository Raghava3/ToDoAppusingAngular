package com.bridgelabz.todoapp.controller;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.todoapp.model.Label;
import com.bridgelabz.todoapp.model.ToDo;
import com.bridgelabz.todoapp.model.TrashToDo;
import com.bridgelabz.todoapp.model.User;
import com.bridgelabz.todoapp.service.serviceinterface.ToDoService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

@RestController
public class ToDoController {

	@Autowired
	private ToDoService toDoService;

	
	Logger log=Logger.getLogger(ToDoController.class);
    	
	
	@RequestMapping(value = "getNotes", method = RequestMethod.GET)
	public ResponseEntity<String> getNotes(HttpServletRequest request, HttpServletResponse response)
			throws JsonProcessingException {

		log.debug("coming inside the getNotes method");

		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		if (user != null && session != null) {

			log.debug("user is there");
			
			List<ToDo> todoList = toDoService.getNotes(user.getId());
		    
			  log.info(user.getId());
			
			  if (!todoList.isEmpty()) {
				Collections.reverse(todoList);
				ObjectMapper mapper = new ObjectMapper();
				ObjectNode root = mapper.createObjectNode();
				root.put("status", "success");
				root.putPOJO("todo", todoList);
				root.putPOJO("user", user);
				String data = mapper.writeValueAsString(root);
				
				log.debug("list is not empty");
				log.info(data);
				
				return new ResponseEntity<String>(data, HttpStatus.OK);
			  } 
			else {
				
				log.debug("list in empty");
				
				ObjectMapper mapper = new ObjectMapper();
				ObjectNode root = mapper.createObjectNode();
				root.put("status", "notes are not there");
				root.putPOJO("todo", todoList);
				root.putPOJO("user", user);
				String data = mapper.writeValueAsString(root);
			    
				log.info(data);
				
				return new ResponseEntity<String>(data, HttpStatus.OK);
			}
		} 
		else {
			ObjectMapper mapper = new ObjectMapper();
			ObjectNode root = mapper.createObjectNode();
			root.put("status", "no user");
			String data = mapper.writeValueAsString(root);
			
			log.debug(data);
			log.debug("user is not there");
			
			return new ResponseEntity<String>(data, HttpStatus.UNAUTHORIZED);
		}
	}

	/**
	 * to add the new note
	 * 
	 * @param toDo
	 * @param request
	 * @param response
	 * @return String message and status
	 * @throws JsonProcessingException
	 */
	@RequestMapping(value = "/addNote" , method=RequestMethod.POST)
	public ResponseEntity<String> addNote(@RequestBody ToDo toDo, HttpServletRequest request,
			HttpServletResponse response) throws JsonProcessingException {

		log.debug("coming inside the addnote method");

		HttpSession session = request.getSession(false);
		User user = (User) session.getAttribute("user");
		if (session != null && user != null) {
			toDo.setUser(user);
			log.info(toDo.getId());
			boolean result = toDoService.addNote(toDo);
			if (result) {
				
				log.debug("notes added successfully");
				
				ObjectMapper mapper = new ObjectMapper();
				ObjectNode root = mapper.createObjectNode();
				root.put("status", "sucess");
				root.putPOJO("todo", toDo);
				String data = mapper.writeValueAsString(root);
				
				log.info(data);
				
				return new ResponseEntity<String>(data, HttpStatus.OK);
			} else {

				ObjectMapper mapper = new ObjectMapper();
				ObjectNode root = mapper.createObjectNode();
				root.put("status", "failure");
				String data = mapper.writeValueAsString(root);
				
				log.debug("fail to add note");
				log.info(data);
				
				return new ResponseEntity<String>(data, HttpStatus.NO_CONTENT);
			}
		}

		else {
			ObjectMapper mapper = new ObjectMapper();
			ObjectNode root = mapper.createObjectNode();
			root.put("status", "failure");
			String data = mapper.writeValueAsString(root);
	      
			log.debug("not login");
			log.info(data);

			return new ResponseEntity<String>(data, HttpStatus.UNAUTHORIZED);
		}
	}

	
	/**
	 * to delete note
	 * 
	 * @param id
	 * @param request
	 * @param response
	 * @return String message and status
	 * @throws JsonProcessingException 
	 */
	
	@RequestMapping(value = "deleteNote/{id}", method = RequestMethod.POST)
	public ResponseEntity<String> deleteNote(@PathVariable("id") int id, HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
		
		log.debug("coming inside delete notes");
		
		HttpSession session = request.getSession(false);
		User user = (User) session.getAttribute("user");
		if (session != null && user != null) {
			int result = toDoService.deleteNote(id);
			if (result != 0) {
				List<ToDo> todoList = toDoService.getNotes(user.getId());
				ObjectMapper mapper = new ObjectMapper();
				ObjectNode root = mapper.createObjectNode();
				root.put("status", "success");
				root.putPOJO("todo", todoList);
				String data = mapper.writeValueAsString(root);

				log.debug("note deleted sucessfully");
				log.info( data ); 
				
				return new ResponseEntity<String>(data, HttpStatus.OK);
				
			} else {
				ObjectMapper mapper = new ObjectMapper();
				ObjectNode root = mapper.createObjectNode();
				root.put("status", "toDoNot there");
				String data = mapper.writeValueAsString(root);
				
				log.info( data );
				log.debug("there is no note");
				
				return new ResponseEntity<String>(data, HttpStatus.NOT_FOUND);
			}
		}
		else{
		
			ObjectMapper mapper = new ObjectMapper();
			ObjectNode root = mapper.createObjectNode();
			root.put("status", "signIn first");
			String data = mapper.writeValueAsString(root);
	   		
			log.debug("not login");
			log.info( data );
	   		
		    return new ResponseEntity<String>(data, HttpStatus.UNAUTHORIZED);
		
		}
	}
	

	/**
	 * to update the note
	 * 
	 * @param toDo
	 * @param toDoid
	 * @param request
	 * @param response
	 * @return String message and status
	 * @throws JsonProcessingException 
	 */
	@RequestMapping(value = "updateNote", method = RequestMethod.POST)
	public ResponseEntity<String> updateNote(@RequestBody ToDo toDo, HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
		
		log.debug("coming inside the update note method");
		
		HttpSession session = request.getSession(false);
		User user = (User) session.getAttribute("user");
		if ( session != null && user != null ) {
			toDo.setUser(user);
			boolean result = toDoService.updateNote(toDo);
			if (result) {
				ObjectMapper mapper = new ObjectMapper();
				ObjectNode root = mapper.createObjectNode();
				root.put("message", "Updated");
				
				log.debug("note updated sucessfully");
				
				String data = mapper.writeValueAsString(root);
				return new ResponseEntity<String>(data, HttpStatus.OK);
			} else {
				ObjectMapper mapper = new ObjectMapper();
				ObjectNode root = mapper.createObjectNode();
				root.put("status", "not updated");
				
				log.debug("fail to update");
				
				String data = mapper.writeValueAsString(root);
				return new ResponseEntity<String>(data, HttpStatus.OK);
			}
				
		} else {
			ObjectMapper mapper = new ObjectMapper();
			ObjectNode root = mapper.createObjectNode();
			root.put("message", "login required");
			
			log.debug("not login");
			
			String data = mapper.writeValueAsString(root);
			return new ResponseEntity<String>(data, HttpStatus.UNAUTHORIZED);
		}
	}
	
	

	/**
	 * @param toDo
	 * @param request
	 * @param response
	 * @return ResponseEntity
	 * @throws JsonProcessingException
	 */
	@RequestMapping(value="/copyToDo", method=RequestMethod.POST)
	public ResponseEntity<String> copyToDo(@RequestBody ToDo toDo, HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
		
		log.debug("coming inside the copyToDo method");
		
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		if( session != null && user != null ) {
			toDo.setUser(user);
			boolean result = toDoService.copyToDo( toDo );
			if( result ) {
				ObjectMapper mapper = new ObjectMapper();
				ObjectNode root = mapper.createObjectNode();
				root.put("message", "toDo Coppied");
				root.putPOJO("todoCopy", toDo);
				
				log.debug("note copied sucessfully");
				
				String data = mapper.writeValueAsString(root);
				return new ResponseEntity<String>(data, HttpStatus.OK);
			}
			else {
				ObjectMapper mapper = new ObjectMapper();
				ObjectNode root = mapper.createObjectNode();
				root.put("message", "toDo not Coppied");
				
				log.debug("fail to copy notes");
				
				String data = mapper.writeValueAsString(root);
				return new ResponseEntity<String>(data, HttpStatus.NOT_ACCEPTABLE);
			}
			
		}
		else {
			ObjectMapper mapper = new ObjectMapper();
			ObjectNode root = mapper.createObjectNode();
			root.put("message", "login required");
			
			log.debug("not login");
			
			String data = mapper.writeValueAsString(root);
			return new ResponseEntity<String>(data, HttpStatus.UNAUTHORIZED);
		}
	}
	
	/**
	 * @param toDo
	 * @param request
	 * @param response
	 * @return ResponseEntity
	 * @throws JsonProcessingException
	 */
	@RequestMapping(value="setReminder", method=RequestMethod.POST)
	public ResponseEntity<String> setReminder ( @RequestBody ToDo toDo, HttpServletRequest request, HttpServletResponse response  ) throws JsonProcessingException {
		
		log.debug("coming inside the setreminder");
		
		HttpSession session = request.getSession();
		User  user = (User) session.getAttribute("user");
		if(session != null && user != null ){
			toDoService.setReminder(toDo);
			ObjectMapper mapper = new ObjectMapper();
			ObjectNode root = mapper.createObjectNode();
			root.put("message", "remainder updated");
			root.putPOJO("todo", toDo);
			String data = mapper.writeValueAsString(root);
			
			log.debug("reminder sucess");
			log.info( data );
			
			return new ResponseEntity<String>(data, HttpStatus.OK);
		}
		else{
			
			ObjectMapper mapper = new ObjectMapper();
			ObjectNode root = mapper.createObjectNode();
			root.put("message", "signIn Required");
			
			log.debug("not login");
			
			String data = mapper.writeValueAsString(root);
			return new ResponseEntity<String>(data, HttpStatus.UNAUTHORIZED);
		}
	}
	
	
	/**
	 * @param request
	 * @param response
	 * @return ResponseEntity
	 * @throws JsonProcessingException 
	 */
	@RequestMapping(value="cancelRemainder", method=RequestMethod.POST)
	public ResponseEntity<String> cancelRemainder(@RequestBody ToDo toDo, HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
		
		log.debug("coming inside the cancelReminder method");
		
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		if( session != null && user != null ) {
			toDoService.cancelRemainder(toDo);
			ObjectMapper mapper = new ObjectMapper();
			ObjectNode root = mapper.createObjectNode();
			root.put("message", "remainder updated");
			String data = mapper.writeValueAsString(root);
			
			log.info( data ); 
            log.debug("reminder canceled");
			
			return new ResponseEntity<String>(data, HttpStatus.OK);
		}
		else {
			ObjectMapper mapper = new ObjectMapper();
			ObjectNode root = mapper.createObjectNode();
			root.put("message", "signIn Required");
			
			log.debug("not login");
			
			String data = mapper.writeValueAsString(root);
			return new ResponseEntity<String>(data, HttpStatus.UNAUTHORIZED);
		}
	}
	
	/**
	 * @param toDo
	 * @param request
	 * @param response
	 * @return
	 * @throws JsonProcessingException 
	 */
	@RequestMapping(value="setColor", method=RequestMethod.POST)
	public ResponseEntity<String> setColor (@RequestBody ToDo toDo, HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
		
		log.debug("coming inside setColor method");
		
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		if( session != null && user != null ) 
		{
			
			toDoService.setColor(toDo);
			
			ObjectMapper mapper = new ObjectMapper();
			ObjectNode root = mapper.createObjectNode();
			root.put("message", "color updated");
			String data = mapper.writeValueAsString(root);
			
			log.debug("set color is updates sucessfully");
			log.info( data ); 
			
			return new ResponseEntity<String>(data, HttpStatus.OK);
		}
		else {
			ObjectMapper mapper = new ObjectMapper();
			ObjectNode root = mapper.createObjectNode();
			
			root.put("message", "signIn Required");
			String data = mapper.writeValueAsString(root);
			
			log.debug("not login");
			
			return new ResponseEntity<String>(data, HttpStatus.UNAUTHORIZED);
		}
		
		
	}
	
	
	/**
	 * @param toDo
	 * @param request
	 * @param response
	 * @return ResponseEntity
	 * @throws JsonProcessingException
	 */
	@RequestMapping(value="moveToTrash", method=RequestMethod.POST)
	public ResponseEntity<String> moveToTrash(@RequestBody TrashToDo trashToDo, HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
		
		/*System.out.println("coming in the move to trash method");
		System.out.println("trsh object"+trashToDo.toString());
*/		
		log.debug("coming inside the moveToTrash method");
		
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		if( session != null && user != null ) {
			trashToDo.setUser(user);
			

			boolean result = toDoService.moveToTrash( trashToDo );
			if( result ) {
				ObjectMapper mapper = new ObjectMapper();
				ObjectNode root = mapper.createObjectNode();
				root.put("message", "toDo Coppied");
				root.putPOJO("todoCopy", trashToDo);
				
				log.debug("moved to trash sucessfully");
				
				String data = mapper.writeValueAsString(root);
				return new ResponseEntity<String>(data, HttpStatus.OK);
			}
			else {
				ObjectMapper mapper = new ObjectMapper();
				ObjectNode root = mapper.createObjectNode();
				root.put("message", "toDo not moved to trash");
				
               log.debug("not moved to trash");
				
				String data = mapper.writeValueAsString(root);
				return new ResponseEntity<String>(data, HttpStatus.NOT_ACCEPTABLE);
			}
			
		}
		else {
			ObjectMapper mapper = new ObjectMapper();
			ObjectNode root = mapper.createObjectNode();
			root.put("message", "login required");
			
			log.debug("not login");
			
			String data = mapper.writeValueAsString(root);
			return new ResponseEntity<String>(data, HttpStatus.UNAUTHORIZED);
		}
	}
	
	
	/**
	 * to get all the notes by using the usedId
	 * 
	 * @param UserId
	 * @param request
	 * @param response
	 * @return String message and status
	 * @throws JsonProcessingException
	 */
	@RequestMapping(value = "getTrashNotes", method = RequestMethod.GET)
	public ResponseEntity<String> getTrashNotes(HttpServletRequest request, HttpServletResponse response)
			throws JsonProcessingException {

		log.debug("coming inside the getTrashNotes");

		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		if (user != null && session != null) {
			
			List<ToDo> trashtodoList = toDoService.getTrashNotes(user.getId());
			System.out.println(user.getId());
			if (!trashtodoList.isEmpty()) {
				Collections.reverse(trashtodoList);
				ObjectMapper mapper = new ObjectMapper();
				ObjectNode root = mapper.createObjectNode();
				root.put("status", "success");
				root.putPOJO("todo", trashtodoList);
				root.putPOJO("user", user);
				String data = mapper.writeValueAsString(root);
				
				log.debug("sucessfully got the trash notes");
				log.info(data);
				
				return new ResponseEntity<String>(data, HttpStatus.OK);
			  } 
			else {
				ObjectMapper mapper = new ObjectMapper();
				ObjectNode root = mapper.createObjectNode();
				root.put("status", "notes are not there");
				root.putPOJO("todo", trashtodoList);
				root.putPOJO("user", user);
				String data = mapper.writeValueAsString(root);
				
				log.info(data);
                log.debug("didnt get trash notes");
                
				return new ResponseEntity<String>(data, HttpStatus.OK);
			}
		} 
		else {
			ObjectMapper mapper = new ObjectMapper();
			ObjectNode root = mapper.createObjectNode();
			root.put("status", "no user");
			String data = mapper.writeValueAsString(root);
			
			log.info(data);
			log.debug("not login");
			
			return new ResponseEntity<String>(data, HttpStatus.UNAUTHORIZED);
		}
	}

	
	/**
	 * to delete note
	 * 
	 * @param id
	 * @param request
	 * @param response
	 * @return String message and status
	 * @throws JsonProcessingException 
	 */
	
	@RequestMapping(value = "deleteNotePermanently/{id}", method = RequestMethod.POST)
	public ResponseEntity<String> deleteNotePermanently(@PathVariable("id") int id, HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
		
		log.debug("coming inside the deleteNotePermanently method");
		
		HttpSession session = request.getSession(false);
		User user = (User) session.getAttribute("user");
		if (session != null && user != null) {
			int result = toDoService.deleteNotePermanently(id);
			if (result != 0) {
				List<ToDo> trashToDoList = toDoService.getNotes(user.getId());
				ObjectMapper mapper = new ObjectMapper();
				ObjectNode root = mapper.createObjectNode();
				root.put("status", "success");
				root.putPOJO("todo", trashToDoList);
				String data = mapper.writeValueAsString(root);

				log.debug("note deleted permanently");
				log.info( "data"+data ); 

				return new ResponseEntity<String>(data, HttpStatus.OK);
				
			} else {
				ObjectMapper mapper = new ObjectMapper();
				ObjectNode root = mapper.createObjectNode();
				root.put("status", "toDoNot there");
				String data = mapper.writeValueAsString(root);
			    
				log.debug("not deleted permanently");
				log.info( data ); 
				
				return new ResponseEntity<String>(data, HttpStatus.NOT_FOUND);
			}
		}
		else{
		
			ObjectMapper mapper = new ObjectMapper();
			ObjectNode root = mapper.createObjectNode();
			root.put("status", "signIn first");
			String data = mapper.writeValueAsString(root);
	   		
			log.debug("not login");
			log.info( data );
			
		    return new ResponseEntity<String>(data, HttpStatus.UNAUTHORIZED);
		
		}
	}
	
	
	@RequestMapping(value = "/restore" , method=RequestMethod.POST)
	public ResponseEntity<String> restoreNote(@RequestBody TrashToDo trashToDo, HttpServletRequest request,
			HttpServletResponse response) throws JsonProcessingException {

		log.debug("coming inside the restore");
		
		ToDo toDo=new ToDo(trashToDo.getId(), trashToDo.getTitle(), trashToDo.getNote(), trashToDo.getRemainder(), trashToDo.getColor(), false, false, trashToDo.getUpDated(), trashToDo.getUser());

		HttpSession session = request.getSession(false);
		User user = (User) session.getAttribute("user");
		if (session != null && user != null) {
			toDo.setUser(user);
			
			log.info(toDo.getId());
			
			boolean result = toDoService.addNote(toDo);
		
			log.info(toDo.getId());
			
			if (result) {
				ObjectMapper mapper = new ObjectMapper();
				ObjectNode root = mapper.createObjectNode();
				root.put("status", "sucess");
				root.putPOJO("todo", toDo);
				String data = mapper.writeValueAsString(root);
				
				log.info(data);
				
				return new ResponseEntity<String>(data, HttpStatus.OK);
			} else {

				ObjectMapper mapper = new ObjectMapper();
				ObjectNode root = mapper.createObjectNode();
				root.put("status", "failure");
				String data = mapper.writeValueAsString(root);
				
				log.info(data);
				log.debug("restore not sucessful");
				
				return new ResponseEntity<String>(data, HttpStatus.NO_CONTENT);
			}
		}

		else {
			ObjectMapper mapper = new ObjectMapper();
			ObjectNode root = mapper.createObjectNode();
			root.put("status", "failure");
			String data = mapper.writeValueAsString(root);
			
			log.info(data);
			log.debug("not login");
			
			return new ResponseEntity<String>(data, HttpStatus.UNAUTHORIZED);
		}
	}
	
	
	@RequestMapping(value="/labling", method=RequestMethod.POST)
	public ResponseEntity<String> addLabel(@RequestBody Label label, HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException
	{
		log.debug("comin inside add label controller");
		
		HttpSession session=request.getSession();
		User user=(User) session.getAttribute("user");
		if(user!=null)
		{
		label.setUser(user);
		boolean result = toDoService.addLabel(label);
		if (result) {
			ObjectMapper mapper = new ObjectMapper();
			ObjectNode root = mapper.createObjectNode();
			root.put("status", "sucess");
			root.putPOJO("label", label);
			String data = mapper.writeValueAsString(root);
			
			log.debug("addlabel sucessfull");
			log.info(data);
			
			return new ResponseEntity<String>(data, HttpStatus.OK);
		} 
		else {

			ObjectMapper mapper = new ObjectMapper();
			ObjectNode root = mapper.createObjectNode();
			root.put("status", "failure");
			String data = mapper.writeValueAsString(root);
			
			log.debug("add label failure");
			log.info(data);
			
			return new ResponseEntity<String>(data, HttpStatus.NO_CONTENT);
		}
			
		}
		else
		{
			ObjectMapper mapper = new ObjectMapper();
			ObjectNode root = mapper.createObjectNode();
			root.put("status", "failure");
			String data = mapper.writeValueAsString(root);
			System.out.println(data);
			return new ResponseEntity<String>(data, HttpStatus.UNAUTHORIZED);
		}
				
	}
	
	
	@RequestMapping(value = "getLabel", method = RequestMethod.GET)
	public ResponseEntity<String> getLabel(HttpServletRequest request, HttpServletResponse response)
			throws JsonProcessingException {

		log.debug("coming inside the getLabel");

		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		if (user != null && session != null) {
			
			List<Label> todoLabel = toDoService.getLabel(user.getId());
			System.out.println(user.getId());
			if (!todoLabel.isEmpty()) {
				Collections.reverse(todoLabel);
				ObjectMapper mapper = new ObjectMapper();
				ObjectNode root = mapper.createObjectNode();
				root.put("status", "success");
				root.putPOJO("todo", todoLabel);
				root.putPOJO("user", user);
				String data = mapper.writeValueAsString(root);
			
			    log.info(data);
			    log.debug("getting the label");
			    
				return new ResponseEntity<String>(data, HttpStatus.OK);
			  } 
			else {
				ObjectMapper mapper = new ObjectMapper();
				ObjectNode root = mapper.createObjectNode();
				root.put("status", "labels are not there");
				root.putPOJO("todo", todoLabel);
				root.putPOJO("user", user);
				String data = mapper.writeValueAsString(root);
				
				log.debug("no label");
			    log.info(data);
				
				return new ResponseEntity<String>(data, HttpStatus.OK);
			}
		} 
		else {
			ObjectMapper mapper = new ObjectMapper();
			ObjectNode root = mapper.createObjectNode();
			root.put("status", "no user");
			String data = mapper.writeValueAsString(root);
			
			log.info(data);
			log.debug("not login");
			
			return new ResponseEntity<String>(data, HttpStatus.UNAUTHORIZED);
		}
	}
	
}
