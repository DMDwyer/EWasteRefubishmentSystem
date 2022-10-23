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
import com.rest.main.model.ElectronicDevice;
import com.rest.main.repository.ElectronicDeviceRepository;


@SpringBootTest
@AutoConfigureMockMvc
public class ElectronicDeviceControllerUnitTests extends AbstractContainerBaseTest{
	
	@Autowired
	private ElectronicDeviceRepository deviceRepository;
	
	@Autowired
	private MockMvc mockMvc;

	@Test
	public void givenDevices_whenGetAllDevices_thenListOfDevices() throws Exception {
			
		System.out.println(mySQLContainer.getDatabaseName());
		System.out.println(mySQLContainer.getPassword());
		System.out.println(mySQLContainer.getUsername());
		System.out.println(mySQLContainer.getJdbcUrl());
		
		deviceRepository.deleteAll();
		
		// given - setup or precondition
		
		List<ElectronicDevice> mockDevices = new ArrayList<ElectronicDevice>();
		
		ElectronicDevice mockDevice1 = new ElectronicDevice();
		mockDevice1.setUserId(1);
		mockDevice1.setProject("IRE1");
		mockDevice1.setStatus("Checked In");
		mockDevice1.setCategory("PC");
		//mockDevice1.setModelNo("HP2102");
		//mockDevice1.setSerialNo("125-837-490");
		mockDevice1.setItemNo("IRE000001");
		mockDevice1.setQuantity(1);
		mockDevice1.setDateCreated(LocalDate.now());
		
		
		ElectronicDevice mockDevice2 = new ElectronicDevice();
		mockDevice2.setUserId(1);
		mockDevice2.setProject("IRE1");
		mockDevice2.setStatus("Checked In");
		mockDevice2.setCategory("Laptop");
		//mockDevice2.setModelNo("D3213");
		//mockDevice2.setSerialNo("126-937-460");
		mockDevice2.setItemNo("IRE000002");
		mockDevice2.setQuantity(1);
		mockDevice2.setDateCreated(LocalDate.now());
		
		ElectronicDevice mockDevice3 = new ElectronicDevice();
		mockDevice3.setUserId(1);
		mockDevice3.setProject("IRE1");
		mockDevice3.setStatus("Checked In");
		mockDevice3.setCategory("Phone");
		//mockDevice3.setModelNo("S4324");
		//mockDevice3.setSerialNo("195-867-491");
		mockDevice3.setItemNo("IRE000003");
		mockDevice3.setQuantity(1);
		mockDevice3.setDateCreated(LocalDate.now());
		
		mockDevices.add(mockDevice1);
		mockDevices.add(mockDevice2);
		mockDevices.add(mockDevice3);
		
		deviceRepository.saveAll(mockDevices);
		
		// when - action
		
		ResultActions response = mockMvc.perform(MockMvcRequestBuilders.get("/api/devices/all"));
		
		
		// then - verify the output
		response.andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
		response.andExpect(MockMvcResultMatchers.jsonPath("$.size()", CoreMatchers.is(mockDevices.size())));
			
	}
	
	@Test
	public void givenDevice_whenGetDeviceById_thenReturnDeviceWithId() throws Exception {
		
		ElectronicDevice mockDevice = new ElectronicDevice();
		mockDevice.setUserId(1);
		mockDevice.setProject("IRE1");
		mockDevice.setStatus("Checked In");
		mockDevice.setCategory("Phone");
		//mockDevice.setModelNo("S4324");
		//mockDevice.setSerialNo("195-867-491");
		mockDevice.setItemNo("IRE000001");
		mockDevice.setQuantity(1);
		mockDevice.setDateCreated(LocalDate.now());
		
		deviceRepository.save(mockDevice);
		
		ResultActions response = mockMvc.perform(MockMvcRequestBuilders.get("/api/devices/" + mockDevice.getId()));
		
		response.andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
		//response.andExpect(MockMvcResultMatchers.jsonPath("$.user.name").value(mockUser.getName()));
	}
	
	@Test
	public void givenDevice_whenDeviceAdded_thenCheckStatus() throws Exception {
		
		System.out.println(mySQLContainer.getDatabaseName());
		System.out.println(mySQLContainer.getPassword());
		System.out.println(mySQLContainer.getUsername());
		System.out.println(mySQLContainer.getJdbcUrl());
		
		// given - setup or precondition
		
		ElectronicDevice mockDevice = new ElectronicDevice();
		mockDevice.setUserId(1);
		mockDevice.setProject("IRE1");
		mockDevice.setStatus("Checked In");
		mockDevice.setCategory("Phone");
		//mockDevice.setModelNo("S4324");
		//mockDevice.setSerialNo("195-867-491");
		mockDevice.setItemNo("IRE000001");
		mockDevice.setQuantity(1);
		mockDevice.setDateCreated(LocalDate.now());
		
		//userService.saveUser(mockUser);
		
		ObjectMapper obj = new ObjectMapper();
		
		obj.registerModule(new JavaTimeModule()); 
		
		String jsonMockDevice = obj.writeValueAsString(mockDevice);
		
		// when - action
		
		ResultActions response = mockMvc.perform(MockMvcRequestBuilders.post("/api/devices/add").contentType(MediaType.APPLICATION_JSON).content(jsonMockDevice));
		
		
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
	public void givenDevice_whenDeviceDeleted_thenCheckStatus() throws Exception {
		
		System.out.println(mySQLContainer.getDatabaseName());
		System.out.println(mySQLContainer.getPassword());
		System.out.println(mySQLContainer.getUsername());
		System.out.println(mySQLContainer.getJdbcUrl());
		
		// given - setup or precondition
		ElectronicDevice mockDevice = new ElectronicDevice();
		mockDevice.setUserId(1);
		mockDevice.setProject("IRE1");
		mockDevice.setStatus("Checked In");
		mockDevice.setCategory("Phone");
		//mockDevice.setModelNo("S4324");
		//mockDevice.setSerialNo("195-867-491");
		mockDevice.setItemNo("IRE000001");
		mockDevice.setQuantity(1);
		mockDevice.setDateCreated(LocalDate.now());
		
		//userService.saveUser(mockUser);
		
		deviceRepository.save(mockDevice);
		
		// when - action
		
		ResultActions response = mockMvc.perform(MockMvcRequestBuilders.delete("/api/devices/" + mockDevice.getId()));
		
		// then - verify the output
		response.andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
		//response.andExpect(MockMvcResultMatchers.jsonPath("$.size()", CoreMatchers.is(pcs.size())));
		
	}
	
	
}