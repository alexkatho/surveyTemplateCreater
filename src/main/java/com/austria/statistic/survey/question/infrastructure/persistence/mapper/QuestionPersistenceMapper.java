package com.austria.statistic.survey.question.infrastructure.persistence.mapper;

import java.util.stream.Collectors;

import com.austria.statistic.survey.question.domain.AnswerOption;
import com.austria.statistic.survey.question.domain.Question;
import com.austria.statistic.survey.question.infrastructure.persistence.AnswerOptionEntity;
import com.austria.statistic.survey.question.infrastructure.persistence.QuestionEntity;

public class QuestionPersistenceMapper {

    /** Domain -> Entity */
    public static QuestionEntity toEntity(Question question) {
        QuestionEntity entity = QuestionEntity.builder()
                .id(question.getId())
                .text(question.getText())
                .type(question.getType())
                .position(question.getPosition())
                .required(question.getRequired())
                .build();

        // Domain Question gibt immer eine leere Liste, nie null
        if (!question.getOptions().isEmpty()) {
            entity.setOptions(
                question.getOptions().stream()
                        .map(opt -> toEntity(opt, entity))
                        .collect(Collectors.toList())
            );
        }

        return entity;
    }

    private static AnswerOptionEntity toEntity(AnswerOption option, QuestionEntity questionEntity) {
        return AnswerOptionEntity.builder()
                .id(option.getId())
                .label(option.getLabel())
                .value(option.getValue())
                .position(option.getPosition())
                .question(questionEntity)
                .build();
    }

    /** Entity -> Domain */
    public static Question toDomain(QuestionEntity entity) {
        // Factory-Methode nutzen
        Question question = Question.create(entity.getText(),
                entity.getType(),
                entity.getPosition());

        if (entity.getOptions() != null && !entity.getOptions().isEmpty()) {
            for (AnswerOptionEntity optEntity : entity.getOptions()) {
                question.addOption(optEntity.getLabel(), optEntity.getValue(), optEntity.getPosition());
            }
        }

        // required-Flag setzen
        if (!entity.isRequired()) {
            question.isRequiredFalse();
        }

        // ID setzen über Reflection oder Setter in Entity (optional, falls benötigt)
        setId(question, entity.getId());

        return question;
    }

    private static void setId(Question question, Long id) {
        try {
            var field = Question.class.getDeclaredField("id");
            field.setAccessible(true);
            field.set(question, id);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException("Failed to set ID in Question domain", e);
        }
    }

    private static AnswerOption toDomain(AnswerOptionEntity entity) {
        return new AnswerOption(entity.getLabel(), entity.getValue(), entity.getPosition());
    }
}