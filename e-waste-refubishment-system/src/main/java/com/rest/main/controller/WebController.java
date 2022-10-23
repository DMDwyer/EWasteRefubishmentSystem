package com.rest.main.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rest.main.model.ElectronicDevice;
import com.rest.main.model.User;

@Controller
public class WebController {
	
	@Autowired
	private UserController userController;
	
	@Autowired
	private ElectronicDeviceController deviceController;
	
	@RequestMapping("/signUp")
	public String signUp(Model model) {
		
		User user = new User();
		model.addAttribute("user", user);
		return "addUser";
	}
	
	@PostMapping("/addAccount")
	public String addAccount(User user)
	{
		userController.saveUser(user);
		return "redirect:/user/list";
	}
	
	@RequestMapping("/user/list")
	public String listUsersPage(Model model)
	{
		List<User> users = userController.getAllUsers();
		model.addAttribute("userList", users);
		return "userList";	
	}
	
	@RequestMapping("/device/list")
	public String listDevicesPage(Model model)
	{
		List<ElectronicDevice> devices = deviceController.getAllDevices();
		model.addAttribute("deviceList", devices);
		return "deviceList";
	}
	
}
