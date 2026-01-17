package com.austria.statistic.survey;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import com.austria.statistic.statistics.Statistics;

public interface SurveyRepository extends CrudRepository<Survey, Long>{
	
	// pagination 
	Page<Survey> findByStatus(SurveyStatus status, Pageable pageable);
	
	Page<Survey> findAll(Pageable pageable);

}
