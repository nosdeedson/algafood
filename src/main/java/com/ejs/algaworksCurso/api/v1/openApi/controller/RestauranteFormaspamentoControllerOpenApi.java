package com.ejs.algaworksCurso.api.v1.openApi.controller;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import com.ejs.algaworksCurso.api.v1.model.out.formaPagamento.FormaPagamentoOut;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(tags = "Restaurante Formas Pagamento")
public interface RestauranteFormaspamentoControllerOpenApi {

	@ApiOperation(value = "Associa restaurante a forma de pagamento")
	ResponseEntity<Void> associarFormaPagamento(Long restauranteId, Long formaPagamentoId);
	
	ResponseEntity<FormaPagamentoOut> buscar(@ApiParam Long restauranteId, @ApiParam Long formaPagamentoId);
	
	@ApiOperation(value = "Desassocia restaurante a forma de pagamento")
	ResponseEntity<Void> desassociarFormaPagamento(Long restauranteId, Long formaPagamentoId);

	ResponseEntity<CollectionModel<FormaPagamentoOut>> listar(Long restauranteId);
	
	@ApiOperation(value = "Lista formas de pagamentos não vinculadas ao restaurante")
	ResponseEntity<CollectionModel<FormaPagamentoOut>> listarFormasPagamentosNaoAssociadasRestaurante(@ApiParam Long restauranteId);
}