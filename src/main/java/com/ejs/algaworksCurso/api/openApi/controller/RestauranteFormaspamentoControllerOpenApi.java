package com.ejs.algaworksCurso.api.openApi.controller;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import com.ejs.algaworksCurso.api.model.out.formaPagamento.FormaPagamentoOut;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "Restaurante Formas Pagamento")
public interface RestauranteFormaspamentoControllerOpenApi {

	@ApiOperation(value = "Associa restaurante a forma de pagamento")
	ResponseEntity<Void> associarFormaPagamento(Long restauranteId, Long formaPagamentoId);
	
	ResponseEntity<FormaPagamentoOut> buscar(@PathVariable Long restauranteId, @PathVariable Long formaPagamentoId);
	
	@ApiOperation(value = "Desassocia restaurante a forma de pagamento")
	ResponseEntity<Void> desassociarFormaPagamento(Long restauranteId, Long formaPagamentoId);

	ResponseEntity<CollectionModel<FormaPagamentoOut>> listar(Long restauranteId);
}