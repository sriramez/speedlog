package com.speedlog.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.speedlog.entity.Location;

public interface LocationRepository extends MongoRepository<Location, String>{

}
