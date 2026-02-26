package com.austria.statistic.survey.application;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.austria.statistic.survey.domain.model.Survey;
import com.austria.statistic.survey.domain.policy.SurveyActivationPolicy;
import com.austria.statistic.survey.domain.port.SurveyRepositoryPort;

@Service
public class PublishSurveyUseCase {

    private final SurveyRepositoryPort repository;
    private final SurveyActivationPolicy policy = new SurveyActivationPolicy();

    public PublishSurveyUseCase(SurveyRepositoryPort repository) {
        this.repository = repository;
    }

    @Transactional
    public Survey publish(Long surveyId) {
        Survey survey = repository.findById(surveyId)
                .orElseThrow(() -> new IllegalArgumentException("Survey not found: " + surveyId));

        policy.validateCanBePublished(survey);
        survey.publish();

        return repository.save(survey);
    }
}
