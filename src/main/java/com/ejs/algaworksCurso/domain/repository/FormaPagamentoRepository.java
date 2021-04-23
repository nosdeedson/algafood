package com.ejs.algaworksCurso.domain.repository;

import java.util.List;

import com.ejs.algaworksCurso.domain.model.FormaPagamento;

public interface FormaPagamentoRepository {

	List<FormaPagamento> listar();
	FormaPagamento buscar(Long id);
	FormaPagamento salvar(FormaPagamento formaPagamento);
	void remvove(FormaPagamento formaPamento);
}
