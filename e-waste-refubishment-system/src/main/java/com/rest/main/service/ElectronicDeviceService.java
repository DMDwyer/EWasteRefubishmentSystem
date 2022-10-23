package com.rest.main.service;

import java.util.List;

import com.rest.main.model.ElectronicDevice;
import com.rest.main.model.PC;
import com.rest.main.model.Phone;

public interface ElectronicDeviceService {
	
	ElectronicDevice saveDevice(ElectronicDevice device);
	
	List<ElectronicDevice> getAllDevices();
	
	ElectronicDevice getDeviceById(long id);
	

}
