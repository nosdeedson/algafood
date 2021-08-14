package com.ejs.algaworksCurso.domain.event;

import com.ejs.algaworksCurso.domain.model.Pedido;

public class PedidoConfirmadoEvent {

	private Pedido pedido;
	
	public PedidoConfirmadoEvent(Pedido pedido) {
		this.pedido = pedido;
	}


	/**
	 * @return the pedido
	 */
	public Pedido getPedido() {
		return pedido;
	}
	
}
