package com.austria.statistic.survey.answer;


import java.util.List;

import com.austria.statistic.survey.answer.dto.AnswerOptionCreateDto;
import com.austria.statistic.survey.answer.dto.AnswerOptionDto;

public class AnswerOptionMapper {

    public static AnswerOption toEntity(AnswerOptionCreateDto dto) {
        return AnswerOption.builder()
        		.label(dto.getLabel())
        		.value(dto.getLabel())
        		.position(dto.getPosition())
        .build();
    }

    public static AnswerOptionDto toDto(AnswerOption o) {
        return AnswerOptionDto.builder()
        		.id(o.getId())
        		.label(o.getLabel())
        		.position(o.getPosition())
        		.value(o.getValue())
        		.build();
    }

    public static List<AnswerOptionDto> toDtoList(List<AnswerOption> options) {
        return options.stream().map(AnswerOptionMapper::toDto).toList();
    }
}

