package com.med.firstapp.rest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.med.firstapp.model.User;

public class UserService {


	private Map<Integer, User> users = new HashMap<>(); 
	
	public UserService(){

		users.put(1, new User(1, "User 1", "profession 1"));
		users.put(2, new User(2, "User 2", "profession 2"));
		users.put(3, new User(3, "User 3", "profession 3"));
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
