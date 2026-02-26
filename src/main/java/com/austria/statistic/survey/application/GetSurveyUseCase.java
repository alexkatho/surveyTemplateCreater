package com.austria.statistic.survey.application;

import org.springframework.stereotype.Service;

import com.austria.statistic.survey.domain.model.Survey;
import com.austria.statistic.survey.domain.port.SurveyRepositoryPort;

@Service
public class GetSurveyUseCase {

    private final SurveyRepositoryPort repository;

    public GetSurveyUseCase(SurveyRepositoryPort repository) {
        this.repository = repository;
    }

    public Survey getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Survey not found: " + id));
    }
}
