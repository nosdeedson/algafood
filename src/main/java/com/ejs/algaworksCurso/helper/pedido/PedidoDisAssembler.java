package com.ejs.algaworksCurso.helper.pedido;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ejs.algaworksCurso.api.model.dto.out.pedido.PedidoOut;
import com.ejs.algaworksCurso.api.model.dto.out.pedido.PedidoResumidoDTO;
import com.ejs.algaworksCurso.domain.model.Pedido;

@Component
public class PedidoDisAssembler {

	@Autowired
	private ModelMapper mapper;
	
	public PedidoOut pedidoToPedidoOut(Pedido pedido) {
		return this.mapper.map(pedido, PedidoOut.class);
	}
	
	public PedidoResumidoDTO pedidoToPedidoResumidoDTO(Pedido pedido) {
		return this.mapper.map(pedido, PedidoResumidoDTO.class);
	}
}
