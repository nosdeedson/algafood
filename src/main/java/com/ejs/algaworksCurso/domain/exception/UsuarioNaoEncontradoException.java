package com.ejs.algaworksCurso.domain.exception;

public class UsuarioNaoEncontradoException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = 1L;

	public UsuarioNaoEncontradoException(String message, Throwable cause) {
		super(message, cause);
	}

	public UsuarioNaoEncontradoException(String message) {
		super(message);
	}
	
	public UsuarioNaoEncontradoException(Long usuarioId) {
		this(String.format("Usuário de código %d não existe.", usuarioId));
	}
	

}
