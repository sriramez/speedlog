package com.speedlog.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.speedlog.entity.SpeedZone;

public interface SpeedZoneRepository extends MongoRepository<SpeedZone, Long>{

}
