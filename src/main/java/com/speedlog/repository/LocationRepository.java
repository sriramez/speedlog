package com.speedlog.repository;

import java.util.List;

import org.springframework.data.geo.Box;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.speedlog.entity.Location;

public interface LocationRepository extends MongoRepository<Location, String>{

	List<Location> findByCoordinatesWithin(Box b);
}
