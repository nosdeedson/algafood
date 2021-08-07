package com.ejs.algaworksCurso.domain.exception;

public class FotoProdutoNaoEncontradaException extends EntidadeNaoEncontradaException{

	private static final long serialVersionUID = 1L;

	public FotoProdutoNaoEncontradaException(String message, Throwable cause) {
		super(message, cause);
	}

	public FotoProdutoNaoEncontradaException(String message) {
		super(message);
	}
	
	public FotoProdutoNaoEncontradaException(Long restauranteId, Long produtoId) {
		this(String.format("Não encontramos a foto do produto de código %d do restaurante "
				+ " de código %d.", produtoId, restauranteId));
	}
	
	

}
