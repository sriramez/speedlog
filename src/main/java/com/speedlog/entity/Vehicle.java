package com.speedlog.entity;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

@Document
public class Vehicle {
	
	@Id
	String id;
	String carNumber;
	String carModel;
	String Color;
	String description;
	List<String> offencesHistory;
	
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
	
	String ownerName;
	String vehicleDrivingLicence;
	String currentAddress;
	
	@DocumentReference(lazy = true)
	List<Patrol> cars;
	
	boolean isBeingPersued;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCarNumber() {
		return carNumber;
	}
	public void setCarNumber(String carNumber) {
		this.carNumber = carNumber;
	}
	public String getCarModel() {
		return carModel;
	}
	public void setCarModel(String carModel) {
		this.carModel = carModel;
	}
	public String getColor() {
		return Color;
	}
	public void setColor(String color) {
		Color = color;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public List<String> getOffencesHistory() {
		return offencesHistory;
	}
	public void setOffencesHistory(List<String> offencesHistory) {
		this.offencesHistory = offencesHistory;
	}
	
	public String getOwnerName() {
		return ownerName;
	}
	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}
	public String getVehicleDrivingLicence() {
		return vehicleDrivingLicence;
	}
	public void setVehicleDrivingLicence(String vehicleDrivingLicence) {
		this.vehicleDrivingLicence = vehicleDrivingLicence;
	}
	public String getCurrentAddress() {
		return currentAddress;
	}
	public void setCurrentAddress(String currentAddress) {
		this.currentAddress = currentAddress;
	}
	public List<Patrol> getCars() {
		return cars;
	}
	public void setCars(List<Patrol> cars) {
		this.cars = cars;
	}
	public boolean isBeingPersued() {
		return isBeingPersued;
	}
	public void setBeingPersued(boolean isBeingPersued) {
		this.isBeingPersued = isBeingPersued;
	}
	
	

}
