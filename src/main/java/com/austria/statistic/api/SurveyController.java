package com.austria.statistic.api;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.austria.statistic.survey.domain.Survey;
import com.austria.statistic.survey.dto.SurveyCreateDto;
import com.austria.statistic.survey.dto.SurveyDetailDto;
import com.austria.statistic.survey.dto.SurveyListDto;
import com.austria.statistic.survey.mapper.SurveyMapper;
import com.austria.statistic.survey.question.dto.QuestionDto;
import com.austria.statistic.survey.service.SurveyService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/survey")
public class SurveyController {

	private final SurveyService surveyService;

	public SurveyController(SurveyService surveyService) {
		super();
		this.surveyService = surveyService;
	}

	@GetMapping
	public Page<SurveyListDto> getSurveys(Pageable pageable) {
		return surveyService.getAllSurveys(pageable);
	}
	
	@GetMapping("/{id}")
	public SurveyDetailDto<QuestionDto> getSurveyById(@PathVariable Long id) {
		return surveyService.getSurveyById(id);
	}
	
	@PostMapping
	public SurveyDetailDto<QuestionDto> createSurvey(@Valid @RequestBody SurveyCreateDto dto) {
	    Survey saved = surveyService.createSurvey(dto);
	    return SurveyMapper.toDetailDto(saved);
	}

	
}	
