package com.ejs.algaworksCurso.domain.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ejs.algaworksCurso.domain.model.Restaurante;

@Repository
public interface RestauranteRepository extends CustomJpaRepository<Restaurante, Long>,
	JpaSpecificationExecutor<Restaurante>{
	
	
	@Query("select distinct r from Restaurante r join r.cozinha LEFT JOIN FETCH r.formasPagamento")
	List<Restaurante> todas(Sort sort);
	
}
