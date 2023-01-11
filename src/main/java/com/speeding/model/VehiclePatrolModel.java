package com.speeding.model;

import com.speedlog.entity.Patrol;
import com.speedlog.entity.Vehicle;

public class VehiclePatrolModel {

	String carNumber;
	String carModel;
	String color;
	String description;
	String ownerName;
	String vehicleDrivingLicence;
	double vehiclelatitude;
	double vehicleLongitude;
	String patrolNumber;
	double patrolLatitude;
	double patrolLongitude;

	public VehiclePatrolModel(Patrol patrol) {
		Vehicle vehicle = patrol.getVehicle();
		if (vehicle != null) {
			this.carNumber = vehicle.getCarNumber();
			this.carModel = vehicle.getCarModel();
			this.color = vehicle.getColor();
			this.description = vehicle.getDescription();
			this.ownerName = vehicle.getOwnerName();
			this.vehicleDrivingLicence = vehicle.getVehicleDrivingLicence();
			this.vehiclelatitude = vehicle.getCurrentLocation().getCoordinates().get(1);
			this.vehicleLongitude = vehicle.getCurrentLocation().getCoordinates().get(0);

		}
		this.patrolNumber = patrol.getCarNumber();
		this.patrolLatitude = patrol.getCurrentLocation().getCoordinates().get(1);
		this.patrolLongitude = patrol.getCurrentLocation().getCoordinates().get(0);
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
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public double getVehiclelatitude() {
		return vehiclelatitude;
	}

	public void setVehiclelatitude(double vehiclelatitude) {
		this.vehiclelatitude = vehiclelatitude;
	}

	public double getVehicleLongitude() {
		return vehicleLongitude;
	}

	public void setVehicleLongitude(double vehicleLongitude) {
		this.vehicleLongitude = vehicleLongitude;
	}

	public String getPatrolNumber() {
		return patrolNumber;
	}

	public void setPatrolNumber(String patrolNumber) {
		this.patrolNumber = patrolNumber;
	}

	public double getPatrolLatitude() {
		return patrolLatitude;
	}

	public void setPatrolLatitude(double patrolLatitude) {
		this.patrolLatitude = patrolLatitude;
	}

	public double getPatrolLongitude() {
		return patrolLongitude;
	}

	public void setPatrolLongitude(double patrolLongitude) {
		this.patrolLongitude = patrolLongitude;
	}

}
