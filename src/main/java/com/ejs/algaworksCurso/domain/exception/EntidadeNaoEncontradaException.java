package com.ejs.algaworksCurso.domain.exception;

public class EntidadeNaoEncontradaException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public EntidadeNaoEncontradaException(String message, Throwable cause) {
		super(message, cause);
	}

	public EntidadeNaoEncontradaException(String message) {
		super(message);
	}
	
	

}
