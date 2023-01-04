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

import com.speeding.model.PoliceStationModel;
import com.speeding.model.PoliceStationToRetModel;
import com.speedlog.entity.PoliceStation;
import com.speedlog.serviceImpl.PoliceStationService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("policeStation")
public class PoliceStationController {
	
	
	@Autowired
	PoliceStationService policeStationService;
	
	@PostMapping
	PoliceStation createPoliceStation(@RequestBody PoliceStationModel policeStation)
	{
		return policeStationService.createPoliceStation(policeStation);
	}
	
	@PutMapping
	PoliceStationToRetModel addPatrolCarToPoliceStation(@RequestParam String patrolCarnumber,@RequestParam String policeStationName) throws Exception
	{
		return policeStationService.addPatrolCarsToPoliceStation(patrolCarnumber, policeStationName);
	}
	
	@DeleteMapping
	PoliceStationToRetModel removeCarFromPoliceStation(@RequestParam String patrolCarnumber,@RequestParam String policeStationName) throws Exception
	{
		return policeStationService.removePatrolCarsToPoliceStation(patrolCarnumber, policeStationName);
	}
	
	@GetMapping
	PoliceStationToRetModel getPoliceStationDetails(@RequestParam String policeStationName) throws Exception
	{
		return policeStationService.getPoliceStationWithName(policeStationName);
	}
	
	List<PoliceStationToRetModel> getAllPoliceStation()
	{
		return policeStationService.getAllPoliceStation();
	}

}
