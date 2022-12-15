package com.speedlog.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

@Document("Patrol")
public class Patrol {

	@Id
	String id;
	String carnumber;
	public PoliceStation getPoliceStation() {
		return policeStation;
	}
	public void setPoliceStation(PoliceStation policeStation) {
		this.policeStation = policeStation;
	}
	
	String currentAddress;
	
	@DocumentReference
	Location currentLocation;
	
	@DocumentReference
	Location previousLocation;
	
	
	public Location getCurrentLocation() {
		return currentLocation;
	}
	public void setCurrentLocation(Location currentLocation) {
		this.currentLocation = currentLocation;
	}
	public Location getPreviousLocation() {
		return previousLocation;
	}
	public void setPreviousLocation(Location previousLocation) {
		this.previousLocation = previousLocation;
	}
	@DocumentReference
	Vehicle vehicle;
	
	@DocumentReference
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
