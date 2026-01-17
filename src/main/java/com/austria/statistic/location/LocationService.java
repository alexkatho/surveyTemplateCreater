package com.austria.statistic.location;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;

@Service
public class LocationService {

	private final LocationRepository repo;

	public LocationService(LocationRepository locationRepository) {
		super();
		this.repo = locationRepository;
	}

	private Optional<Location> findById(int id) {
		return repo.findById(id);
	}

	public LocationDto getLocation(Integer id) {
		return findById(id).map(LocationMapper::mapToDto)
				.orElseThrow(() -> new EntityNotFoundException("Location with id " + id + " not found"));
	}

	private Iterable<Location> findAll() {
		return repo.findAll();
	}

	public List<LocationDto> getAllLocations() {
		List<LocationDto> result = new ArrayList<LocationDto>();
		for (Location location : findAll()) {
			result.add(LocationMapper.mapToDto(location));
		}
		return result;
	}

}
