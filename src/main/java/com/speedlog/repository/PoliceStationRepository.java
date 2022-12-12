package com.speedlog.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.speedlog.entity.PoliceStation;

public interface PoliceStationRepository extends MongoRepository<PoliceStation, String>{

	@Query("{name:'?0'}")
	PoliceStation findByName(String name);
}
