package com.austria.statistic.survey.question.dto;

import java.util.List;

import com.austria.statistic.survey.answer.dto.AnswerOptionCreateDto;
import com.austria.statistic.survey.question.QuestionType;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
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
public class QuestionCreateDto {

	@NotBlank (message = "Text darf nicht leer sein!")
	private String text;
	@NotNull (message = "Fragetyp ist Pflicht")
    private QuestionType type;
	@NotNull(message = "Muss gesetzt werden")
    private Boolean required; // optional
	@Positive(message = "Position muss > 0 sein ")
    private Integer position; // opt
	private List<AnswerOptionCreateDto> options;

}
