package com.austria.statistic.survey.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SurveyListDto {
	private Long id;
	private String title;
	private LocalDate startDate;
	private LocalDate endDate;
}
