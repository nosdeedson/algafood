package com.ejs.algaworksCurso.domain.repository;

import java.util.List;

import com.ejs.algaworksCurso.domain.model.Permissao;

public interface PermissaoRepository {

	List<Permissao> listar();
	Permissao buscar(Long id);
	Permissao salvar(Permissao permissao);
	void remove(Permissao permissao);
	
}
