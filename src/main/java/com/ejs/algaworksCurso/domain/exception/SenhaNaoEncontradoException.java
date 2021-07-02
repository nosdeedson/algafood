package com.ejs.algaworksCurso.domain.exception;

public class SenhaNaoEncontradoException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = 1L;

	public SenhaNaoEncontradoException(String message, Throwable cause) {
		super(message, cause);
	}

	public SenhaNaoEncontradoException(String message) {
		super(message);
	}
	

}
