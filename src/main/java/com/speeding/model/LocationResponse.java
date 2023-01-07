package com.speeding.model;

public class LocationResponse {

	double lat;
	
	double lng;
	
	String vehicleName;
	
	
	public String getVehicleName() {
		return vehicleName;
	}

	public void setVehicleName(String vehicleName) {
		this.vehicleName = vehicleName;
	}

	public LocationResponse(double lat, double lng,String vehicleName) {
		super();
		this.lat = lat;
		this.lng = lng;
		this.vehicleName=vehicleName;
	}

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public double getLng() {
		return lng;
	}

	public void setLng(double lng) {
		this.lng = lng;
	}
	
	
}
