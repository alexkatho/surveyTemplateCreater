package com.austria.statistic.statistics;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.constraints.Min;

@RestController
public class StatisticController {

	
	@Autowired
	private final StatisticService db;
	
	StatisticController(StatisticService statisticService){
		db = statisticService;
	}
	
	
	@GetMapping("/statistics")
	public Page<StatisticDto> getStatistics(
	        @RequestParam(required = false) String locationName, Pageable pageable
	) {
	    return db.getAllStatistics(locationName, pageable);
	}


	@GetMapping("/statistics/{id}")
	public StatisticDto getStatistic(@PathVariable int id) {
		return db.getStatistic((id));
	}
	
	@GetMapping("/statistics/survey")
    public List<SurveyDto> getLargestLocationsWithPopulationOver(
            @RequestParam("minPopulation") long minPopulation) {
        return db.getLocationsWithPopulationGreaterThan(minPopulation);
    }
	
	@GetMapping("/statistics/survey/all")
    public Page<SurveyDto> getAllLocationsWithPopulationOver(
            @RequestParam("minPopulation") @Min(0) long minPopulation, Pageable pageable) {
        return db.getAllStatisticsWithGreaterThanPopulation(minPopulation, pageable);
    }
	
	

}
