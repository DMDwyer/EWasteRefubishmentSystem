package com.rest.main.model;

import javax.persistence.Column;
import javax.persistence.Entity;

import lombok.Data;

@Data
@Entity
public class Phone extends ElectronicDevice{
	
	@Column(name = "model_no", nullable = false)
	private String modelNo;
	
	@Column(name = "sku", nullable = false)
	private String sku;
	
	@Column (name = "grade", nullable = false)
	private char grade;

}
