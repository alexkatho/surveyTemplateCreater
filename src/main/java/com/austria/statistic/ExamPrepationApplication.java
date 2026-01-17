package com.austria.statistic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class ExamPrepationApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExamPrepationApplication.class, args);
	}

}
