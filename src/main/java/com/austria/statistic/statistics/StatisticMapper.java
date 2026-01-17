package com.austria.statistic.statistics;

public class StatisticMapper {

	public static StatisticDto mapToDto (Statistics statistic) {
		return new StatisticDto(statistic.getId(), statistic.getPopulation(),
				statistic.getTime(), statistic.getLocation().getId(), statistic.getLocation().getName());	
	}
}
