package com.ejs.algaworksCurso.infrastructure.armazenamentoFoto;

public class ArmazenamentoException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ArmazenamentoException(String message, Throwable cause) {
		super(message, cause);
	}

	public ArmazenamentoException(String message) {
		super(message);
	}

}
