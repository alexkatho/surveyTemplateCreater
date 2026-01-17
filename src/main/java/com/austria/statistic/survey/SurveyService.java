package com.austria.statistic.survey;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.austria.statistic.survey.dto.SurveyCreateDto;
import com.austria.statistic.survey.dto.SurveyDetailDto;
import com.austria.statistic.survey.dto.SurveyListDto;
import com.austria.statistic.survey.mapper.SurveyMapper;
import com.austria.statistic.survey.question.dto.QuestionDto;
import com.austria.statistic.survey.question.repository.QuestionRepository;

@Service
public class SurveyService  <Q>{

	private final SurveyRepository surveyRepo;
	private final QuestionRepository questionRepo;

	public SurveyService(SurveyRepository surveyRepo, QuestionRepository questionRepo) {
		super();
		this.surveyRepo = surveyRepo;
		this.questionRepo = questionRepo;
	}

	public Page<SurveyListDto> getAllSurveys(Pageable pageable) {
		Page<Survey> surveys = surveyRepo.findAll(pageable);
		return SurveyMapper.toPageDto(surveys);
	}

	public SurveyDetailDto<QuestionDto> getSurveyById(Long id) {
		Survey survey = surveyRepo.findById(id).orElseThrow(() -> new RuntimeException("Survey not found"));
		return SurveyMapper.toDetailDto(survey); // Platzhalter: QuestionDto wird hier später eingebunden
	}

	public Survey createSurvey(SurveyCreateDto dto) {
        Survey survey = SurveyMapper.toEntity(dto);
        return surveyRepo.save(survey);
    }
}
