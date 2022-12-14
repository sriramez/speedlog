package com.speedlog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.speeding.model.PatrolModel;
import com.speeding.model.PatrolToRetModel;
import com.speedlog.entity.Patrol;
import com.speedlog.serviceImpl.PatrolService;

@RestController
@RequestMapping("patrol")
public class PatrolController {

	@Autowired
	PatrolService patrolService;
	
	@PostMapping
	public Patrol createPatrol(@RequestBody PatrolModel patrol)
	{
		return patrolService.createPatrol(patrol);
	}
	
	@PutMapping
	public Patrol updateCurrentGpsPosition(@RequestParam String carnumber,@RequestParam double latitude,@RequestParam double longitude) throws Exception
	{
		return patrolService.updateCurrentGpsPosition(carnumber, latitude, longitude);
	}
	
	@PutMapping("vehicle")
	public PatrolToRetModel AddVehiclesToPersue(@RequestParam String patrolNumber,@RequestParam String vehicleNumber) throws Exception
	{
		return patrolService.addVehicleToPersue(patrolNumber, vehicleNumber);
	}
	
	@DeleteMapping("vehicle")
	public Patrol removeVehicleToPersue(@RequestParam String patrolNumber,@RequestParam String vehicleNumber) throws Exception
	{
		return patrolService.removeVehicleFromPersue(patrolNumber, vehicleNumber);
	}
	
	
}
