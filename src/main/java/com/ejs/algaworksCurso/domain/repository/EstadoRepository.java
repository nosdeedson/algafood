package com.ejs.algaworksCurso.domain.repository;

import java.util.List;

import com.ejs.algaworksCurso.domain.model.Estado;

public interface EstadoRepository {

	List<Estado> listar();
	Estado buscar(Long id);
	Estado salvar(Estado estado);
	void remove(Estado estado);
}
