package com.rest.main.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.mockito.ArgumentMatchers.anyLong;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import com.rest.main.exception.ResourceNotFoundException;
import com.rest.main.model.User;
import com.rest.main.repository.UserRepository;
import com.rest.main.service.impl.UserServiceImpl;

@SpringBootTest(webEnvironment=SpringBootTest.WebEnvironment.NONE)
@RunWith(MockitoJUnitRunner.class)
public class UserServiceUnitTest {
	
	@InjectMocks
	//@Autowired
	private UserServiceImpl userService;
	
	@Mock
	private UserRepository userRepository;
	
	User mockUser;
	
	@BeforeEach 
	void setUp() throws Exception
	{
		MockitoAnnotations.openMocks(this);
		
		mockUser = new User();
		mockUser.setUserId(1);
		mockUser.setEmail("sample@email.com");
		mockUser.setName("JohnD");
		mockUser.setPassword("admin");
		mockUser.setAccessLevel(1);
		mockUser.setDateCreated(LocalDate.now());
	}
	
	
	@Test
	public void givenUser_whenUserAdded_thenConfirmNotNull() throws Exception {
		
		// given - setup or precondition
		
		
		when(userRepository.findById(anyLong())).thenReturn(Optional.of(mockUser));
		
		System.out.println(mockUser.toString());
		
		// when - action
		//Test adding the user
		
		//userService.saveUser(mockUser);
		
		User newUser = userService.getUserById(1);
		
		System.out.println(newUser.toString());
		
		// then - verify the output
		assertNotNull(newUser);
		assertNotNull(newUser.getUserId());
		assertEquals("sample@email.com", newUser.getEmail());
		assertEquals("JohnD", newUser.getName());
		assertEquals("admin", newUser.getPassword());
		assertEquals(1, newUser.getAccessLevel());
		
	}
	
	@Test
	public void givenUser_whenGetUserById_thenThrowResourceNotFoundException() throws Exception {
		
		//when(userRepository.findById(anyLong())).thenReturn(null);
		
		assertThrows(ResourceNotFoundException.class, () ->{userService.getUserById(2);});
		
	}
	
	@Test
	public void givenUserInformation_whenUserAdded_thenVerifyUserDetails() throws Exception {
		
		when(userRepository.save(any(User.class))).thenReturn(mockUser);
		
		User newUser = userService.saveUser(mockUser);
		
		assertNotNull(newUser);
		assertEquals(mockUser.getEmail(), newUser.getEmail());
		
	}
	
	@Test
	public void givenUsers_whenGetAllUsers_thenListAllUsers() throws Exception {
		
		List<User> mockUsers = new ArrayList<>();
		
		mockUsers.add(mockUser);
		mockUsers.add(mockUser);
		
		when(userRepository.findAll()).thenReturn(mockUsers);
		
		List<User> newUsers = userService.getAllUsers();
		
		assertEquals(newUsers.size(), mockUsers.size());
		
	}
	
	@Test
	public void givenUser_whenGivenId_thenDeleteUserWithId() throws Exception {
		
		
		when(userRepository.save(any(User.class))).thenReturn(mockUser);
		
		User newUser = userService.saveUser(mockUser);
		
		userService.deleteUser(newUser.getUserId());
		
		assertNull(newUser);
		
	}
	
	

}
