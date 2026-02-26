package com.austria.statistic.survey.api.dto;

import java.time.LocalDate;
import java.util.List;

import com.austria.statistic.survey.domain.model.SurveyStatus;

public record SurveyDetailDto<Q>(

    Long id,
    String title,
    SurveyStatus status,
    List<Q> questions,
    LocalDate startDate,
    LocalDate endDate

) {}
