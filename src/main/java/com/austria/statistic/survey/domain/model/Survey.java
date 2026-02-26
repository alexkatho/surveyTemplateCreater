package com.austria.statistic.survey.domain.model;

import java.util.ArrayList;
import java.util.List;

import com.austria.statistic.survey.question.Question;

import lombok.Getter;

@Getter
public class Survey {

    private Long id;
    private String title;
    private String description;
    private SurveyStatus status;
    private SurveyDates dates;
    private final List<Question> questions = new ArrayList<>();

    public Survey(Long id, String title, String description, SurveyDates dates) {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Survey title must not be blank");
        }
        this.id = id;
        this.title = title;
        this.description = description;
        this.dates = dates;
        this.status = SurveyStatus.DRAFT;
    }

    public static Survey create(String title, String description, SurveyDates dates) {
        return new Survey(null, title, description, dates);
    }

    // Business behavior
    public void publish() {
        if (questions.isEmpty()) {
            throw new IllegalStateException("Survey must contain at least one question before publishing");
        }
        this.status = SurveyStatus.ACTIVE;
    }

    public void close() {
        this.status = SurveyStatus.CLOSED;
    }
    
    public void cancel() {
        this.status = SurveyStatus.CANCELLED;
    }

    public void addQuestion(Question question) {
        this.questions.add(question);
    }

    // Getters
   /* public Long getId() { return id; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public SurveyStatus getStatus() { return status; }
    public SurveyDates getDates() { return dates; }
    public List<Question> getQuestions() { return List.copyOf(questions); }
    */

    public void assignId(Long id) {
        this.id = id;
    }
}
