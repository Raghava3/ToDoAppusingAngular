package com.bridgelabz.todoapp.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
@Entity
@Table(name="TrashTodo")
public class TrashToDo {

	@Id
	@GenericGenerator(name = "gen", strategy = "increment")
	@GeneratedValue(generator = "gen")
	private int id;
	private String title;
	private String note;
	private Date remainder;
	private String color;
	private Date upDated = new Date();

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn
	private User user;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "TrashToDo [id=" + id + ", title=" + title + ", note=" + note + ", remainder=" + remainder + ", color="
				+ color + ", upDated=" + upDated + ", user=" + user + "]";
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Date getRemainder() {
		return remainder;
	}

	public void setRemainder(Date remainder) {
		this.remainder = remainder;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public Date getUpDated() {
		return upDated;
	}

	public void setUpDated(Date upDated) {
		this.upDated = upDated;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
}
