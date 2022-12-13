package com.speedlog.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.speeding.model.VehicleModel;
import com.speedlog.entity.Vehicle;
import com.speedlog.repository.VehicleRepository;

@Service
public class VehicleService {
	
	@Autowired
	VehicleRepository vehicleRepo;


	public Vehicle createVehicle(VehicleModel vehicle)
	{
		Vehicle vehicleObj = new Vehicle();
		vehicleObj.setBeingPersued(false);
		vehicleObj.setCarModel(vehicle.getCarModel());
		vehicleObj.setCarNumber(vehicle.getCarNumber());
		vehicleObj.setColor(vehicle.getColor());
		vehicleObj.setDescription(vehicle.getDescription());
		vehicleObj.setOwnerName(vehicle.getOwnerName());
		vehicleObj.setVehicleDrivingLicence(vehicle.getVehicleDrivingLicence());
		return vehicleRepo.insert(vehicleObj);
	}
	
	public Vehicle updateCarInfo(String vehicleNumber,VehicleModel vehicle) throws Exception
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
		return vehicleRepo.save(car);
	}
	
	public Vehicle updateCurrentGpsPosition(String carNumber,double latitude,double longitude) throws Exception
	{
		Vehicle car = vehicleRepo.findByCarNumber(carNumber);
		if(car==null)
		{
			throw new Exception(carNumber+" is not found");
		}
		double previousLatitude = car.getCurrentGPSLatitude();
		double previousLongitude = car.getCurrentGPSLongitude();
		car.setCurrentGPSLatitude(latitude);
		car.setCurrentGPSLongitude(longitude);
		car.setPreviousGPSLatitude(previousLatitude);
		car.setPreviousGPSLongitude(previousLongitude);
		return vehicleRepo.save(car);
		
	}
}
