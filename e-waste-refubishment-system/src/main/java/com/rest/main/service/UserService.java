package com.rest.main.service;

import java.util.List;

import com.rest.main.model.User;

public interface UserService {
	
	User saveUser(User user);
	
	List<User> getAllUsers();
	
	User getUserById(long userId);
	
	User updateUserPassword(String newPassword, long userId);
	
	User updateUserAccess(int newLevel, long userId);
	
	void deleteUser(long userId);

}
