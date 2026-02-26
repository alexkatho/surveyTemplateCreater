package com.austria.statistic.survey.api;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.austria.statistic.survey.api.dto.SurveyCreateDto;
import com.austria.statistic.survey.api.dto.SurveyDetailDto;
import com.austria.statistic.survey.api.mapper.SurveyApiMapper;
import com.austria.statistic.survey.application.CreateSurveyUseCase;
import com.austria.statistic.survey.application.GetSurveyUseCase;
import com.austria.statistic.survey.application.PublishSurveyUseCase;
import com.austria.statistic.survey.domain.model.SurveyDates;

@RestController
@RequestMapping("/api/surveys")
public class SurveyController {

    private final CreateSurveyUseCase createSurvey;
    private final GetSurveyUseCase getSurvey;
    private final PublishSurveyUseCase publishSurvey;

    public SurveyController(CreateSurveyUseCase createSurvey,
                            GetSurveyUseCase getSurvey,
                            PublishSurveyUseCase publishSurvey) {
        this.createSurvey = createSurvey;
        this.getSurvey = getSurvey;
        this.publishSurvey = publishSurvey;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SurveyDetailDto create(@RequestBody SurveyCreateDto dto) {
        var survey = createSurvey.create(
                dto.title(),
                dto.description(),
                new SurveyDates(
                        dto.startDate(),
                        dto.endDate()
                )
        );
        return SurveyApiMapper.toDetailDto(survey);
    }

    @GetMapping("/{id}")
    public SurveyDetailDto get(@PathVariable Long id) {
        return SurveyApiMapper.toDetailDto(getSurvey.getById(id));
    }

    @PostMapping("/{id}/publish")
    public SurveyDetailDto publish(@PathVariable Long id) {
        return SurveyApiMapper.toDetailDto(publishSurvey.publish(id));
    }
}
