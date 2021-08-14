package com.ejs.algaworksCurso.domain.event;

import com.ejs.algaworksCurso.domain.model.Pedido;

public class PedidoCanceladoEvent {
	private Pedido pedido;

	public PedidoCanceladoEvent(Pedido pedido) {
		this.pedido = pedido;
	}

	/**
	 * @return the pedido
	 */
	public Pedido getPedido() {
		return pedido;
	}
	
	
	
}
