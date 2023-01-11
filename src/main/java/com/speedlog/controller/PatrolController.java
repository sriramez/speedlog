package com.speedlog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
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

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("patrol")
public class PatrolController {

	@Autowired
	PatrolService patrolService;
	
	@PostMapping
	public PatrolToRetModel createPatrol(@RequestBody PatrolModel patrol) throws Exception
	{
		return patrolService.createPatrol(patrol);
	}
	
	@PutMapping
	public PatrolToRetModel updateCurrentGpsPosition(@RequestParam String carnumber,@RequestParam double latitude,@RequestParam double longitude) throws Exception
	{
		return patrolService.updateCurrentGpsPosition(carnumber, latitude, longitude);
	}
	
	@PutMapping("vehicle")
	public List<PatrolToRetModel> AddVehiclesToPersue(@RequestParam String patrolNumber,@RequestParam String vehicleNumber) throws Exception
	{
		return List.of(patrolService.addVehicleToPersue(patrolNumber, vehicleNumber));
	}
	
	@DeleteMapping("vehicle")
	public PatrolToRetModel removeVehicleToPersue(@RequestParam String patrolNumber,@RequestParam String vehicleNumber) throws Exception
	{
		return patrolService.removeVehicleFromPersue(patrolNumber, vehicleNumber);
	}
	
	@GetMapping("vehicle")
	public PatrolToRetModel getVehicleUsingId(@RequestParam String patrolNumber) throws Exception
	{
		return patrolService.getPatrolVehicleFromVehicleId(patrolNumber);
	}
	
	@GetMapping
	public List<PatrolToRetModel> getAllPatrolVehicles()
	{
		return patrolService.getAllPatrolVehicle();
	}
	
	
}
