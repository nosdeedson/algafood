package com.ejs.algaworksCurso.api.openApi.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.ejs.algaworksCurso.api.model.out.formaPagamento.FormaPagamentoOut;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "Restaurante Formas Pagamento")
public interface RestauranteFormaspamentoControllerOpenApi {

	ResponseEntity<List<FormaPagamentoOut>> listar(Long restauranteId);

	@ApiOperation(value = "Associa restaurante a forma de pagamento")
	void associarFormaPagamento(Long restauranteId, Long formaPagamentoId);

	@ApiOperation(value = "Desassocia restaurante a forma de pagamento")
	void desassociarFormaPagamento(Long restauranteId, Long formaPagamentoId);

}