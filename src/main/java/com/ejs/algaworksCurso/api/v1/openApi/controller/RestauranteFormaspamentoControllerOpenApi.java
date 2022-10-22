package com.ejs.algaworksCurso.api.v1.openApi.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

import com.ejs.algaworksCurso.api.v1.model.out.formaPagamento.FormaPagamentoOut;

@Tag(name = "Restaurantes/Formas Pagamento")
@SecurityRequirement(name = "bearer-token")
public interface RestauranteFormaspamentoControllerOpenApi {

	ResponseEntity<Void> associarFormaPagamento(Long restauranteId, Long formaPagamentoId);
	
	ResponseEntity<FormaPagamentoOut> buscar( Long restauranteId, Long formaPagamentoId);
	
	ResponseEntity<Void> desassociarFormaPagamento(Long restauranteId, Long formaPagamentoId);

	ResponseEntity<CollectionModel<FormaPagamentoOut>> listar(Long restauranteId);
	
	ResponseEntity<CollectionModel<FormaPagamentoOut>> listarFormasPagamentosNaoAssociadasRestaurante( Long restauranteId);
}