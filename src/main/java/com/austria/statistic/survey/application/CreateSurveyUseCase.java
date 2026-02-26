package com.austria.statistic.survey.application;

import org.springframework.stereotype.Service;

import com.austria.statistic.survey.domain.model.Survey;
import com.austria.statistic.survey.domain.model.SurveyDates;
import com.austria.statistic.survey.domain.port.SurveyRepositoryPort;

@Service
public class CreateSurveyUseCase {

    private final SurveyRepositoryPort repository;

    public CreateSurveyUseCase(SurveyRepositoryPort repository) {
        this.repository = repository;
    }

    public Survey create(String title, String description, SurveyDates dates) {
        Survey survey = Survey.create(title, description, dates);
        return repository.save(survey);
    }
}
