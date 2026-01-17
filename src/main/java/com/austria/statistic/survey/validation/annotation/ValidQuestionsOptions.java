package com.austria.statistic.survey.validation.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.austria.statistic.survey.validation.validator.QuestionOptionsValidator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = QuestionOptionsValidator.class)
public @interface ValidQuestionsOptions {
    String message() default "Choice-Fragen benötigen mindestens zwei Antwortoptionen";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
