package com.ejs.algaworksCurso.domain.repository;

import java.time.OffsetDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ejs.algaworksCurso.domain.model.FormaPagamento;

@Repository
public interface FormaPagamentoRepository extends JpaRepository<FormaPagamento, Long> {
		
	@Query(value = "SELECT fp.* FROM forma_pagamento fp "
			+ " WHERE fp.id NOT IN( "
			+ " SELECT fp.id FROM forma_pagamento fp"
			+ " LEFT JOIN restaurante_forma_pagamento rfp "
			+ " ON rfp.forma_pagamento_id = fp.id"
			+ " WHERE rfp.restaurante_id= :restauranteId )", nativeQuery = true)
	List<FormaPagamento> findFormaPagamentoDisassociadoRestaurante(@Param("restauranteId") Long restauranteId);

	@Query("SELECT MAX(dataAtualizacao) FROM FormaPagamento")
	OffsetDateTime ultimaDataAtualizacao();
}
