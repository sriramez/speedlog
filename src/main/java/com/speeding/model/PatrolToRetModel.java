package com.speeding.model;

import com.speedlog.entity.Patrol;

public class PatrolToRetModel {
	String carnumber;
	String stationName;
	
	LocationModel currentLocation;
	
	LocationModel previousLocation;

	String currentAddress;
	
	PoliceStationModel policeStation;
	
	VehicleModel vehicle;
	
	
	public PatrolToRetModel(Patrol patrol)
	{
		this.carnumber = patrol.getCarnumber();
		this.currentAddress=patrol.getCurrentAddress();
		this.currentLocation = new LocationModel(patrol.getCurrentLocation().getType(),patrol.getCurrentLocation().getCoordinates());
		this.previousLocation = new LocationModel(patrol.getPreviousLocation().getType(),patrol.getPreviousLocation().getCoordinates());
		
		String stationNameFromPatrol = "";
		if(patrol.getPoliceStation()!=null)
		{
			stationNameFromPatrol = patrol.getPoliceStation().getName();
		}
		this.stationName = stationNameFromPatrol;
				
				
	}
	
	
	public LocationModel getCurrentLocation() {
		return currentLocation;
	}

	public void setCurrentLocation(LocationModel currentLocation) {
		this.currentLocation = currentLocation;
	}




	public LocationModel getPreviousLocation() {
		return previousLocation;
	}




	public void setPreviousLocation(LocationModel previousLocation) {
		this.previousLocation = previousLocation;
	}




	public String getCarnumber() {
		return carnumber;
	}

	public void setCarnumber(String carnumber) {
		this.carnumber = carnumber;
	}

	public String getStationName() {
		return stationName;
	}

	public void setStationName(String stationName) {
		this.stationName = stationName;
	}

	public String getCurrentAddress() {
		return currentAddress;
	}

	public void setCurrentAddress(String currentAddress) {
		this.currentAddress = currentAddress;
	}

	public PoliceStationModel getPoliceStation() {
		return policeStation;
	}

	public void setPoliceStation(PoliceStationModel policeStation) {
		this.policeStation = policeStation;
	}

	public VehicleModel getVehicle() {
		return vehicle;
	}

	public void setVehicle(VehicleModel vehicle) {
		this.vehicle = vehicle;
	}
	
	
	
	
	
}
