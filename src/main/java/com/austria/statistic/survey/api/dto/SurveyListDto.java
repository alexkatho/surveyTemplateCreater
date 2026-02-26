package com.austria.statistic.survey.api.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public record SurveyListDto(
        Long id,
        String title,
        LocalDate startDate,
        LocalDate endDate
) {}

