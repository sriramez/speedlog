package com.speedlog.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

@Document("Patrol")
public class Patrol {

	@Id
	String id;
	String carnumber;
	String currentGPSLocation;
	double currentGPSLatitude;
	double currentGPSLongitude;
	double previousGPSLatitude;
	double previousGPSLongitude;
	String currentAddress;
	
	@DocumentReference(lazy = true)
	Vehicle vehicle;
	
	@DocumentReference(lazy=true)
	PoliceStation policeStation;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCarnumber() {
		return carnumber;
	}
	public void setCarnumber(String carnumber) {
		this.carnumber = carnumber;
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
	public Vehicle getVehicle() {
		return vehicle;
	}
	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}
	
	
}
