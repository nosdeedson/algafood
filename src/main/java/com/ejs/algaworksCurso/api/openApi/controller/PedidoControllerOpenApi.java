package com.ejs.algaworksCurso.api.openApi.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import com.ejs.algaworksCurso.api.model.StringUriResposta;
import com.ejs.algaworksCurso.api.model.in.pedido.PedidoIn;
import com.ejs.algaworksCurso.domain.model.filter.PedidoFilter;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Api(tags = "Pedidos")
public interface PedidoControllerOpenApi {

	@ApiOperation(value = "Cancela um pedido")
	ResponseEntity<?> cancelarPedido(String codigoPedido);

	@ApiOperation(value = "Confirma um pedido", notes = "Muda o status do pedido para 'CONFIRMADO'")
	ResponseEntity<?> confirmarPedido(String codigoPedido);

	@ApiImplicitParams({
		@ApiImplicitParam(name = "campos", value = "Atributos a serem devolvidos na resposta.",
				paramType = "query", type = "String")
	})
	ResponseEntity<?> buscar(String codigoPedido);

	@ApiOperation(value = "Entregar pedido", notes = "Muda o status do pedido para 'ENTREGUE'")
	ResponseEntity<?> entregarPedido(String codigoPedido);

	@ApiImplicitParams({
		@ApiImplicitParam(name = "campos", value = "Atributos a serem devolvidos na resposta.",
				paramType = "query", type = "String")
	})
	ResponseEntity<?> listar(Pageable pageable, PedidoFilter filtro);

	ResponseEntity<StringUriResposta> salvar(PedidoIn pedidoIn);

}