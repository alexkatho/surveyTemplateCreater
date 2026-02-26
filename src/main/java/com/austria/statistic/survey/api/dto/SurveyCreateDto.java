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

@ValidSurveyDates
public record SurveyCreateDto(

    @NotBlank(message = "Titel darf nicht leer sein!")
    String title,

    @NotBlank(message = "Beschreibung darf nicht leer sein!")
    String description,

    @NotNull(message = "Startdatum ist Pflicht")
    @FutureOrPresent
    LocalDate startDate,

    @NotNull(message = "Enddatum ist Pflicht")
    @FutureOrPresent
    LocalDate endDate,

    @NotNull(message = "Status ist Pflicht")
    SurveyStatus status,

    @Size(min = 1, message = "Mindestens 1 Frage der Survey hinzufügen")
    List<@Valid QuestionCreateDto> questions

) {}