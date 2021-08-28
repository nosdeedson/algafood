package com.ejs.algaworksCurso.api.openApi.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import com.ejs.algaworksCurso.api.model.in.pedido.PedidoIn;
import com.ejs.algaworksCurso.domain.model.filter.PedidoFilter;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;

@Api(tags = "Pedidos")
public interface PedidoControllerOpenApi {

	ResponseEntity<?> cancelarPedido(String codigoPedido);

	ResponseEntity<?> confirmarPedido(String codigoPedido);

	@ApiImplicitParams({
		@ApiImplicitParam(name = "campos", value = "Atributos a serem devolvidos na resposta.",
				paramType = "query", type = "String")
	})
	ResponseEntity<?> buscar(String codigoPedido);

	ResponseEntity<?> entregarPedido(String codigoPedido);

	@ApiImplicitParams({
		@ApiImplicitParam(name = "campos", value = "Atributos a serem devolvidos na resposta.",
				paramType = "query", type = "String")
	})
	ResponseEntity<?> listar(Pageable pageable, PedidoFilter filtro);

	ResponseEntity<?> salvar(PedidoIn pedidoIn);

}