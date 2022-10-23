package com.rest.main;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.springframework.boot.test.context.SpringBootTest;

import com.rest.main.controller.ElectronicDeviceControllerUnitTests;
import com.rest.main.controller.ProjectControllerUnitTests;
import com.rest.main.controller.UserControllerUnitTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({UserControllerUnitTest.class, ProjectControllerUnitTests.class, ElectronicDeviceControllerUnitTests.class})
public class EWasteRefubishmentSystemApplicationTests {
	
	@Test
	public void contextLoads() {
		
	}
	
}
