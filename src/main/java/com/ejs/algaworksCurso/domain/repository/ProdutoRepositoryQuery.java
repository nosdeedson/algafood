package com.ejs.algaworksCurso.domain.repository;

import com.ejs.algaworksCurso.domain.model.FotoProduto;

public interface ProdutoRepositoryQuery {

	FotoProduto save(FotoProduto fotoProduto);
	
	void delete(FotoProduto fotoProduto);
}
