package com.speeding.model;

import java.util.List;

public class LocationModel {
	String type;
	List<Double> coordinates;
	
	public LocationModel(String type, List<Double> arrayList) {
		super();
		this.type = type;
		this.coordinates = arrayList;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public List<Double> getCoordinates() {
		return coordinates;
	}
	public void setCoordinates(List<Double> coordinates) {
		this.coordinates = coordinates;
	}
	
	
}
