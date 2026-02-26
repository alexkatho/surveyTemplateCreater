package com.austria.statistic.survey.question.infrastructure.persistence;


import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.austria.statistic.survey.question.domain.Question;
import com.austria.statistic.survey.question.domain.port.QuestionRepositoryPort;
import com.austria.statistic.survey.question.infrastructure.persistence.mapper.QuestionPersistenceMapper;

@Repository
public class JpaQuestionRepository implements QuestionRepositoryPort {

    private final SpringDataQuestionRepository jpaRepository;

    public JpaQuestionRepository(SpringDataQuestionRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Question save(Question question) {
        QuestionEntity entity = QuestionPersistenceMapper.toEntity(question);
        QuestionEntity saved = jpaRepository.save(entity);
        return QuestionPersistenceMapper.toDomain(saved);
    }

    @Override
    public Optional<Question> findById(Long id) {
        return jpaRepository.findById(id)
                .map(QuestionPersistenceMapper::toDomain);
    }

    @Override
    public List<Question> findAll() {
        return jpaRepository.findAll()
                .stream()
                .map(QuestionPersistenceMapper::toDomain)
                .toList();
    }

    @Override
    public void deleteById(Long id) {
        jpaRepository.deleteById(id);
    }

	@Override
	public List<Question> findBySurveyId(Long surveyId) {
		return null;
	}
}