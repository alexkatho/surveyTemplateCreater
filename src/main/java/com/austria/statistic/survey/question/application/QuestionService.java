package com.austria.statistic.survey.question.application;


import java.util.List;

import org.springframework.stereotype.Service;

import com.austria.statistic.survey.question.domain.Question;
import com.austria.statistic.survey.question.domain.port.QuestionRepositoryPort;

@Service
public class QuestionService {

    private final QuestionRepositoryPort repository;

    public QuestionService(QuestionRepositoryPort repository) {
        this.repository = repository;
    }

    public Question createQuestion(Question question) {
        return repository.save(question);
    }

    public List<Question> getBySurvey(Long surveyId) {
        return repository.findBySurveyId(surveyId);
    }
}
