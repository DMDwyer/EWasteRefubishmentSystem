package com.rest.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rest.main.model.ElectronicDevice;


public interface ElectronicDeviceRepository extends JpaRepository<ElectronicDevice, Long>{

}
