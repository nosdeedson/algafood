package com.ejs.algaworksCurso.api.v1.openApi.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;

import com.ejs.algaworksCurso.api.v1.model.in.pedido.PedidoIn;
import com.ejs.algaworksCurso.api.v1.model.out.formaPagamento.FormaPagamentoOut;
import com.ejs.algaworksCurso.api.v1.model.out.pedido.PedidoOut;
import com.ejs.algaworksCurso.api.v1.model.out.pedido.PedidoResumidoDTO;
import com.ejs.algaworksCurso.domain.model.filter.PedidoFilter;

public interface PedidoControllerOpenApi {

	ResponseEntity<Void> cancelarPedido(String codigoPedido);

	ResponseEntity<?> confirmarPedido(String codigoPedido);

	ResponseEntity<PedidoOut> buscar(String codigoPedido);
	
	ResponseEntity<FormaPagamentoOut> buscarFormaPagamento(String codigoPedido);

	ResponseEntity<?> entregarPedido(String codigoPedido);

	ResponseEntity<PagedModel<PedidoResumidoDTO>> listar(Pageable pageable, PedidoFilter filtro);

	ResponseEntity<PedidoOut> salvar(PedidoIn pedidoIn);

}