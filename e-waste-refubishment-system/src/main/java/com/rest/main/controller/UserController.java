package com.rest.main.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rest.main.model.User;
import com.rest.main.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {
	
	private UserService userService;
	
	public UserController(UserService userService) {
		super();
		this.userService = userService;
	}
	

	//-----------REST API Backend Layer----------
	
	@PostMapping("/add")
	public ResponseEntity<User> saveUser(@RequestBody User user){
		return new ResponseEntity<User>(userService.saveUser(user), HttpStatus.CREATED);
	}
	
	@GetMapping("/all")
	public List<User> getAllUsers(){
		return userService.getAllUsers();
	}
	
	//Change id to userId
	@GetMapping("{id}")
	public ResponseEntity<User> getUserById(@PathVariable("id") long userId){
		return new ResponseEntity<User>(userService.getUserById(userId), HttpStatus.OK);
	}
	
	@PutMapping("/changepassword/{id}")
	public ResponseEntity<User> updateUserPassword(@PathVariable("id") long id, @RequestBody String newPassword){
		return new ResponseEntity<User>(userService.updateUserPassword(newPassword, id), HttpStatus.OK);
	}
	
	
	@PutMapping("/changeaccess/{id}")
	public ResponseEntity<User> updateUserAccess(@PathVariable("id") long id, @RequestBody int newLevel){
		return new ResponseEntity<User>(userService.updateUserAccess(newLevel, id), HttpStatus.OK);
	}
	
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteUser(@PathVariable("id") long id){
		
		userService.deleteUser(id);
		
		return new ResponseEntity<String>("User with id: " + id +" has been deleted", HttpStatus.OK);
	}
	

}
