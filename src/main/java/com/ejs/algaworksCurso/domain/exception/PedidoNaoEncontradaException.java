package com.ejs.algaworksCurso.domain.exception;

public class PedidoNaoEncontradaException extends EntidadeNaoEncontradaException{

	private static final long serialVersionUID = 1L;

	public PedidoNaoEncontradaException(String message, Throwable cause) {
		super(message, cause);
	}

	public PedidoNaoEncontradaException(String codigoPedido) {
		super(String.format("Pedido de código %d não existe.", codigoPedido));
	}
	
	

}
