package com.rest.main.repository;


import static org.hamcrest.CoreMatchers.equalTo; 
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;

import com.rest.main.exception.ResourceNotFoundException;
import com.rest.main.model.User;

@DataJpaTest
@SpringBootTest
public class UserRepositoryIntegrationTest {

	@Autowired
	private TestEntityManager entityManager;
	
	@Autowired
	private UserRepository userRepository;
	
	@Test
	public void testFindById() {
		
		//setup data scenario
		User aNewUser = new User();
		aNewUser.setEmail("sample@email.com");
		aNewUser.setName("JohnD");
		aNewUser.setPassword("admin");
		aNewUser.setAccessLevel(1);
		aNewUser.setDateCreated(LocalDate.now());
		entityManager.persist(aNewUser);
		
		//Find an inserted record using repository class
		User foundUser = userRepository.findById(aNewUser.getUserId()).orElseThrow(() ->
		new ResourceNotFoundException("User", "User Id", aNewUser.getUserId()));
		
		//Assertion
		assertThat(foundUser.getUserId(), is(equalTo(1)));
		
	}
}
