package com.austria.statistic.survey.domain.model;

import java.time.LocalDate;

public record SurveyDates(LocalDate startDate, LocalDate endDate) {

    public SurveyDates {
        if (startDate == null) {
            throw new IllegalArgumentException("Start date must not be null");
        }
        if (endDate != null && endDate.isBefore(startDate)) {
            throw new IllegalArgumentException("End date must not be before start date");
        }
    }

    public boolean isActiveOn(LocalDate date) {
        return (date.isEqual(startDate) || date.isAfter(startDate))
                && (endDate == null || date.isBefore(endDate) || date.isEqual(endDate));
    }
    
    public LocalDate startDate() { return startDate; }
    public LocalDate endDate() { return endDate; }
}
