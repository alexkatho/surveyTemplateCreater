package com.austria.statistic.location;

public class LocationMapper {

	public static LocationDto mapToDto(Location location) {
		return new LocationDto(location.getId(), location.getName(), location.getLocationType().name());
	}
}
