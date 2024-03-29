package com.speedlog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.speeding.model.LocationResponse;
import com.speeding.model.VehicleModel;
import com.speedlog.serviceImpl.VehicleService;

@CrossOrigin(origins = "http://localhost:4200")
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
	
	@GetMapping
	List<VehicleModel> getVehicleUsingVehicleNumber(@RequestParam String vehicleNumber) throws Exception
	
	{
		VehicleModel model = vehicleService.getCarInfo(vehicleNumber);
		return List.of(model);
	}
	
	@PutMapping("location")
	VehicleModel updateCurrentGpsPosition(@RequestParam String carNumber,@RequestParam double latitude,@RequestParam double longitude) throws Exception
	{
		return vehicleService.updateCurrentGpsPosition(carNumber, latitude, longitude);
		
	}
	
	@GetMapping("location")
	List<LocationResponse> getVehiclePosition(@RequestParam String carNumber) throws Exception
	{
		return List.of(vehicleService.getVehicleLocation(carNumber));
	}
	
	@GetMapping("locations")
	List<LocationResponse> getAllVehiclePositions()
	{
		return vehicleService.getAllVehicleLocations();
	}
	
	
	
	
	
	
	
	
}
