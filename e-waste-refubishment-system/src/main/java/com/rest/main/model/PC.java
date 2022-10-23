package com.rest.main.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Entity
public class PC extends ElectronicDevice{

	@Column(name = "cpu", nullable = false)
	private String cpu;
	
	@Column(name = "ram", nullable = false)
	private String ram;
	
	@Column(name = "storage", nullable = false)
	private String storage;
	
	@Column(name = "model_no", nullable = false)
	private String modelNo;
	
	@Column(name = "serial_no", nullable = false)
	private String serialNo;
	
	@Column (name = "grade", nullable = false)
	private char grade;
	
	
}
