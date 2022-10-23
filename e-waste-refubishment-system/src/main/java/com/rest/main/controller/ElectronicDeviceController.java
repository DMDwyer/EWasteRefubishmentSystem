package com.rest.main.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rest.main.model.ElectronicDevice;
import com.rest.main.model.Laptop;
import com.rest.main.model.Phone;
import com.rest.main.model.PC;
import com.rest.main.service.ElectronicDeviceService;


@RestController
@RequestMapping("/api/devices")
public class ElectronicDeviceController {
	
	private ElectronicDeviceService deviceService;

	public ElectronicDeviceController(ElectronicDeviceService deviceService) {
		super();
		this.deviceService = deviceService;
	}
	
	//build create PC REST API
	@PostMapping("/add")
	public ResponseEntity<ElectronicDevice> saveDevice(@RequestBody ElectronicDevice device){
		return new ResponseEntity<ElectronicDevice>(deviceService.saveDevice(device), HttpStatus.CREATED);
	}
	
	@PostMapping("/pc/add")
	public ResponseEntity<ElectronicDevice> savePC(@RequestBody PC pc){
		return new ResponseEntity<ElectronicDevice>(deviceService.saveDevice(pc), HttpStatus.CREATED);
	}
	
	@PostMapping("/laptop/add")
	public ResponseEntity<ElectronicDevice> saveLaptop(@RequestBody Laptop laptop){
		return new ResponseEntity<ElectronicDevice>(deviceService.saveDevice(laptop), HttpStatus.CREATED);
	}
	
	@PostMapping("/mobile/add")
	public ResponseEntity<ElectronicDevice> savePhone(@RequestBody Phone phone){
		return new ResponseEntity<ElectronicDevice>(deviceService.saveDevice(phone), HttpStatus.CREATED);
	}
	
	
	// build get all PC's REST API
	@GetMapping("/all")
	public List<ElectronicDevice> getAllDevices(){
		return deviceService.getAllDevices();
	}
	

}
