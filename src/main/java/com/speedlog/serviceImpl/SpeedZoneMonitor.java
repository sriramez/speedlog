package com.speedlog.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.geo.Box;
import org.springframework.data.geo.Point;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.speedlog.entity.Location;
import com.speedlog.entity.PoliceStation;
import com.speedlog.entity.SpeedZone;
import com.speedlog.entity.Vehicle;
import com.speedlog.repository.LocationRepository;
import com.speedlog.repository.PoliceStationRepository;
import com.speedlog.repository.SpeedZoneRepository;
import com.speedlog.repository.VehicleRepository;

@Component
public class SpeedZoneMonitor {

	@Autowired
	SpeedZoneRepository zoneRepo;

	@Autowired
	LocationRepository locationRepo;
	
	@Autowired
	VehicleService vehicleService;
	
	
	@Autowired
	VehicleRepository vehicleRepo;
	
	@Autowired
	PoliceStationRepository stationRepository;
	
	@Scheduled(fixedRate = 100000)
	public void scanVehicleSpeeds() {
		List<SpeedZone> findAll = zoneRepo.findAll();
		for(int i=0;i<findAll.size();i++)
		{
			SpeedZone zone = findAll.get(i);
			
			List<Location> locations = locationRepo.findByCoordinatesWithin(
					new Box(new Point( zone.getBottomLeftlongitude(),zone.getBottomLeftLatitude()),
							new Point( zone.getTopRightLongitude(),zone.getTopRightLatitude())));
			for(int j=0;j<locations.size();j++)
			{
				Location location = locations.get(j);
				if(location.isCurrent()==true)
				{
					Vehicle vehicle = location.getVehicle();
					vehicle = vehicleRepo.findByCarNumber(vehicle.getCarNumber());
					Location current = vehicle.getCurrentLocation();
					Location previous = vehicle.getPreviousLocation();
					if(current.getCoordinates()!=null && previous.getCoordinates()!=null)
					{
						long currentTime = vehicle.getCurrentLocation().getCurrentTime();
						long previousTime = vehicle.getPreviousLocation().getCurrentTime();
						long timeDiff = currentTime - previousTime;
						double distance = vehicleService.getDistanceBetweenTwoPointsInMiles(current.getCoordinates().get(1), current.getCoordinates().get(0), previous.getCoordinates().get(1),
								previous.getCoordinates().get(0));
						double timeDifference = Double.valueOf(timeDiff) / Double.valueOf(3600000);
						double speedInMiles = distance / timeDifference;
						if (speedInMiles > zone.getSpeedlimit()) {
							PoliceStation station = vehicleService.getNearByStation(vehicle);
							if(!station.getVehicles().contains(vehicle))
							{
								station.getVehicles().add(vehicle);
								stationRepository.save(station);
							}
							
						}
			
					}

				}
			}

		}
			
	}
}
