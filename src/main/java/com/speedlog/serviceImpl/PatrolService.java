package com.speedlog.serviceImpl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.speeding.model.PatrolModel;
import com.speeding.model.PatrolToRetModel;
import com.speeding.model.VehicleModel;
import com.speedlog.entity.Patrol;
import com.speedlog.entity.Vehicle;
import com.speedlog.repository.PatrolRepository;
import com.speedlog.repository.VehicleRepository;

@Service
public class PatrolService {

	@Autowired
	PatrolRepository patrolRepo;
	
	@Autowired
	VehicleRepository vehicleRepository;
	
	
	
	public Patrol createPatrol(PatrolModel patrol)
	{
		Patrol patrolObj = new Patrol();
		patrolObj.setCarnumber(patrol.getCarnumber());
		return patrolRepo.insert(patrolObj);
	}
	
	public Patrol updateCurrentGpsPosition(String carNumber,double latitude,double longitude) throws Exception
	{
		Patrol patrol = patrolRepo.findByCarNumber(carNumber);
		if(patrol==null)
		{
			throw new Exception(carNumber+" is not found");
		}
		double previousLatitude = patrol.getCurrentGPSLatitude();
		double previousLongitude = patrol.getCurrentGPSLongitude();
		patrol.setCurrentGPSLatitude(latitude);
		patrol.setCurrentGPSLongitude(longitude);
		patrol.setPreviousGPSLatitude(previousLatitude);
		patrol.setPreviousGPSLongitude(previousLongitude);
		return patrolRepo.save(patrol);
		
	}
	
	public PatrolToRetModel addVehicleToPersue(String patrolnumber,String vehicleNumber) throws Exception
	{
		Patrol patrol = patrolRepo.findByCarNumber(patrolnumber);
		if(patrol==null)
		{
			throw new Exception(patrolnumber+" is not found");
		}
		Vehicle vehicle = vehicleRepository.findByCarNumber(vehicleNumber);
		if(vehicle==null)
		{
			throw new Exception(vehicleNumber+" is not found");
		}
		patrol.setVehicle(vehicle);
		if(vehicle.getCars()==null)
		{
			vehicle.setCars(new ArrayList<Patrol>());
		}
		Patrol toRet = patrolRepo.save(patrol);
		vehicle.getCars().add(patrol);
		vehicle.setBeingPersued(true);
		vehicleRepository.save(vehicle);
		
		PatrolToRetModel toRetPatrol = new PatrolToRetModel(toRet);
		VehicleModel model = new VehicleModel(vehicle);
		toRetPatrol.setVehicle(model);
		return toRetPatrol;
		
	}
	
	public Patrol removeVehicleFromPersue(String patrolnumber,String vehicleNumber) throws Exception
	{
		Patrol patrol = patrolRepo.findByCarNumber(patrolnumber);
		if(patrol==null)
		{
			throw new Exception(patrolnumber+" is not found");
		}
		Vehicle vehicle = vehicleRepository.findByCarNumber(vehicleNumber);
		if(vehicle==null)
		{
			throw new Exception(vehicleNumber+" is not found");
		}
		patrol.setVehicle(null);
		Patrol toRet = patrolRepo.save(patrol);
		vehicle.getCars().remove(patrol);
		vehicle.setBeingPersued(false);
		vehicleRepository.save(vehicle);
		return toRet;
		
	}
}
