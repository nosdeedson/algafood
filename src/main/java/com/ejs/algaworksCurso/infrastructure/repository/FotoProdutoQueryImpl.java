package com.ejs.algaworksCurso.infrastructure.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ejs.algaworksCurso.domain.model.FotoProduto;
import com.ejs.algaworksCurso.domain.repository.ProdutoRepositoryQuery;

@Repository
public class FotoProdutoQueryImpl implements ProdutoRepositoryQuery {

	@PersistenceContext
	EntityManager manager;
	
	@Transactional
	@Override
	public FotoProduto save(FotoProduto fotoProduto) {
		fotoProduto = manager.merge(fotoProduto);
		return fotoProduto;
	}

	@Transactional
	@Override
	public void delete(FotoProduto fotoProduto) {
		manager.remove(fotoProduto);
	}

}
