package com.austria.statistic.survey.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;

import com.austria.statistic.survey.domain.SurveyStatus;
import com.austria.statistic.survey.dto.SurveyCreateDto;
import com.austria.statistic.survey.dto.SurveyDetailDto;
import com.austria.statistic.survey.dto.SurveyListDto;
import com.austria.statistic.survey.persistance.SurveyEntity;
import com.austria.statistic.survey.question.QuestionMapper;
import com.austria.statistic.survey.question.dto.QuestionDto;

public class SurveyMapper<Q> {

	public static SurveyListDto toDto(SurveyEntity survey) {
		return new SurveyListDto(survey.getId(), survey.getTitle(), survey.getStartDate(), survey.getEndDate());
	}
	
	public static Page<SurveyListDto> toPageDto(Page<SurveyEntity> surveyPage) {
	    return surveyPage.map(SurveyMapper::toDto);
	}

	
	public static SurveyDetailDto<QuestionDto> toDetailDto(SurveyEntity survey){
		 List<QuestionDto> questions = survey.getQuestions().stream()
	                .map(QuestionMapper::toDto)
	                .toList();

	        return SurveyDetailDto.<QuestionDto>builder()
	                .id(survey.getId())
	                .title(survey.getTitle())
	                .status(survey.getStatus())
	                .questions(questions)
	                .startDate(survey.getStartDate())
	                .endDate(survey.getEndDate())
	                .build();
		
	}
	
	public static SurveyEntity toEntity(SurveyCreateDto dto) {
        SurveyEntity survey = SurveyEntity.builder()
                .title(dto.getTitle())
                .description(dto.getDescription())
                .startDate(dto.getStartDate())
                .endDate(dto.getEndDate())
                .status(dto.getStatus() != null ? dto.getStatus() : SurveyStatus.DRAFT)
                .build();

        if (dto.getQuestions() != null) {
            survey.setQuestions(dto.getQuestions().stream()
                    .map(qDto -> QuestionMapper.toEntity(qDto, survey)) // ⬅ options werden dort gemappt
                    .collect(Collectors.toList()));
        }

        return survey;
    }

	
	
}
