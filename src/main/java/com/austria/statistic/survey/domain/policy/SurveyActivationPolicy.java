package com.austria.statistic.survey.domain.policy;

import com.austria.statistic.survey.domain.model.Survey;

public class SurveyActivationPolicy {

    public void validateCanBePublished(Survey survey) {
        if (survey.getQuestions().isEmpty()) {
            throw new IllegalStateException("Survey must have at least one question");
        }
    }
}
