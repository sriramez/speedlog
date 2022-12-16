package com.speedlog.serviceImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.speeding.model.VehicleModel;
import com.speedlog.entity.Location;
import com.speedlog.entity.Vehicle;
import com.speedlog.repository.LocationRepository;
import com.speedlog.repository.VehicleRepository;

@Service
public class VehicleService {
	
	@Autowired
	VehicleRepository vehicleRepo;
	
	@Autowired
	LocationRepository locationRepository;


	public VehicleModel createVehicle(VehicleModel vehicle)
	{
		Vehicle vehicleObj = new Vehicle();
		vehicleObj.setBeingPersued(false);
		vehicleObj.setCarModel(vehicle.getCarModel());
		vehicleObj.setCarNumber(vehicle.getCarNumber());
		vehicleObj.setColor(vehicle.getColor());
		vehicleObj.setDescription(vehicle.getDescription());
		vehicleObj.setOwnerName(vehicle.getOwnerName());
		vehicleObj.setVehicleDrivingLicence(vehicle.getVehicleDrivingLicence());
		
		if (vehicleObj.getCurrentLocation() == null) {
			Location current = new Location();
			current.setType("Point");
			List<Double> coordinates = new ArrayList<>();
			coordinates.add(0.0);
			coordinates.add(0.0);
			current.setCoordinates(coordinates);
			locationRepository.insert(current);
			vehicleObj.setCurrentLocation(current);
			}
		
		return new VehicleModel(vehicleRepo.insert(vehicleObj));
	}
	
	public VehicleModel updateCarInfo(String vehicleNumber,VehicleModel vehicle) throws Exception
	{
		
		Vehicle car = vehicleRepo.findByCarNumber(vehicleNumber);
		if(car==null)
		{
			throw new Exception(vehicleNumber+" is not found");
		}
		car.setCarModel(vehicle.getCarModel());
		car.setCarNumber(vehicle.getCarNumber());
		car.setColor(vehicle.getColor());
		car.setDescription(vehicle.getDescription());
		car.setOwnerName(vehicle.getOwnerName());
		car.setVehicleDrivingLicence(vehicle.getVehicleDrivingLicence());
		return new VehicleModel(vehicleRepo.save(car));
	}
	
	public VehicleModel updateCurrentGpsPosition(String carNumber,double latitude,double longitude) throws Exception
	{
		Vehicle car = vehicleRepo.findByCarNumber(carNumber);
		if(car==null)
		{
			throw new Exception(carNumber+" is not found");
		}
		double previousLatitude = car.getCurrentLocation().getCoordinates().get(0);
		double previousLongitude = car.getCurrentLocation().getCoordinates().get(1);
		locationRepository.delete(car.getCurrentLocation());
		List<Double> coordinates = new ArrayList<>();
		Location current = new Location();
		coordinates.add(longitude);
		coordinates.add(latitude);
		current.setCoordinates(coordinates);
		Location previous = new Location();
		previous.setCoordinates(Arrays.asList(previousLongitude,previousLatitude));
		locationRepository.insert(current);
		locationRepository.insert(previous);
		car.setCurrentLocation(current);
		car.setPreviousLocation(previous);
		vehicleRepo.save(car);

		return new VehicleModel(vehicleRepo.save(car));
		
	}
}
