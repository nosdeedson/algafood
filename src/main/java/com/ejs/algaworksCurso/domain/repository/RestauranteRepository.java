package com.ejs.algaworksCurso.domain.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.ejs.algaworksCurso.domain.model.Restaurante;

@Repository
public interface RestauranteRepository extends CustomJpaRepository<Restaurante, Long>,
	JpaSpecificationExecutor<Restaurante>{
	
}
