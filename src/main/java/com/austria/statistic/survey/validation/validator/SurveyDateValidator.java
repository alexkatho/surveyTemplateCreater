package com.austria.statistic.survey.validation.validator;

import com.austria.statistic.survey.dto.SurveyCreateDto;
import com.austria.statistic.survey.validation.annotation.ValidSurveyDates;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class SurveyDateValidator implements ConstraintValidator<ValidSurveyDates, SurveyCreateDto> {

	@Override
	public boolean isValid(SurveyCreateDto dto, ConstraintValidatorContext context) {

		if (dto == null)
			return true; // null wird von @NotNull geprüft
		if (dto.getStartDate() == null || dto.getEndDate() == null)
			return true;

		return dto.getEndDate().isAfter(dto.getStartDate());
	}
}
