package com.speedlog.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.speeding.model.PoliceStationModel;
import com.speeding.model.PoliceStationToRetModel;
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
	
	public PoliceStation createPoliceStation(PoliceStationModel policeStation)
	{
		PoliceStation policeStatoinObj = new PoliceStation();
		policeStatoinObj.setAddress(policeStation.getAddress());
		policeStatoinObj.setLocation(policeStation.getLocation());
		policeStatoinObj.setName(policeStation.getName());
		return policeStationRepo.insert(policeStatoinObj);
	}
	
	public PoliceStationToRetModel addPatrolCarsToPoliceStation(String patrolCarnumber,String policeStationName) throws Exception
	{
		PoliceStation station = policeStationRepo.findByName(policeStationName);
		if(station==null)
		{
		throw new Exception("Police station doesn't exist");	
		}
		Patrol patrol = patrolRepository.findByCarNumber(patrolCarnumber);
		if(patrol==null)
		{
			throw new Exception("Patrol dosen't exist");
		}
		if(station.getCars()==null)
		{
			station.setCars(new ArrayList<Patrol>());
		}
		station.getCars().add(patrol);
		patrol.setPoliceStation(station);
		PoliceStation toReturn = policeStationRepo.save(station);
		patrolRepository.save(patrol);
		PoliceStationToRetModel model = new PoliceStationToRetModel(toReturn);
		return model;
	}
	
	public PoliceStationToRetModel removePatrolCarsToPoliceStation(String patrolCarnumber,String policeStationName) throws Exception
	{
		PoliceStation station = policeStationRepo.findByName(policeStationName);
		if(station==null)
		{
		throw new Exception("Police station doesn't exist");	
		}
		Patrol patrol = patrolRepository.findByCarNumber(patrolCarnumber);
		if(patrol==null)
		{
			throw new Exception("Patrol dosen't exist");
		}
		if(station.getCars()==null)
		{
			station.setCars(new ArrayList<Patrol>());
		}
		patrol.setPoliceStation(null);
		patrolRepository.save(patrol);
		List<Patrol> removedCars = new ArrayList<>();
		
		int size = station.getCars().size();
		
		for(int i=0;i<size;i++)
		{
			String carnumber = station.getCars().get(i).getCarnumber();
			if(!carnumber.equalsIgnoreCase(patrolCarnumber))
			{
				removedCars.add(station.getCars().get(i));
			}
		}
		station.setCars(removedCars);
		PoliceStation toReturn = policeStationRepo.save(station);
		
		return new PoliceStationToRetModel(toReturn);
	}
	
	
}
