package com.ejs.algaworksCurso.domain.exception;

public class EstadoNaoEncontradoException extends EntidadeNaoEncontradaException{

	private static final long serialVersionUID = 1L;

	public EstadoNaoEncontradoException(String message, Throwable cause) {
		super(message, cause);
	}

	public EstadoNaoEncontradoException(String message) {
		super(message);
	}
	
	public EstadoNaoEncontradoException(Long estadoId) {
		this(String.format("Estado de código %d não esixte.", estadoId));
	}
	
	

}
