package com.ejs.algaworksCurso.domain.repository;

import java.util.List;

import com.ejs.algaworksCurso.domain.model.Cidade;

public interface CidadeRepository {

	List<Cidade> listar();
	Cidade buscar(Long id);
	Cidade salvar(Cidade cidade);
	void remove(Cidade cidade);
}
