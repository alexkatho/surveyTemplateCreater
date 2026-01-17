package com.austria.statistic.survey.question.dto;

import java.util.List;

import com.austria.statistic.survey.answer.dto.AnswerOptionDto;
import com.austria.statistic.survey.question.QuestionType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class QuestionDto {
	private Long id;
	private QuestionType type;
	private String text;
	private Integer position;
	private List<AnswerOptionDto> options;

}
