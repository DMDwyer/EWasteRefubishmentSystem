package com.rest.main.model;

import javax.persistence.Column;
import javax.persistence.Entity;

import lombok.Data;

@Data
@Entity
public class Laptop extends PC{

	@Column (name="screen_size")
	private int screenSize;
	
	Laptop(String cpu, String ram, String storage, String modelNo, String serialNo, char grade, int screenSize) {
		super(cpu, ram, storage, modelNo, serialNo, grade);
		this.screenSize = screenSize;
	}
	
}
