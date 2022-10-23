package com.rest.main.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.rest.main.exception.ResourceNotFoundException;
import com.rest.main.model.User;
import com.rest.main.repository.UserRepository;
import com.rest.main.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	private UserRepository userRepository;
	
	public UserServiceImpl(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}
	
	@Override
	public User saveUser(User user) {
		return userRepository.save(user);
	}

	@Override
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	@Override
	public User getUserById(long userId) {
		return userRepository.findById(userId).orElseThrow(() ->
		new ResourceNotFoundException("User", "User Id", userId));
	}

	@Override
	public User updateUserPassword(String newPassword, long userId) {
		
		User existingUser = userRepository.findById(userId).orElseThrow(() ->
				new ResourceNotFoundException("User", "User Id", userId));
		
		existingUser.setPassword(newPassword);
		
		userRepository.save(existingUser);
		return existingUser;
	}
	
	@Override
	public User updateUserAccess(int newLevel, long userId) {
		
		User existingUser = userRepository.findById(userId).orElseThrow(() -> 
				new ResourceNotFoundException("User", "User Id", userId));
		
		existingUser.setAccessLevel(newLevel);
		
		userRepository.save(existingUser);
		return existingUser;
	}

	@Override
	public void deleteUser(long userId) {
		userRepository.findById(userId).orElseThrow(() ->
				new ResourceNotFoundException("User", "User Id", userId));
		
		userRepository.deleteById(userId);
		
	}

}
