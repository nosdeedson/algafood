package com.ejs.algaworksCurso.domain.exception;

public class PermissaoNaoEncontradaException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = 1L;

	public PermissaoNaoEncontradaException(String message, Throwable cause) {
		super(message, cause);
	}

	public PermissaoNaoEncontradaException(String message) {
		super(message);
	}
	
	public PermissaoNaoEncontradaException(Long permissaoId) {
		this(String.format("Permissão de código %d não existe.", permissaoId));
	}
	

}
