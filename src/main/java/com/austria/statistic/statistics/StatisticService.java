package com.austria.statistic.statistics;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;

@Service
public class StatisticService {

	private final StatisticRepository repo;

	public StatisticService(StatisticRepository repo) {
		super();
		this.repo = repo;
	}

	public Page<StatisticDto> getAllStatistics(String locationName, Pageable pageable) {
		Page<Statistics> page;

		if (locationName == null || locationName.isBlank()) {
			page = repo.findAll(pageable);
		} else {
			page = repo.findByLocation_Name(locationName, pageable);
		}

		return page.map(StatisticMapper::mapToDto);
	}

	public Page<SurveyDto> getAllStatisticsWithGreaterThanPopulation(long minPop, Pageable pageable) {

		return repo.findByPopulationGreaterThan(minPop, pageable);
	}

	private Optional<Statistics> findById(Integer id) {
		return repo.findById(id);
	}

	public StatisticDto getStatistic(Integer id) {
		return findById(id).map(StatisticMapper::mapToDto)
				.orElseThrow(() -> new EntityNotFoundException("Statistic with id " + id + " not found"));
	}

	public List<SurveyDto> getLocationsWithPopulationGreaterThan(long minPopulation) {
		return repo.findLargestLocationsWithPopulationGreaterThan(minPopulation);
	}

}
