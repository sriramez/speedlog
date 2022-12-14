package com.speeding.model;

import com.speedlog.entity.Patrol;

public class PatrolToRetModel {
	String carnumber;
	String stationName;
	String currentGPSLocation;
	double currentGPSLatitude;
	double currentGPSLongitude;
	double previousGPSLatitude;
	double previousGPSLongitude;
	String currentAddress;
	
	PoliceStationModel policeStation;
	
	VehicleModel vehicle;
	
	
	public PatrolToRetModel(Patrol patrol)
	{
		this.carnumber = patrol.getCarnumber();
		this.currentAddress=patrol.getCurrentAddress();
		this.currentGPSLatitude=patrol.getCurrentGPSLatitude();
		this.currentGPSLocation=patrol.getCurrentGPSLocation();
		this.currentGPSLongitude = patrol.getCurrentGPSLongitude();
		this.previousGPSLatitude = patrol.getPreviousGPSLatitude();
		this.previousGPSLongitude = patrol.getPreviousGPSLongitude();
		this.stationName = patrol.getPoliceStation().getName();
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

	public String getCurrentGPSLocation() {
		return currentGPSLocation;
	}

	public void setCurrentGPSLocation(String currentGPSLocation) {
		this.currentGPSLocation = currentGPSLocation;
	}

	public double getCurrentGPSLatitude() {
		return currentGPSLatitude;
	}

	public void setCurrentGPSLatitude(double currentGPSLatitude) {
		this.currentGPSLatitude = currentGPSLatitude;
	}

	public double getCurrentGPSLongitude() {
		return currentGPSLongitude;
	}

	public void setCurrentGPSLongitude(double currentGPSLongitude) {
		this.currentGPSLongitude = currentGPSLongitude;
	}

	public double getPreviousGPSLatitude() {
		return previousGPSLatitude;
	}

	public void setPreviousGPSLatitude(double previousGPSLatitude) {
		this.previousGPSLatitude = previousGPSLatitude;
	}

	public double getPreviousGPSLongitude() {
		return previousGPSLongitude;
	}

	public void setPreviousGPSLongitude(double previousGPSLongitude) {
		this.previousGPSLongitude = previousGPSLongitude;
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
