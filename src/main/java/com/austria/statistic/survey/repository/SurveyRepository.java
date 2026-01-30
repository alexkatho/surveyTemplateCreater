package com.austria.statistic.survey.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import com.austria.statistic.statistics.Statistics;
import com.austria.statistic.survey.domain.Survey;
import com.austria.statistic.survey.domain.SurveyStatus;

public interface SurveyRepository extends CrudRepository<Survey, Long>{
	
	// pagination 
	Page<Survey> findByStatus(SurveyStatus status, Pageable pageable);
	
	Page<Survey> findAll(Pageable pageable);

}
