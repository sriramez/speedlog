package com.speedlog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.speeding.model.VehicleModel;
import com.speedlog.serviceImpl.VehicleService;

@RestController
@RequestMapping("/vehicle")
public class VehicleController {
	
	@Autowired
	VehicleService vehicleService;

	@PostMapping
	VehicleModel createVehicle(@RequestBody VehicleModel vehicle)
	{
		return vehicleService.createVehicle(vehicle);
	}
	
	@PutMapping
	VehicleModel updateCarInfo(@RequestParam String vehicleNumber,@RequestBody VehicleModel vehicle) throws Exception
	{
		
		return vehicleService.updateCarInfo(vehicleNumber, vehicle);
	}
	
	@PutMapping("location")
	VehicleModel updateCurrentGpsPosition(@RequestParam String carNumber,@RequestParam double latitude,@RequestParam double longitude) throws Exception
	{
		return vehicleService.updateCurrentGpsPosition(carNumber, latitude, longitude);
		
	}
}
