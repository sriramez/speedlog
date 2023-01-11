package com.speeding.model;

import com.speedlog.entity.Patrol;

public class PatrolToRetModel {
	String carNumber;
	String stationName;

	LocationModel currentLocation;

	LocationModel previousLocation;

	String currentAddress;

	PoliceStationModel policeStation;

	VehicleModel vehicle;

	public PatrolToRetModel(Patrol patrol) {
		this.carNumber = patrol.getCarNumber();
		this.currentAddress = patrol.getCurrentAddress();
		if (patrol.getCurrentLocation() != null)
		{
			this.currentLocation = new LocationModel(patrol.getCurrentLocation().getType(),
					patrol.getCurrentLocation().getCoordinates());
		}
		if(patrol.getPreviousLocation()!=null)
		{
		this.previousLocation = new LocationModel(patrol.getPreviousLocation().getType(),
				patrol.getPreviousLocation().getCoordinates());
		}
		String stationNameFromPatrol = "";
		if (patrol.getPoliceStation() != null) {
			stationNameFromPatrol = patrol.getPoliceStation().getName();
		}
		if(patrol.getVehicle()!=null)
		{
			this.vehicle = new VehicleModel(patrol.getVehicle());
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

	public String getCarNumber() {
		return carNumber;
	}

	public void setCarnumber(String carNumber) {
		this.carNumber = carNumber;
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
