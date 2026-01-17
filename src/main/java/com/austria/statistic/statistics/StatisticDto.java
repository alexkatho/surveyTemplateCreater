package com.austria.statistic.statistics;

import java.time.LocalDateTime;


public record StatisticDto(
	    Long id,
	    long population,
	    LocalDateTime time,
	    Long locationId,
	    String locationName
	) {}
