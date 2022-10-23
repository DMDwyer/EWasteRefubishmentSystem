package com.rest.main.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.rest.main.exception.ResourceNotFoundException;
import com.rest.main.model.ElectronicDevice;
import com.rest.main.model.PC;
import com.rest.main.model.Phone;
import com.rest.main.repository.ElectronicDeviceRepository;
import com.rest.main.service.ElectronicDeviceService;

@Service
public class ElectronicDeviceServiceImpl implements ElectronicDeviceService{

	private ElectronicDeviceRepository deviceRepository;
	
	public ElectronicDeviceServiceImpl(ElectronicDeviceRepository deviceRepository) {
		super();
		this.deviceRepository = deviceRepository;
	}
	
	@Override
	public ElectronicDevice saveDevice(ElectronicDevice device) {
		return deviceRepository.save(device);
	}
	
	@Override
	public List<ElectronicDevice> getAllDevices() {
		return deviceRepository.findAll();
	}
	
	@Override
	public ElectronicDevice getDeviceById(long id) {
		Optional<ElectronicDevice> device = deviceRepository.findById(id);
		
		if(device.isPresent()) {
			return device.get();
		}else {
			throw new ResourceNotFoundException("Device", "id", id);
		}
	}

}
