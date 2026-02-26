package com.austria.statistic.survey.infrastructure.persistence.mapper;

import java.util.List;

import com.austria.statistic.survey.domain.model.Survey;
import com.austria.statistic.survey.domain.model.SurveyDates;
import com.austria.statistic.survey.infrastructure.persistence.entity.SurveyEntity;
import com.austria.statistic.survey.question.infrastructure.persistence.mapper.QuestionPersistenceMapper;

public class SurveyPersistenceMapper {

    public static Survey toDomain(SurveyEntity entity) {
        Survey survey = new Survey(
                entity.getId(),
                entity.getTitle(),
                entity.getDescription(),
                new SurveyDates(entity.getStartDate(), entity.getEndDate())
        );

        entity.getQuestions()
                .stream()
                .map(QuestionPersistenceMapper::toDomain)
                .forEach(survey::addQuestion);

        survey.assignId(entity.getId());
        return survey;
    }

    public static SurveyEntity toEntity(Survey domain) {
        SurveyEntity entity = new SurveyEntity();
        entity.setId(domain.getId());
        entity.setTitle(domain.getTitle());
        entity.setDescription(domain.getDescription());
        entity.setStatus(domain.getStatus());
        entity.setStartDate(domain.getDates().startDate());
        entity.setEndDate(domain.getDates().endDate());

        List var questionEntities = domain.getQuestions().stream()
                .map(q -> QuestionPersistenceMapper.toEntity(q, entity))
                .collect(Collectors.toList());

        entity.setQuestions(questionEntities);
        return entity;
    }
}
