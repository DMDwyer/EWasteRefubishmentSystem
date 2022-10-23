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
import com.rest.main.model.Project;
import com.rest.main.repository.ProjectRepository;

@SpringBootTest
@AutoConfigureMockMvc
public class ProjectControllerUnitTests extends AbstractContainerBaseTest{
	
	@Autowired
	private ProjectRepository projectRepository;
	
	@Autowired
	private MockMvc mockMvc;

	@Test
	public void givenProjects_whenGetAllProjects_thenListOfProjects() throws Exception {
			
		System.out.println(mySQLContainer.getDatabaseName());
		System.out.println(mySQLContainer.getPassword());
		System.out.println(mySQLContainer.getUsername());
		System.out.println(mySQLContainer.getJdbcUrl());
		
		projectRepository.deleteAll();
		
		// given - setup or precondition
		
		List<Project> mockProjects = new ArrayList<Project>();
		
		Project mockProject1 = new Project();
		mockProject1.setName("IRE1");
		mockProject1.setCompany("SampleInc");
		mockProject1.setAddress("Dublin");
		mockProject1.setStatus("To Be Checked In");
		mockProject1.setDateCreated(LocalDate.now());
		mockProject1.setUserId(1);
		
		Project mockProject2 = new Project();
		mockProject2.setName("IRE2");
		mockProject2.setCompany("SampleLtd");
		mockProject2.setAddress("Galway");
		mockProject2.setStatus("To Be Checked In");
		mockProject2.setDateCreated(LocalDate.now());
		mockProject2.setUserId(2);
		
		Project mockProject3 = new Project();
		mockProject3.setName("IRE3");
		mockProject3.setCompany("SampleCo");
		mockProject3.setAddress("Kerry");
		mockProject3.setStatus("To Be Checked In");
		mockProject3.setDateCreated(LocalDate.now());
		mockProject3.setUserId(3);
		
		mockProjects.add(mockProject1);
		mockProjects.add(mockProject2);
		mockProjects.add(mockProject3);
		
		projectRepository.saveAll(mockProjects);
		
		// when - action
		
		ResultActions response = mockMvc.perform(MockMvcRequestBuilders.get("/api/projects/all"));
		
		
		// then - verify the output
		response.andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
		response.andExpect(MockMvcResultMatchers.jsonPath("$.size()", CoreMatchers.is(mockProjects.size())));
			
	}
	
	@Test
	public void givenProject_whenGetProjectById_thenReturnProjectWithId() throws Exception {
		
		Project mockProject = new Project();
		mockProject.setName("IRE1");
		mockProject.setCompany("SampleInc");
		mockProject.setAddress("Dublin");
		mockProject.setStatus("To Be Checked In");
		mockProject.setDateCreated(LocalDate.now());
		mockProject.setUserId(1);
		
		projectRepository.save(mockProject);
		
		ResultActions response = mockMvc.perform(MockMvcRequestBuilders.get("/api/projects/" + mockProject.getProjectId()));
		
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
		
		Project mockProject = new Project();
		mockProject.setName("IRE1");
		mockProject.setCompany("SampleInc");
		mockProject.setAddress("Dublin");
		mockProject.setStatus("To Be Checked In");
		mockProject.setDateCreated(LocalDate.now());
		mockProject.setUserId(1);
		
		//userService.saveUser(mockUser);
		
		ObjectMapper obj = new ObjectMapper();
		
		obj.registerModule(new JavaTimeModule()); 
		
		String jsonMockProject = obj.writeValueAsString(mockProject);
		
		// when - action
		
		ResultActions response = mockMvc.perform(MockMvcRequestBuilders.post("/api/projects/add").contentType(MediaType.APPLICATION_JSON).content(jsonMockProject));
		
		
		// then - verify the output
		response.andExpect(MockMvcResultMatchers.status().is2xxSuccessful()).andReturn();
		//response.andExpect(MockMvcResultMatchers.jsonPath("$.size()", CoreMatchers.is(pcs.size())));
		
	}
	
	/*
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
		//response.andExpect(MockMvcResultMatchers.jsonPath("$.size()", CoreMatchers.is(pcs.size())));
		
	}
	
	*/
	
	@Test
	public void givenProject_whenProjectDeleted_thenCheckStatus() throws Exception {
		
		System.out.println(mySQLContainer.getDatabaseName());
		System.out.println(mySQLContainer.getPassword());
		System.out.println(mySQLContainer.getUsername());
		System.out.println(mySQLContainer.getJdbcUrl());
		
		// given - setup or precondition
		
		Project mockProject = new Project();
		mockProject.setName("IRE1");
		mockProject.setCompany("SampleInc");
		mockProject.setAddress("Dublin");
		mockProject.setStatus("To Be Checked In");
		mockProject.setDateCreated(LocalDate.now());
		mockProject.setUserId(1);
		
		//userService.saveUser(mockUser);
		
		projectRepository.save(mockProject);
		
		// when - action
		
		ResultActions response = mockMvc.perform(MockMvcRequestBuilders.delete("/api/projects/" + mockProject.getProjectId()));
		
		// then - verify the output
		response.andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
		//response.andExpect(MockMvcResultMatchers.jsonPath("$.size()", CoreMatchers.is(pcs.size())));
		
	}
	
	
}
