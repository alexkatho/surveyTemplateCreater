package com.austria.statistic.survey.application;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.austria.statistic.survey.api.dto.SurveyCreateDto;
import com.austria.statistic.survey.api.dto.SurveyDetailDto;
import com.austria.statistic.survey.api.dto.SurveyListDto;
import com.austria.statistic.survey.infrastructure.persistence.entity.SurveyEntity;
import com.austria.statistic.survey.infrastructure.persistence.repository.SurveyRepository;
import com.austria.statistic.survey.mapper.SurveyMapper;
import com.austria.statistic.survey.question.dto.QuestionDto;
import com.austria.statistic.survey.question.infrastructure.persistence.JpaQuestionRepository;
import com.austria.statistic.survey.question.infrastructure.persistence.QuestionJpaRepository;

@Service
public class SurveyService  <Q>{

	private final SurveyRepository surveyRepo;
	private final JpaQuestionRepository questionRepo;

	public SurveyService(SurveyRepository surveyRepo, JpaQuestionRepository questionRepo) {
		super();
		this.surveyRepo = surveyRepo;
		this.questionRepo = questionRepo;
	}

	public Page<SurveyListDto> getAllSurveys(Pageable pageable) {
		Page<SurveyEntity> surveys = surveyRepo.findAll(pageable);
		return SurveyMapper.toPageDto(surveys);
	}

	public SurveyDetailDto<QuestionDto> getSurveyById(Long id) {
		SurveyEntity survey = surveyRepo.findById(id).orElseThrow(() -> new RuntimeException("Survey not found"));
		return SurveyMapper.toDetailDto(survey); // Platzhalter: QuestionDto wird hier später eingebunden
	}

	public SurveyEntity createSurvey(SurveyCreateDto dto) {
        SurveyEntity survey = SurveyMapper.toEntity(dto);
        return surveyRepo.save(survey);
    }
}
