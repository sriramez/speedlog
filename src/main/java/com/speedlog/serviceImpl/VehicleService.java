package com.speedlog.serviceImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.speeding.model.LocationResponse;
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

	public VehicleModel createVehicle(VehicleModel vehicle) {
		Vehicle vehicleObj = new Vehicle();
		vehicleObj.setBeingPersued(false);
		vehicleObj.setCarModel(vehicle.getCarModel());
		vehicleObj.setCarNumber(vehicle.getCarNumber());
		vehicleObj.setColor(vehicle.getColor());
		vehicleObj.setDescription(vehicle.getDescription());
		vehicleObj.setOwnerName(vehicle.getOwnerName());
		vehicleObj.setVehicleDrivingLicence(vehicle.getVehicleDrivingLicence());
		Location location = new Location();
		Vehicle output = vehicleRepo.insert(vehicleObj);
		Location current = new Location();
		current.setType("Point");
		List<Double> coordinates = new ArrayList<>();
		coordinates.add(0.0);
		coordinates.add(0.0);
		current.setCoordinates(coordinates);
		current.setCurrentTime(0);
		location.setVehicle(output);
		location = locationRepository.insert(location);
		output.setCurrentLocation(current);

		return new VehicleModel(vehicleRepo.save(output));
	}

	public VehicleModel updateCarInfo(String vehicleNumber, VehicleModel vehicle) throws Exception {

		Vehicle car = vehicleRepo.findByCarNumber(vehicleNumber);
		if (car == null) {
			throw new Exception(vehicleNumber + " is not found");
		}
		car.setCarModel(vehicle.getCarModel());
		car.setCarNumber(vehicle.getCarNumber());
		car.setColor(vehicle.getColor());
		car.setDescription(vehicle.getDescription());
		car.setOwnerName(vehicle.getOwnerName());
		car.setVehicleDrivingLicence(vehicle.getVehicleDrivingLicence());
		return new VehicleModel(vehicleRepo.save(car));
	}

	public VehicleModel updateCurrentGpsPosition(String carNumber, double latitude, double longitude) throws Exception {
		Vehicle car = vehicleRepo.findByCarNumber(carNumber);
		if (car == null) {
			throw new Exception(carNumber + " is not found");
		}
		List<Double> coordinates = new ArrayList<>();
		Location current = new Location();
		coordinates.add(longitude);
		coordinates.add(latitude);
		current.setCoordinates(coordinates);
		current.setType("Point");
		current.setCurrentTime(System.currentTimeMillis());
		current.setCurrent(true);
		Location previous = new Location();
		if (car.getCurrentLocation() != null) {
			double previousLatitude = car.getCurrentLocation().getCoordinates().get(1);
			double previousLongitude = car.getCurrentLocation().getCoordinates().get(0);
			long previousTime = car.getCurrentLocation().getCurrentTime();
			locationRepository.delete(car.getCurrentLocation());
			long timeDiff = current.getCurrentTime() - previousTime;
			previous.setCoordinates(Arrays.asList(previousLongitude, previousLatitude));
			previous.setType("Point");
			previous.setCurrentTime(previousTime);
			previous.setCurrent(false);
			double distance = getDistanceBetweenTwoPointsInMiles(previousLatitude, previousLongitude, latitude,
					longitude);
			double timeDifference = Double.valueOf(timeDiff) / Double.valueOf(3600000);
			double speedInMiles = distance / timeDifference;
			if (speedInMiles > 80) {
				PoliceStation station = getNearByStation(car);
				if(!station.getVehicles().contains(car))
				{
					station.getVehicles().add(car);
					stationRepository.save(station);	
				}
				
			}
			current.setVehicle(car);
			previous.setVehicle(car);
			current= locationRepository.insert(current);
			previous = locationRepository.insert(previous);
			car.setCurrentLocation(current);
			car.setPreviousLocation(previous);
		} else {
			locationRepository.insert(current);
			car.setCurrentLocation(current);
		}
		return new VehicleModel(vehicleRepo.save(car));

	}

	public LocationResponse getVehicleLocation(String carNumber) throws Exception {
		Vehicle car = vehicleRepo.findByCarNumber(carNumber);
		if (car == null) {
			throw new Exception(carNumber + " is not found");
		}
		LocationResponse response = new LocationResponse(0, 0, carNumber);
		if (car.getCurrentLocation() != null) {
			double lng = car.getCurrentLocation().getCoordinates().get(0);
			double lat = car.getCurrentLocation().getCoordinates().get(1);
			response.setLat(lat);
			response.setLng(lng);
		}

		return response;

	}

	public PoliceStation getNearByStation(Vehicle car) {
		double distanceBetweenVehicleAndStation = 10000;

		List<PoliceStation> findAll = stationRepository.findAll();

		PoliceStation nearByStation = null;

		for (int i = 0; i < findAll.size(); i++) {
			double tempDistance = getDistanceBetweenTwoPointsInMiles(findAll.get(i).getLatitude(),
					findAll.get(i).getLongitude(), car.getCurrentLocation().getCoordinates().get(1),
					car.getCurrentLocation().getCoordinates().get(0));
			if (tempDistance < distanceBetweenVehicleAndStation) {
				nearByStation = findAll.get(i);
			}
		}
		return nearByStation;
	}

	public double getDistanceBetweenTwoPointsInMiles(double latitude1, double longitude1, double latitude2,
			double longitude2) {
		int radius = 3963;
		double dLat = deg2rad(latitude2 - latitude1);
		var dLon = deg2rad(longitude2 - longitude1);
		var a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
				+ Math.cos(deg2rad(latitude1)) * Math.cos(deg2rad(latitude2)) * Math.sin(dLon / 2) * Math.sin(dLon / 2);
		var c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
		var d = radius * c;
		return d;
	}

	private double deg2rad(double deg) {
		return deg * (Math.PI / 180);
	}

	public VehicleModel getCarInfo(String vehicleNumber) throws Exception {
		Vehicle car = vehicleRepo.findByCarNumber(vehicleNumber);
		if (car == null) {
			throw new Exception(vehicleNumber + " is not found");
		}
		return new VehicleModel(car);
	}

	public List<LocationResponse> getAllVehicleLocations() {

		return vehicleRepo.findAll().stream().map(vehicle -> {
			try {
				return getVehicleLocation(vehicle.getCarNumber());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}).collect(Collectors.toList());
	}

}
