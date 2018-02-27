package com.med.firstapp.rest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.med.firstapp.model.Email;
import com.med.firstapp.model.User;

public class UserService {


	private Map<Integer, User> users = new HashMap<>();

	public UserService(){

		User user = new User(1, "User 1", "profession 1");
		List<Email> emails = new ArrayList<>();
		emails.add(new Email("user1@home.com", "home"));
		user.setEmails(emails);
		users.put(1, user);


		user = new User(2, "User 2", "profession 2");
		emails = new ArrayList<>();
		emails.add(new Email("user2@work.com", "work"));
		user.setEmails(emails);
		users.put(2, user);


		user = new User(3, "User 3", "profession 3");
		emails = new ArrayList<>();
		emails.add(new Email("user3@home.com", "home"));
		emails.add(new Email("user3@work.com", "work"));
		user.setEmails(emails);
		users.put(3, user);

	}

	public List<User> getUsers() {
		return new ArrayList<User>(users.values());
	}

	public User addUser(User user){

		user.setId(users.size() + 1);
		users.put(user.getId(), user);
		return user;
	}

	public User getUserById(int userId) {
		return users.get(userId);
	}

}
