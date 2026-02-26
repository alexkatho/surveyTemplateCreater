package com.austria.statistic.survey.question.domain.port;

import java.util.List;
import java.util.Optional;

import com.austria.statistic.survey.question.domain.Question;
import com.austria.statistic.survey.question.infrastructure.persistence.QuestionEntity;


public interface QuestionRepositoryPort {
    Question save(Question question);

    Optional<Question> findById(Long id);

    List<Question> findAll();
    
    void deleteById(Long id);
    
    List<Question> findBySurveyId(Long surveyId);
}