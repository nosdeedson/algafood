package com.ejs.algaworksCurso.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.ejs.algaworksCurso.domain.model.Produto;

@Repository
public interface ProdutoRepository extends CustomJpaRepository<Produto, Long>{
	
	Optional<Produto> findByIdAndRestaurante_Id(Long id, Long RestauranteId);
	
	List<Produto> findByRestauranteId(Long RestauranteId);

}
