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
@Table(name="projects")
public class Project {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long projectId;
	
	@Column(name = "name", nullable = false)
	private String name;
	
	@Column(name = "company", nullable = false)
	private String company;
	
	@Column(name = "address", nullable = false)
	private String address;
	
	@Column(name = "status", nullable = false)
	private String status;
	
	@Column(name = "date_created")
	private LocalDate dateCreated = LocalDate.now();
	
	@Column(name = "user_id", nullable = false)
	private long userId;
	
}
	

