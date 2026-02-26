package com.austria.statistic.survey.validation.validator;

import com.austria.statistic.survey.question.domain.QuestionType;
import com.austria.statistic.survey.question.dto.QuestionCreateDto;
import com.austria.statistic.survey.validation.annotation.ValidQuestionsOptions;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class QuestionOptionsValidator implements ConstraintValidator<ValidQuestionsOptions, QuestionCreateDto> {

	@Override
	public boolean isValid(QuestionCreateDto dto, ConstraintValidatorContext context) {

		if (dto == null)
			return true;
		if (dto.getType() == null)
			return true;

		if (dto.getType() == QuestionType.SINGLE_CHOICE || dto.getType() == QuestionType.MULTIPLE_CHOICE) {

			return dto.getOptions() != null && dto.getOptions().size() >= 2;
		}

		return true;
	}
}
