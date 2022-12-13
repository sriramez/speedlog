package com.speedlog.serviceImpl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.speeding.model.PoliceStationModel;
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
	
	public PoliceStation addPatrolCarsToPoliceStation(String patrolCarnumber,String policeStationName) throws Exception
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
		return toReturn;
	}
	
	public PoliceStation removePatrolCarsToPoliceStation(String patrolCarnumber,String policeStationName) throws Exception
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
		station.getCars().remove(patrol);
		patrol.setPoliceStation(null);
		PoliceStation toReturn = policeStationRepo.save(station);
		patrolRepository.save(patrol);
		return toReturn;
	}
	
	
}
