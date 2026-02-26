package com.austria.statistic.survey.domain.port;

import java.util.List;
import java.util.Optional;

import com.austria.statistic.survey.domain.model.Survey;

public interface SurveyRepositoryPort {

    Survey save(Survey survey);

    Optional<Survey> findById(Long id);

    List<Survey> findAll();

    void deleteById(Long id);
}
