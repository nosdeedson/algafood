package com.ejs.algaworksCurso.domain.exception;

public class ProdutoNaoEncontradoException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = 1L;

	public ProdutoNaoEncontradoException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public ProdutoNaoEncontradoException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
	
	public ProdutoNaoEncontradoException(Long produtoId, Long restauranteId) {
		this(String.format("Restaurante de código %d não tem produto de códiog %d", restauranteId, produtoId));
	}

}
