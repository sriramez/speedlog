package com.speedlog.serviceImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.speeding.model.PatrolModel;
import com.speeding.model.PatrolToRetModel;
import com.speeding.model.VehicleModel;
import com.speedlog.entity.Location;
import com.speedlog.entity.Patrol;
import com.speedlog.entity.Vehicle;
import com.speedlog.repository.LocationRepository;
import com.speedlog.repository.PatrolRepository;
import com.speedlog.repository.VehicleRepository;

@Service
public class PatrolService {

	@Autowired
	PatrolRepository patrolRepo;

	@Autowired
	VehicleRepository vehicleRepository;

	@Autowired
	LocationRepository locationRepository;

	public Patrol createPatrol(PatrolModel patrol) {
		Patrol patrolObj = new Patrol();
		patrolObj.setCarnumber(patrol.getCarnumber());
		if (patrolObj.getCurrentLocation() == null) {
			Location current = new Location();
			current.setType("Point");
			List<Double> coordinates = new ArrayList<>();
			coordinates.add(0.0);
			coordinates.add(0.0);
			current.setCoordinates(coordinates);
			locationRepository.insert(current);
			patrolObj.setCurrentLocation(current);
			}
		return patrolRepo.insert(patrolObj);
	}

	public PatrolToRetModel updateCurrentGpsPosition(String carNumber, double latitude, double longitude) throws Exception {
		Patrol patrol = patrolRepo.findByCarNumber(carNumber);
		if (patrol == null) {
			throw new Exception(carNumber + " is not found");
		}
		if (patrol.getPreviousLocation() != null) {
			locationRepository.delete(patrol.getPreviousLocation());
		}
		double previousLatitude = patrol.getCurrentLocation().getCoordinates().get(1);
		double previousLongitude = patrol.getCurrentLocation().getCoordinates().get(0);
		locationRepository.delete(patrol.getCurrentLocation());
		List<Double> coordinates = new ArrayList<>();
		Location current = new Location();
		coordinates.add(longitude);
		coordinates.add(latitude);
		current.setCoordinates(coordinates);
		Location previous = new Location();
		previous.setCoordinates(Arrays.asList(previousLongitude,previousLatitude));
		locationRepository.insert(current);
		locationRepository.insert(previous);
		patrol.setCurrentLocation(current);
		patrol.setPreviousLocation(previous);
		patrolRepo.save(patrol);
		return new PatrolToRetModel(patrolRepo.save(patrol));

	}

	public PatrolToRetModel addVehicleToPersue(String patrolnumber, String vehicleNumber) throws Exception {
		Patrol patrol = patrolRepo.findByCarNumber(patrolnumber);
		if (patrol == null) {
			throw new Exception(patrolnumber + " is not found");
		}
		Vehicle vehicle = vehicleRepository.findByCarNumber(vehicleNumber);
		if (vehicle == null) {
			throw new Exception(vehicleNumber + " is not found");
		}
		if(patrol.getVehicle()!=null)
		{
			Vehicle previousVehicle = patrol.getVehicle();
			previousVehicle.setCars(previousVehicle.getCars().stream()
					.filter(patrolObj -> !patrolObj.getCarnumber().equalsIgnoreCase(patrolnumber))
					.collect(Collectors.toList()));
			vehicleRepository.save(previousVehicle);
	
		}
		
		patrol.setVehicle(vehicle);
		if (vehicle.getCars() == null) {
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

	public PatrolToRetModel removeVehicleFromPersue(String patrolnumber, String vehicleNumber) throws Exception {
		Patrol patrol = patrolRepo.findByCarNumber(patrolnumber);
		if (patrol == null) {
			throw new Exception(patrolnumber + " is not found");
		}
		Vehicle vehicle = vehicleRepository.findByCarNumber(vehicleNumber);
		if (vehicle == null) {
			throw new Exception(vehicleNumber + " is not found");
		}
		patrol.setVehicle(null);
		Patrol toRet = patrolRepo.save(patrol);
		vehicle.setCars(
				vehicle.getCars().stream().filter(car -> !car.equals(vehicleNumber)).collect(Collectors.toList()));
		vehicle.setBeingPersued(false);
		vehicleRepository.save(vehicle);
		return new PatrolToRetModel(toRet);

	}
	
	
	public PatrolToRetModel getPatrolVehicleFromVehicleId(String patrolnumber) throws Exception
	{
		Patrol patrol = patrolRepo.findByCarNumber(patrolnumber);
		if (patrol == null) {
			throw new Exception(patrolnumber + " is not found");
		}
		return new PatrolToRetModel(patrol);
	}
	
	public List<PatrolToRetModel> getAllPatrolVehicle()
	{
		return patrolRepo.findAll().stream().map(patrol-> new PatrolToRetModel(patrol)).toList();
	}
}
