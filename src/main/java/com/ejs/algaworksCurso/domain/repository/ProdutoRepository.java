package com.ejs.algaworksCurso.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ejs.algaworksCurso.domain.model.FotoProduto;
import com.ejs.algaworksCurso.domain.model.Produto;

@Repository
public interface ProdutoRepository extends CustomJpaRepository<Produto, Long>{
	
	Optional<Produto> findByIdAndRestaurante_Id(Long id, Long restauranteId);
	
	List<Produto> findByRestauranteId(Long restauranteId);
	
	List<Produto> findByRestauranteIdAndAtivo(Long restauranteId, boolean ativo);
	
	@Query("SELECT f FROM FotoProduto f join Produto p "
			+ " ON f.produto = p WHERE p.id= :produtoId AND p.restaurante.id= :restauranteId ")
	Optional<FotoProduto> findByRestauranteIdProdutoId(@Param("restauranteId") Long restauranteId,
			@Param("produtoId") Long produtoId);

}
