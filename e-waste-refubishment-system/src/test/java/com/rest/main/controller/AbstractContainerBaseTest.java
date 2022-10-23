package com.rest.main.controller;

import org.testcontainers.containers.MySQLContainer;

public class AbstractContainerBaseTest {
	
	protected static final MySQLContainer mySQLContainer;
	
	static {
		mySQLContainer = new MySQLContainer("mysql:latest");
		
		mySQLContainer.start();
	}

}
