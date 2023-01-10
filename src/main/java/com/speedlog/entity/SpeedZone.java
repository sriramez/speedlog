package com.speedlog.entity;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("speedZone")
public class SpeedZone {

	@Id
	long id;
	
	String zoneName;
	
	double speedlimit;
	
	double bottomLeftlongitude;
	
	double bottomLeftLatitude;
	
	double topRightLongitude;
	
	double topRightLatitude;

	
	
	public SpeedZone(long id, String zoneName, double bottomLeftlongitude, double bottomLeftLatitude,
			double topRightLongitude, double topRightLatitude,double speedlimit) {
		super();
		this.id = id;
		this.zoneName = zoneName;
		this.bottomLeftlongitude = bottomLeftlongitude;
		this.bottomLeftLatitude = bottomLeftLatitude;
		this.topRightLongitude = topRightLongitude;
		this.topRightLatitude = topRightLatitude;
		this.speedlimit = speedlimit;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public double getSpeedlimit() {
		return speedlimit;
	}

	public void setSpeedlimit(double speedlimit) {
		this.speedlimit = speedlimit;
	}

	public String getZoneName() {
		return zoneName;
	}

	public void setZoneName(String zoneName) {
		this.zoneName = zoneName;
	}

	public double getBottomLeftlongitude() {
		return bottomLeftlongitude;
	}

	public void setBottomLeftlongitude(double bottomLeftlongitude) {
		this.bottomLeftlongitude = bottomLeftlongitude;
	}

	public double getBottomLeftLatitude() {
		return bottomLeftLatitude;
	}

	public void setBottomLeftLatitude(double bottomLeftLatitude) {
		this.bottomLeftLatitude = bottomLeftLatitude;
	}

	public double getTopRightLongitude() {
		return topRightLongitude;
	}

	public void setTopRightLongitude(double topRightLongitude) {
		this.topRightLongitude = topRightLongitude;
	}

	public double getTopRightLatitude() {
		return topRightLatitude;
	}

	public void setTopRightLatitude(double topRightLatitude) {
		this.topRightLatitude = topRightLatitude;
	}
	
	
	
	
	
	
	
}
