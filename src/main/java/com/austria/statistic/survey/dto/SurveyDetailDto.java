package com.austria.statistic.survey.dto;

import java.time.LocalDate;
import java.util.List;

import com.austria.statistic.survey.domain.SurveyStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SurveyDetailDto<Q> {

	private Long id;
    private String title;
    private SurveyStatus status;
    private List<Q> questions;
    private LocalDate startDate;
    private LocalDate endDate;
}
