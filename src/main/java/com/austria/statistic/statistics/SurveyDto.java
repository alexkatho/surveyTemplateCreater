package com.austria.statistic.statistics;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SurveyDto {
	private String locationName;
	private Long maxPopulation;
}
