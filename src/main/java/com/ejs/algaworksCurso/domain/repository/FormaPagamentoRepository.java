package com.ejs.algaworksCurso.domain.repository;

import java.time.OffsetDateTime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ejs.algaworksCurso.domain.model.FormaPagamento;

@Repository
public interface FormaPagamentoRepository extends JpaRepository<FormaPagamento, Long> {

	@Query("SELECT MAX(dataAtualizacao) FROM FormaPagamento")
	OffsetDateTime ultimaDataAtualizacao();
}
