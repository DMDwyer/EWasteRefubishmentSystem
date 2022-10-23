package com.rest.main.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.Table;

import lombok.Data;


	
@Data
@Entity
@Inheritance
@Table(name="edevices")
public class ElectronicDevice {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "user_id", nullable = false)
	private long userId;
	
	@Column(name = "project", nullable = false)
	private String project;
	
	@Column(name = "status", nullable = false)
	private String status;
	
	@Column(name = "category", nullable = false)
	private String category;
	
	@Column(name = "item_no", nullable = false)
	private String itemNo;
	
	@Column(name = "quantity")
	private int quantity;
	
	@Column(name = "comment")
	private String comment;
	
	@Column(name = "date_created")
	private LocalDate dateCreated;
	
	@Column(name = "weight")
	private double weight;
	
	@Column(name = "location")
	private String location;
	
	
	public ElectronicDevice() {
		
	}
	
	

}	