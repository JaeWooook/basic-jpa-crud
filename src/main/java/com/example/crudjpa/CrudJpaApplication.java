package com.example.crudjpa;

import com.example.crudjpa.global.config.TestDataInit;
import com.example.crudjpa.noticeboard.repository.NoticeBoardRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CrudJpaApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrudJpaApplication.class, args);
	}

	@Bean
	public TestDataInit testDataInit(NoticeBoardRepository noticeBoardRepository) {
		return new TestDataInit(noticeBoardRepository);
	}
}
