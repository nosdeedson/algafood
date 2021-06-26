package com.ejs.algaworksCurso.domain.exception;

public class FormaPagamentoNaoEncontradoException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = 1L;

	public FormaPagamentoNaoEncontradoException(String message, Throwable cause) {
		super(message, cause);
	}

	public FormaPagamentoNaoEncontradoException(String message) {
		super(message);
	}
	
	public FormaPagamentoNaoEncontradoException(Long formaPagamentoId) {
		this(String.format("Forma de pagamento de id %d não existe.", formaPagamentoId));
	}

	
}
