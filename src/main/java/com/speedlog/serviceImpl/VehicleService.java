package com.speedlog.serviceImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.speeding.model.VehicleModel;
import com.speedlog.entity.Location;
import com.speedlog.entity.PoliceStation;
import com.speedlog.entity.Vehicle;
import com.speedlog.repository.LocationRepository;
import com.speedlog.repository.PoliceStationRepository;
import com.speedlog.repository.VehicleRepository;

@Service
public class VehicleService {
	
	@Autowired
	VehicleRepository vehicleRepo;
	
	@Autowired
	LocationRepository locationRepository;
	
	@Autowired
	PoliceStationRepository stationRepository;


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
			current.setCurrentTime(0);
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
		long previousTime = car.getCurrentLocation().getCurrentTime();
		locationRepository.delete(car.getCurrentLocation());
		List<Double> coordinates = new ArrayList<>();
		Location current = new Location();
		coordinates.add(longitude);
		coordinates.add(latitude);
		current.setCoordinates(coordinates);
		current.setType("Point");
		current.setCurrentTime(System.currentTimeMillis());
		Location previous = new Location();
		previous.setCoordinates(Arrays.asList(previousLongitude,previousLatitude));
		previous.setType("Point");
		previous.setCurrentTime(previousTime);
		double distance = getDistanceBetweenTwoPointsInMiles(previousLatitude, previousLongitude, latitude, longitude);
		long timeDifference = (current.getCurrentTime() - previous.getCurrentTime())/3600000;
		double speedInMiles = distance/timeDifference;
		if(speedInMiles>80)
		{
			PoliceStation station = getNearByStation(car);
			
			station.getVehicles().add(car);
			stationRepository.save(station);
		}
		locationRepository.insert(current);
		locationRepository.insert(previous);
		car.setCurrentLocation(current);
		car.setPreviousLocation(previous);
		vehicleRepo.save(car);

		return new VehicleModel(vehicleRepo.save(car));
		
	}
	
	
	public PoliceStation getNearByStation(Vehicle car)
	{
		double distanceBetweenVehicleAndStation = 10000;
		
		List<PoliceStation> findAll = stationRepository.findAll();
		
		PoliceStation nearByStation = null;
		
		for(int i=0;i<findAll.size();i++)
		{
			double tempDistance = getDistanceBetweenTwoPointsInMiles(findAll.get(i).getLatitude(), findAll.get(i).getLongitude(), car.getCurrentLocation().getCoordinates().get(1), car.getCurrentLocation().getCoordinates().get(0));
			if(tempDistance<distanceBetweenVehicleAndStation)
			{
				nearByStation = findAll.get(i);
			}
		}
		return nearByStation;
	}
	
	
	private double getDistanceBetweenTwoPointsInMiles(double latitude1,double longitude1,double latitude2,double longitude2)
	{
		int radius = 3963;
		double dLat = deg2rad(latitude2-latitude1); 
		  var dLon = deg2rad(longitude2-longitude1); 
		  var a = 
		    Math.sin(dLat/2) * Math.sin(dLat/2) +
		    Math.cos(deg2rad(latitude1)) * Math.cos(deg2rad(latitude2)) * 
		    Math.sin(dLon/2) * Math.sin(dLon/2)
		    ; 
		  var c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a)); 
		  var d = radius * c;
		  return d;
	}
	
	private double deg2rad(double deg)
	{
		return deg*(Math.PI/180);
	}

	public VehicleModel getCarInfo(String vehicleNumber) throws Exception {
		Vehicle car = vehicleRepo.findByCarNumber(vehicleNumber);
		if(car==null)
		{
			throw new Exception(vehicleNumber+" is not found");
		}
		return new VehicleModel(car);
	}
	
	
	
}
