package com.speedlog.entity;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

@Document("PoliceStation")
public class PoliceStation {

		@Id
		String id;
		String name;
		String location;
		String address;
		double latitude;
		double longitude;
		
		@DocumentReference
		List<Patrol> cars;
		@DocumentReference
		List<Vehicle> vehicles;
		
		public double getLatitude() {
			return latitude;
		}
		public void setLatitude(double latitude) {
			this.latitude = latitude;
		}
		public double getLongitude() {
			return longitude;
		}
		public void setLongitude(double longitude) {
			this.longitude = longitude;
		}
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
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
		public List<Patrol> getCars() {
			return cars;
		}
		public void setCars(List<Patrol> cars) {
			this.cars = cars;
		}
		public List<Vehicle> getVehicles() {
			return vehicles;
		}
		public void setVehicles(List<Vehicle> vehicles) {
			this.vehicles = vehicles;
		}
		
		
	
}
