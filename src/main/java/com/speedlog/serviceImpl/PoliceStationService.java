package com.speedlog.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.speeding.model.PatrolModel;
import com.speeding.model.PatrolToRetModel;
import com.speeding.model.PoliceStationModel;
import com.speeding.model.PoliceStationToRetModel;
import com.speeding.model.VehicleModel;
import com.speedlog.entity.Patrol;
import com.speedlog.entity.PoliceStation;
import com.speedlog.repository.PatrolRepository;
import com.speedlog.repository.PoliceStationRepository;

@Service
public class PoliceStationService {

	@Autowired
	PoliceStationRepository policeStationRepo;

	@Autowired
	PatrolRepository patrolRepository;

	public PoliceStation createPoliceStation(PoliceStationModel policeStation) {
		PoliceStation policeStatoinObj = new PoliceStation();
		policeStatoinObj.setAddress(policeStation.getAddress());
		policeStatoinObj.setLocation(policeStation.getLocation());
		policeStatoinObj.setName(policeStation.getName());
		return policeStationRepo.insert(policeStatoinObj);
	}

	public PoliceStationToRetModel addPatrolCarsToPoliceStation(String patrolCarnumber, String policeStationName)
			throws Exception {
		PoliceStation station = policeStationRepo.findByName(policeStationName);
		if (station == null) {
			throw new Exception("Police station doesn't exist");
		}
		Patrol patrol = patrolRepository.findByCarNumber(patrolCarnumber);
		if (patrol == null) {
			throw new Exception("Patrol dosen't exist");
		}
		if (station.getCars() == null) {
			station.setCars(new ArrayList<Patrol>());
		}
		station.getCars().add(patrol);
		patrol.setPoliceStation(station);
		PoliceStation toReturn = policeStationRepo.save(station);
		patrolRepository.save(patrol);
		PoliceStationToRetModel model = new PoliceStationToRetModel(toReturn);
		return model;
	}

	public PoliceStationToRetModel removePatrolCarsToPoliceStation(String patrolCarnumber, String policeStationName)
			throws Exception {
		PoliceStation station = policeStationRepo.findByName(policeStationName);
		if (station == null) {
			throw new Exception("Police station doesn't exist");
		}
		Patrol patrol = patrolRepository.findByCarNumber(patrolCarnumber);
		if (patrol == null) {
			throw new Exception("Patrol dosen't exist");
		}
		if (station.getCars() == null) {
			station.setCars(new ArrayList<Patrol>());
		}
		patrol.setPoliceStation(null);
		patrolRepository.save(patrol);
		List<Patrol> removedCars = new ArrayList<>();

		int size = station.getCars().size();

		for (int i = 0; i < size; i++) {
			String carnumber = station.getCars().get(i).getCarNumber();
			if (!carnumber.equalsIgnoreCase(patrolCarnumber)) {
				removedCars.add(station.getCars().get(i));
			}
		}
		station.setCars(removedCars);
		PoliceStation toReturn = policeStationRepo.save(station);

		return new PoliceStationToRetModel(toReturn);
	}

	public PoliceStationToRetModel getPoliceStationWithName(String policeStationName) throws Exception {
		PoliceStation station = policeStationRepo.findByName(policeStationName);
		if (station == null) {
			throw new Exception("Police station doesn't exist");
		}
		return new PoliceStationToRetModel(station);
	}

	public List<PoliceStationToRetModel> getAllPoliceStation() {
		return policeStationRepo.findAll().stream().map(policeStation -> new PoliceStationToRetModel(policeStation))
				.toList();
	}

	public List<VehicleModel> getVehiclesFromStation(String policeStationName) throws Exception {
		PoliceStation station = policeStationRepo.findByName(policeStationName);
		if (station == null) {
			throw new Exception("Police station doesn't exist");
		}
		if(station.getVehicles()==null)
		{
			return new ArrayList<>();	
		}
		return station.getVehicles().stream().map(vehicle->new VehicleModel(vehicle)).collect(Collectors.toList());
	}

	public List<PatrolToRetModel> getPatrolsFromStation(String policeStationName) throws Exception {
		PoliceStation station = policeStationRepo.findByName(policeStationName);
		if (station == null) {
			throw new Exception("Police station doesn't exist");
		}
		if(station.getCars()==null)
		{
			return new ArrayList<>();	
		}
		return station.getCars().stream().map(vehicle->new PatrolToRetModel(vehicle)).collect(Collectors.toList());
	}

	

}
