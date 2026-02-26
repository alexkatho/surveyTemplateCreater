package com.austria.statistic.survey.api.dto;

import java.time.LocalDate;
import java.util.List;

import com.austria.statistic.survey.domain.model.SurveyStatus;
import com.austria.statistic.survey.question.dto.QuestionCreateDto;
import com.austria.statistic.survey.validation.annotation.ValidSurveyDates;

import jakarta.validation.Valid;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ValidSurveyDates
public class SurveyCreateDto {
	@NotBlank(message = "Titel darf nicht leer sein!")
	private String title;

	@NotBlank(message = "Beschreibung darf nicht leer sein!")
	private String description;
	
	@NotNull(message = "Startdatum ist Pflicht")
	@FutureOrPresent
	private LocalDate startDate;
	
	@NotNull(message = "Enddatum ist Pflicht")
	@FutureOrPresent
	private LocalDate endDate;
	
	@NotNull(message = "Status ist Pflicht")
	private SurveyStatus status; // optional, z.B. DRAFT als Default
	
	@Size(min = 1, message = "Mindestens 1 Frage der Survey hinzufügen")
	private List<@Valid QuestionCreateDto> questions; // optional
}
