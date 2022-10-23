package com.rest.main.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long userId;
	
	@Column(name = "email", nullable = false)
	private String email;
	
	@Column(name = "name", nullable = false)
	private String name;
	
	@Column(name = "password", nullable = false)
	private String password;
	
	@Column(name = "access_level")
	private int accessLevel = 3;
	
	@Column(name = "date_created")
	private LocalDate dateCreated = LocalDate.now();
	
	
	public User() {
		
	}
	
	public User(String email, String name, String password, int accessLevel, LocalDate dateCreated) {
		this.email = email;
		this.name = name;
		this.password = password;
		this.accessLevel = accessLevel;
		this.dateCreated = dateCreated;
	}
	

}
