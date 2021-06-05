package com.ejs.algaworksCurso.domain.exception;

public class CidadeNaoEncontradaException extends EntidadeNaoEncontradaException{

	private static final long serialVersionUID = 1L;

	public CidadeNaoEncontradaException(String message, Throwable cause) {
		super(message, cause);
	}

	public CidadeNaoEncontradaException(String message) {
		super(message);
	}
	
	public CidadeNaoEncontradaException(Long cidadeId) {
		this(String.format("Cidade de código %d não esixte.", cidadeId));
	}
	
	

}
