package com.austria.statistic.survey.question.repository;

import org.springframework.data.repository.CrudRepository;

import com.austria.statistic.survey.question.domain.Question;

public interface QuestionRepository extends CrudRepository<Question, Long>{
	
	

}
