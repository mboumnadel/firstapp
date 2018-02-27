package com.med.firstapp.model;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	private int id;
	private String name;
	private String profession;
	private List<Email> emails = new ArrayList<>();

	public User(){}

	public User(int id, String name, String profession){
		this.id = id;
		this.name = name;
		this.profession = profession;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getProfession() {
      return profession;
   }

	public void setProfession(String profession) {
		this.profession = profession + " from User";
	}

	public List<Email> getEmails() {
		return emails;
	}

	public void setEmails(List<Email> emails) {
		this.emails = emails;
	}

	@Override
	public String toString() {
		return "User[Id=" + id + ", Name=" + name + ", Profession=" + profession + "]";
	}
}