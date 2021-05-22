package com.ejs.algaworksCurso;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.ejs.algaworksCurso.infrastructure.repository.CustomJpaRepositoryImpl;

@SpringBootApplication
@EnableJpaRepositories(repositoryBaseClass = CustomJpaRepositoryImpl.class)
public class AlgafoodApplication {

	public static void main(String[] args) {
		SpringApplication.run(AlgafoodApplication.class, args);
	}

}
