package com.ejs.algaworksCurso.domain.exception;

public class RestauranteNaoEncontradoException extends EntidadeNaoEncontradaException{

	private static final long serialVersionUID = 1L;

	public RestauranteNaoEncontradoException(String message, Throwable cause) {
		super(message, cause);
	}

	public RestauranteNaoEncontradoException(String message) {
		super(message);
	}
	
	public RestauranteNaoEncontradoException(Long restauranteId) {
		this(String.format("Retaurante de código %d não esixte.", restauranteId));
	}
	
	

}
