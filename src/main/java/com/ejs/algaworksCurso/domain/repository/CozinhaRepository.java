package com.ejs.algaworksCurso.domain.repository;

import org.springframework.stereotype.Repository;

import com.ejs.algaworksCurso.domain.model.Cozinha;

@Repository
public interface CozinhaRepository extends CustomJpaRepository<Cozinha, Long> {
	

}
