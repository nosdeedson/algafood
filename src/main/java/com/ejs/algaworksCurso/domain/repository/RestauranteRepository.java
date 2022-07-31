package com.ejs.algaworksCurso.domain.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ejs.algaworksCurso.domain.model.FormaPagamento;
import com.ejs.algaworksCurso.domain.model.Restaurante;

@Repository
public interface RestauranteRepository extends CustomJpaRepository<Restaurante, Long>,
	JpaSpecificationExecutor<Restaurante>{
	
	
	@Query("select distinct r from Restaurante r join r.cozinha LEFT JOIN FETCH r.formasPagamento WHERE r.ativo = true")
	List<Restaurante> todas(Sort sort);
	
	@Query("SELECT f FROM Restaurante r join r.formasPagamento f WHERE r.id = :id AND f = :formaPagamento")
	FormaPagamento findByIdAndFormaPagamento(@Param("id") Long id, @Param("formaPagamento") FormaPagamento formaPagamento);
	
}
