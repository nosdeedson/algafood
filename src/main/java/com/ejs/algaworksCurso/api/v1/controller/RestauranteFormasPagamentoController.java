package com.ejs.algaworksCurso.api.v1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ejs.algaworksCurso.api.v1.model.out.formaPagamento.FormaPagamentoOut;
import com.ejs.algaworksCurso.api.v1.openApi.controller.RestauranteFormaspamentoControllerOpenApi;
import com.ejs.algaworksCurso.domain.services.RestauranteFormasPagamentoService;

@RestController
@RequestMapping("restaurantes/{restauranteId}/formas-pagamento")
public class RestauranteFormasPagamentoController implements RestauranteFormaspamentoControllerOpenApi {
	
	@Autowired
	private RestauranteFormasPagamentoService restauranteFormasPagamentoService;
	
		
	@Override
	@PutMapping("{formaPagamentoId}")
	public ResponseEntity<Void> associarFormaPagamento(@PathVariable Long restauranteId,
			@PathVariable Long formaPagamentoId){
		this.restauranteFormasPagamentoService.associarFormaPagamento(restauranteId, formaPagamentoId);
		return ResponseEntity.noContent().build();
	}
	
	@Override
	@GetMapping("{formaPagamentoId}")
	public ResponseEntity<FormaPagamentoOut> buscar(@PathVariable Long restauranteId,
			@PathVariable Long formaPagamentoId){
		FormaPagamentoOut out = this.restauranteFormasPagamentoService.buscar(restauranteId, formaPagamentoId);
		return ResponseEntity.ok(out);
	}
	
	@Override
	@DeleteMapping("{formaPagamentoId}")
	public ResponseEntity<Void> desassociarFormaPagamento(@PathVariable Long restauranteId,
			@PathVariable Long formaPagamentoId){
		this.restauranteFormasPagamentoService.desassociarFormaPagamento(restauranteId, formaPagamentoId);
		return ResponseEntity.noContent().build();
	}
	
	@Override
	@GetMapping
	public ResponseEntity<CollectionModel<FormaPagamentoOut>> listar(@PathVariable Long restauranteId){
		CollectionModel<FormaPagamentoOut> formasPagamento = this.restauranteFormasPagamentoService.listar(restauranteId);
		return ResponseEntity.ok(formasPagamento);		
	}

}
