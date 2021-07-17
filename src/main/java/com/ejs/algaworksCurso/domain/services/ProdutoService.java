package com.ejs.algaworksCurso.domain.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ejs.algaworksCurso.domain.exception.ProdutoNaoEncontradoException;
import com.ejs.algaworksCurso.domain.model.Produto;
import com.ejs.algaworksCurso.domain.repository.ProdutoRepository;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;
	
	public Produto buscarOuFalhar(Long produtoId) {
		return this.produtoRepository.findById(produtoId)
				.orElseThrow( () -> new ProdutoNaoEncontradoException(
						String.format("Produto de código %d não existe.")));
	}
}
