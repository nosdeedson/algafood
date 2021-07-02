package com.ejs.algaworksCurso.domain.exception;

public class EntidadeEmUsoException extends NegocioException{

	private static final long serialVersionUID = 1L;

	public EntidadeEmUsoException(String message, Throwable cause) {
		super(message, cause);
	}

	public EntidadeEmUsoException(String message) {
		super(message);
	}
}
