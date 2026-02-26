package com.austria.statistic.survey.infrastructure.persistence.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.austria.statistic.survey.domain.model.Survey;
import com.austria.statistic.survey.domain.port.SurveyRepositoryPort;
import com.austria.statistic.survey.infrastructure.persistence.entity.SurveyEntity;
import com.austria.statistic.survey.infrastructure.persistence.mapper.SurveyPersistenceMapper;

@Repository
public class JpaSurveyRepository implements SurveyRepositoryPort {

    private final SpringDataSurveyRepository jpaRepository;

    public JpaSurveyRepository(SpringDataSurveyRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Survey save(Survey survey) {
        SurveyEntity entity = SurveyPersistenceMapper.toEntity(survey);
        SurveyEntity saved = jpaRepository.save(entity);
        return SurveyPersistenceMapper.toDomain(saved);
    }

    @Override
    public Optional<Survey> findById(Long id) {
        return jpaRepository.findById(id)
                .map(SurveyPersistenceMapper::toDomain);
    }

    @Override
    public List<Survey> findAll() {
        return jpaRepository.findAll()
                .stream()
                .map(SurveyPersistenceMapper::toDomain)
                .toList();
    }

    @Override
    public void deleteById(Long id) {
        jpaRepository.deleteById(id);
    }
}
