package com.austria.statistic.survey.question.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataQuestionRepository extends JpaRepository<QuestionEntity, Long> {
}
