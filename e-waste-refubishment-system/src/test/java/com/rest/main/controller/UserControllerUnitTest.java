package com.rest.main.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.rest.main.model.User;
import com.rest.main.repository.UserRepository;


@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerUnitTest extends AbstractContainerBaseTest{
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private MockMvc mockMvc;

	@Test
	public void givenUsers_whenGetAllUsers_thenListOfUsers() throws Exception {
			
		System.out.println(mySQLContainer.getDatabaseName());
		System.out.println(mySQLContainer.getPassword());
		System.out.println(mySQLContainer.getUsername());
		System.out.println(mySQLContainer.getJdbcUrl());
		
		userRepository.deleteAll();
		
		// given - setup or precondition
		
		List<User> mockUsers = new ArrayList<User>();
		
		User mockUser1 = new User();
		mockUser1.setEmail("sample2@email.com");
		mockUser1.setName("JaneD");
		mockUser1.setPassword("admin2");
		mockUser1.setAccessLevel(1);
		mockUser1.setDateCreated(LocalDate.now());
		
		User mockUser2 = new User();
		mockUser2.setEmail("sample3@email.com");
		mockUser2.setName("JaneE");
		mockUser2.setPassword("admin3");
		mockUser2.setAccessLevel(1);
		mockUser2.setDateCreated(LocalDate.now());
		
		User mockUser3 = new User();
		mockUser3.setEmail("sample4@email.com");
		mockUser3.setName("JaneF");
		mockUser3.setPassword("admin4");
		mockUser3.setAccessLevel(1);
		mockUser3.setDateCreated(LocalDate.now());
		
		mockUsers.add(mockUser1);
		mockUsers.add(mockUser2);
		mockUsers.add(mockUser3);
		
		userRepository.saveAll(mockUsers);
		
		// when - action
		
		ResultActions response = mockMvc.perform(MockMvcRequestBuilders.get("/api/user/all"));
		
		
		// then - verify the output
		response.andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
		response.andExpect(MockMvcResultMatchers.jsonPath("$.size()", CoreMatchers.is(mockUsers.size())));
			
	}
	
	@Test
	public void givenUser_whenGetUserById_thenReturnUserWithId() throws Exception {
		
		User mockUser = new User();
		mockUser.setEmail("sample@email.com");
		mockUser.setName("JohnD");
		mockUser.setPassword("admin");
		mockUser.setAccessLevel(1);
		mockUser.setDateCreated(LocalDate.now());
		
		userRepository.save(mockUser);
		
		ResultActions response = mockMvc.perform(MockMvcRequestBuilders.get("/api/user/" + mockUser.getUserId()));
		
		response.andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
		//response.andExpect(MockMvcResultMatchers.jsonPath("$.user.name").value(mockUser.getName()));
	}
	
	@Test
	public void givenUser_whenUserAdded_thenCheckStatus() throws Exception {
		
		System.out.println(mySQLContainer.getDatabaseName());
		System.out.println(mySQLContainer.getPassword());
		System.out.println(mySQLContainer.getUsername());
		System.out.println(mySQLContainer.getJdbcUrl());
		
		// given - setup or precondition
		
		User mockUser = new User();
		mockUser.setEmail("sample@email.com");
		mockUser.setName("JohnD");
		mockUser.setPassword("admin");
		mockUser.setAccessLevel(1);
		mockUser.setDateCreated(LocalDate.now());
		
		//userService.saveUser(mockUser);
		
		ObjectMapper obj = new ObjectMapper();
		
		obj.registerModule(new JavaTimeModule()); 
		
		String jsonMockUser = obj.writeValueAsString(mockUser);
		
		// when - action
		
		ResultActions response = mockMvc.perform(MockMvcRequestBuilders.post("/api/user/add").contentType(MediaType.APPLICATION_JSON).content(jsonMockUser));
		
		
		// then - verify the output
		response.andExpect(MockMvcResultMatchers.status().is2xxSuccessful()).andReturn();
	}
	
	@Test
	public void givenUser_whenUserPasswordChanged_thenCheckStatus() throws Exception {
		
		System.out.println(mySQLContainer.getDatabaseName());
		System.out.println(mySQLContainer.getPassword());
		System.out.println(mySQLContainer.getUsername());
		System.out.println(mySQLContainer.getJdbcUrl());
		
		// given - setup or precondition
		
		User mockUser = new User();
		mockUser.setEmail("sample@email.com");
		mockUser.setName("JohnD");
		mockUser.setPassword("admin");
		mockUser.setAccessLevel(1);
		mockUser.setDateCreated(LocalDate.now());
		
		//userService.saveUser(mockUser);
		
		userRepository.save(mockUser);
		
		// when - action
		
		ResultActions response = mockMvc.perform(MockMvcRequestBuilders.put("/api/user/changepassword/" + mockUser.getUserId()).content("admin1234!"));
		
		// then - verify the output
		response.andExpect(MockMvcResultMatchers.status().is2xxSuccessful()).andReturn();		
	}
	
	@Test
	public void givenUser_whenUserAccessChanged_thenCheckStatus() throws Exception {
		
		System.out.println(mySQLContainer.getDatabaseName());
		System.out.println(mySQLContainer.getPassword());
		System.out.println(mySQLContainer.getUsername());
		System.out.println(mySQLContainer.getJdbcUrl());
		
		// given - setup or precondition
		
		User mockUser = new User();
		mockUser.setEmail("sample@email.com");
		mockUser.setName("JohnD");
		mockUser.setPassword("admin");
		mockUser.setAccessLevel(2);
		mockUser.setDateCreated(LocalDate.now());
		
		//userService.saveUser(mockUser);
		
		userRepository.save(mockUser);
		
		// when - action
		
		int newAccess = 1;
		
		ObjectMapper obj = new ObjectMapper();
		
		obj.registerModule(new JavaTimeModule()); 
		
		String jsonNewAccess = obj.writeValueAsString(newAccess);
		
		ResultActions response = mockMvc.perform(MockMvcRequestBuilders.put("/api/user/changeaccess/" + mockUser.getUserId()).contentType(MediaType.APPLICATION_JSON).content(jsonNewAccess));
		
		// then - verify the output
		response.andExpect(MockMvcResultMatchers.status().is2xxSuccessful()).andReturn();		
	}
	
	@Test
	public void givenUser_whenUserDeleted_thenCheckStatus() throws Exception {
		
		System.out.println(mySQLContainer.getDatabaseName());
		System.out.println(mySQLContainer.getPassword());
		System.out.println(mySQLContainer.getUsername());
		System.out.println(mySQLContainer.getJdbcUrl());
		
		// given - setup or precondition
		
		User mockUser = new User();
		mockUser.setEmail("sample@email.com");
		mockUser.setName("JohnD");
		mockUser.setPassword("admin");
		mockUser.setAccessLevel(2);
		mockUser.setDateCreated(LocalDate.now());
		
		//userService.saveUser(mockUser);
		
		userRepository.save(mockUser);
		
		// when - action
		
		ResultActions response = mockMvc.perform(MockMvcRequestBuilders.delete("/api/user/delete/" + mockUser.getUserId()));
		
		// then - verify the output
		response.andExpect(MockMvcResultMatchers.status().isOk()).andReturn();		
	}
	
	
}
