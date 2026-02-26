package com.austria.statistic.survey.question;

import java.util.ArrayList;
import java.util.List;

import com.austria.statistic.survey.answer.AnswerOption;
import com.austria.statistic.survey.answer.AnswerOptionMapper;
import com.austria.statistic.survey.infrastructure.persistence.entity.SurveyEntity;
import com.austria.statistic.survey.question.domain.QuestionType;
import com.austria.statistic.survey.question.dto.QuestionCreateDto;
import com.austria.statistic.survey.question.dto.QuestionDto;
import com.austria.statistic.survey.question.infrastructure.persistence.QuestionEntity;

public class QuestionMapper {

	public static QuestionDto toDto(QuestionEntity question) {
		return QuestionDto.builder().id(question.getId()).text(question.getText()).type(question.getType())
				.position(question.getPosition()).options(AnswerOptionMapper.toDtoList(question.getOptions())).build();
	}

	public static Iterable<QuestionDto> toDtoList(Iterable<QuestionEntity> questions) {
		List<QuestionDto> result = new ArrayList<QuestionDto>();
		questions.forEach(q -> result.add(toDto(q)));
		return result;
	}

	public static QuestionEntity toEntity(QuestionCreateDto created, SurveyEntity survey) {
		QuestionEntity entity = QuestionEntity.builder().position(created.getPosition()).required(created.getRequired())
				.text(created.getText()).type(created.getType()).survey(survey).build();

		entity.setOptions(new ArrayList<>());

		if (created.getOptions() != null && (entity.getType() == QuestionType.SINGLE_CHOICE
				|| entity.getType() == QuestionType.MULTIPLE_CHOICE)) {

			created.getOptions().forEach(optDto -> {
				AnswerOption opt = AnswerOption.builder().label(optDto.getLabel()).value(optDto.getValue())
						.position(optDto.getPosition()).question(entity).build();
				entity.getOptions().add(opt);
			});
		}
		return entity;

	}
}
