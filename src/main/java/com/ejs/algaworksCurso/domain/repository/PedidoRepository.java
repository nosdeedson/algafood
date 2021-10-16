package com.ejs.algaworksCurso.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.ejs.algaworksCurso.domain.model.Pedido;

public interface PedidoRepository extends CustomJpaRepository<Pedido, Long>,
	JpaSpecificationExecutor<Pedido>{
	
	@Query("FROM Pedido p JOIN FETCH p.cliente JOIN FETCH p.restaurante r JOIN FETCH r.cozinha")
	List<Pedido> findAll();
	
	Optional<Pedido> findByCodigo(String codigo);
	
	@Query("SELECT CASE WHEN COUNT(1) > 0 THEN TRUE ELSE FALSE END "
			+ " FROM Pedido p join p.restaurante rest join rest.responsaveis resp"
			+ " WHERE p.codigo= :codigo AND resp.id= :responsavelId")
	boolean findByCodigoAndResponsavelId(String codigo, Long responsavelId);
	
	@Query("SELECT CASE WHEN COUNT(1) > 0 THEN TRUE ELSE FALSE END "
			+ " FROM Pedido p join p.cliente c "
			+ " WHERE p.codigo= :codigo AND c.id= :usuarioId")
	boolean findByCodigoAndUsuarioId(String codigo, Long usuarioId);
}
