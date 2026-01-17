package com.austria.statistic.survey.answer.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AnswerOptionCreateDto {

    @NotBlank
    private String label;

    @NotBlank
    private String value;

    @NotNull
    @PositiveOrZero
    private Integer position;

}

