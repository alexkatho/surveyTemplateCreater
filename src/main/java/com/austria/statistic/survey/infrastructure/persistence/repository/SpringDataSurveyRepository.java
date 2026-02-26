package com.austria.statistic.survey.infrastructure.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.austria.statistic.survey.infrastructure.persistence.entity.SurveyEntity;

interface SpringDataSurveyRepository extends JpaRepository<SurveyEntity, Long> {
}
