package com.speedlog.entity;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document("Location")
public class Location {

	
	
	
	public Location(String id, String type, List<Double> coordinates) {
		super();
		this.id = id;
		this.type = type;
		this.coordinates = coordinates;
	}
	
	
	

	public Location() {
		super();
	}




	@Id
	String id;
	
	String type;
	
	List<Double> coordinates;
	
	long currentTime;
	

	public long getCurrentTime() {
		return currentTime;
	}




	public void setCurrentTime(long currentTime) {
		this.currentTime = currentTime;
	}




	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public void setCoordinates(List<Double> coordinates2) {
		this.coordinates = coordinates2;
	}
	
	
	
}
