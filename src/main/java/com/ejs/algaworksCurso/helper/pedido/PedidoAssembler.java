package com.ejs.algaworksCurso.helper.pedido;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ejs.algaworksCurso.api.model.in.pedido.PedidoIn;
import com.ejs.algaworksCurso.domain.model.Pedido;

@Component
public class PedidoAssembler {
	
	@Autowired
	private ModelMapper mapper;
	
	public Pedido pedidoInToPedido(PedidoIn pedidoIn) {
		return this.mapper.map(pedidoIn, Pedido.class);
	}


}
