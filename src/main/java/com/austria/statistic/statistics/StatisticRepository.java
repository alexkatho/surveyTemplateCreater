package com.austria.statistic.statistics;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface StatisticRepository extends CrudRepository<Statistics, Integer>{
	
	Page<Statistics> findAll(Pageable pageable);

    Page<Statistics> findByLocation_Name(
            String name,
            Pageable pageable
    );
    
    @Query("select new com.austria.statistic.statistics.SurveyDto(s.location.name, s.population) " +
    	       "from Statistics s where s.population > :minPopulation")
    Page<SurveyDto> findByPopulationGreaterThan(long minPopulation, Pageable pageable);
    
    @Query("""
            select new com.austria.statistic.statistics.SurveyDto(
                s.location.name, 
                max(s.population)
            )
            from Statistics s
            group by s.location.name
            having max(s.population) > :minPopulation
            order by max(s.population) desc
        """)
        List<SurveyDto> findLargestLocationsWithPopulationGreaterThan(@Param("minPopulation") long minPopulation);

}
