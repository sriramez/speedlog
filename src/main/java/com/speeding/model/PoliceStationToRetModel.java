package com.speeding.model;

import java.util.List;

import com.speedlog.entity.PoliceStation;
import com.speedlog.entity.Vehicle;

public class PoliceStationToRetModel {

	
	String name;
	String location;
	String address;
	
	List<PatrolToRetModel> patrolCars;
	List<Vehicle> vehicles;
	
	public PoliceStationToRetModel(PoliceStation station)
	{
		this.name = station.getName();
		this.location=station.getLocation();
		this.address=station.getAddress();
		
		this.patrolCars = station.getCars().stream().map(patrol ->{
			return new PatrolToRetModel(patrol);
		}).toList();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public List<PatrolToRetModel> getPatrolCars() {
		return patrolCars;
	}

	public void setPatrolCars(List<PatrolToRetModel> patrolCars) {
		this.patrolCars = patrolCars;
	}
	
	
}
