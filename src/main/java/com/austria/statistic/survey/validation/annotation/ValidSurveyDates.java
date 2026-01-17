package com.austria.statistic.survey.validation.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.austria.statistic.survey.validation.validator.SurveyDateValidator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = SurveyDateValidator.class)
public @interface ValidSurveyDates {

    String message() default "Enddatum muss nach Startdatum liegen";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
