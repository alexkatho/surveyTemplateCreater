package com.austria.statistic.location;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LocationController {

	private final LocationService db;

	public LocationController(LocationService db) {
		super();
		this.db = db;
	}
	
	@GetMapping("/locations")
	public List<LocationDto> getLocations(){
		return db.getAllLocations();
	}
	@GetMapping("/locations/{id}")
	public LocationDto getLocation(@PathVariable int id){
		return db.getLocation(id);
	}
	
	
}
