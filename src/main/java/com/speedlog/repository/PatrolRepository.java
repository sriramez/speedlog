package com.speedlog.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.speedlog.entity.Patrol;

public interface PatrolRepository extends MongoRepository<Patrol, String>{
	
	@Query("{carNumber:'?0'}")
	Patrol findByCarNumber(String carNumber);

}
