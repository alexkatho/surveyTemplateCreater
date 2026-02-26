package com.austria.statistic.survey.api.mapper;

import java.util.List;

import com.austria.statistic.survey.api.dto.SurveyCreateDto;
import com.austria.statistic.survey.api.dto.SurveyDetailDto;
import com.austria.statistic.survey.api.dto.SurveyListDto;
import com.austria.statistic.survey.domain.model.Survey;
import com.austria.statistic.survey.domain.model.SurveyDates;
import com.austria.statistic.survey.domain.model.SurveyStatus;
import com.austria.statistic.survey.question.QuestionMapper;
import com.austria.statistic.survey.question.dto.QuestionDto;

public class SurveyApiMapper {

	public static SurveyListDto toListDto(Survey survey) {
        return new SurveyListDto(
                survey.getId(),
                survey.getTitle(),
                survey.getDates().startDate(),
                survey.getDates().endDate()
        );
    }
	
	// to do changes 
	public static SurveyDetailDto toDetailDto(Survey survey) {

        List<QuestionDto> questionDtos = survey.getQuestions()
                .stream()
                .map(QuestionMapper::toDto)
                .toList();

        return new SurveyDetailDto(
                survey.getId(),
                survey.getTitle(),
                survey.getStatus(),
                questionDtos,
                survey.getDates().startDate(),
                survey.getDates().endDate()
        );
    }

    public static Survey toDomain(SurveyCreateDto dto) {
    	 Survey survey = Survey.create(
                 dto.getTitle(),
                 dto.getDescription(),
                 new SurveyDates(dto.getStartDate(), dto.getEndDate())
         );
        
    	// question mapper toDo
        if (dto.getQuestions() != null) {
            dto.getQuestions().forEach(q ->
                    survey.addQuestion(
                            QuestionMapper.toDomain(q)
                    )
            );
        }
        
        if (dto.getStatus() == SurveyStatus.ACTIVE) {
            survey.publish();
        }

        if (dto.getStatus() == SurveyStatus.CLOSED) {
            survey.publish();
            survey.close();
        }

        return survey;
    }
}
