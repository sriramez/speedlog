package com.speedlog.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.speedlog.entity.Vehicle;

public interface VehicleRepository extends MongoRepository<Vehicle, String>{

	@Query("{carNumber:'?0'}")
	Vehicle findByCarNumber(String number);
}
