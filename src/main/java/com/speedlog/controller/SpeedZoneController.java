package com.speedlog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.speedlog.entity.SpeedZone;
import com.speedlog.repository.SpeedZoneRepository;

@RestController
@RequestMapping("zone")
public class SpeedZoneController {
	
	@Autowired
	SpeedZoneRepository repo;
	
	@PostMapping
	public SpeedZone createZone(@RequestBody SpeedZone zone)
	{
		return repo.save(zone);
	}

}
