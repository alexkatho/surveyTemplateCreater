package com.austria.statistic.survey.infrastructure.persistence.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import com.austria.statistic.statistics.Statistics;
import com.austria.statistic.survey.domain.model.SurveyStatus;
import com.austria.statistic.survey.infrastructure.persistence.entity.SurveyEntity;

public interface SurveyRepository extends CrudRepository<SurveyEntity, Long>{
	
	// pagination 
	Page<SurveyEntity> findByStatus(SurveyStatus status, Pageable pageable);
	
	Page<SurveyEntity> findAll(Pageable pageable);

}
