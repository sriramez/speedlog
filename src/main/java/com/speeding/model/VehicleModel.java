package com.speeding.model;

import com.speedlog.entity.Vehicle;

public class VehicleModel {
	
	public VehicleModel(Vehicle vehicle)
	{
		this.carModel=vehicle.getCarModel();
		this.carNumber=vehicle.getCarNumber();
		this.Color=vehicle.getColor();
		this.description = vehicle.getDescription();
		this.ownerName=vehicle.getOwnerName();
		this.vehicleDrivingLicence=vehicle.getVehicleDrivingLicence();
	}
	
	
	
	
	public VehicleModel() {
		super();
	}




	String carNumber;
	String carModel;
	String Color;
	String description;
	String ownerName;
	String vehicleDrivingLicence;
	
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
	
	
}
