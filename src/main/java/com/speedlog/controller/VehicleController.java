package com.speedlog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.speeding.model.VehicleModel;
import com.speedlog.entity.Vehicle;
import com.speedlog.repository.VehicleRepository;

@RestController
@RequestMapping("/vehicle")
public class VehicleController {
	
	@Autowired
	VehicleRepository vehicleRepo;

	@PostMapping
	Vehicle createVehicle(@RequestBody VehicleModel vehicle)
	{
		Vehicle vehicleObj = new Vehicle();
		vehicleObj.setBeingPersued(false);
		vehicleObj.setCarModel(vehicle.getCarModel());
		vehicleObj.setCarNumber(vehicle.getCarNumber());
		vehicleObj.setColor(vehicle.getColor());
		vehicleObj.setDescription(vehicle.getDescription());
		vehicleObj.setOwnerName(vehicle.getOwnerName());
		vehicleObj.setVehicleDrivingLicence(vehicle.getVehicleDrivingLicence());
		return vehicleRepo.save(vehicleObj);
	}
}
