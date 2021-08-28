package com.ejs.algaworksCurso.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ejs.algaworksCurso.api.model.out.formaPagamento.FormaPagamentoOut;
import com.ejs.algaworksCurso.api.openApi.controller.RestauranteFormaspamentoControllerOpenApi;
import com.ejs.algaworksCurso.domain.services.RestauranteFormasPagamentoService;

@RestController
@RequestMapping("restaurantes/{restauranteId}/formas-pagamento")
public class RestauranteFormasPagamentoController implements RestauranteFormaspamentoControllerOpenApi {
	
	@Autowired
	private RestauranteFormasPagamentoService restauranteFormasPagamento;
	
	@Override
	@GetMapping
	public ResponseEntity<List<FormaPagamentoOut>> listar(@PathVariable Long restauranteId){
		List<FormaPagamentoOut> formasPagamento = this.restauranteFormasPagamento.listar(restauranteId);
		return ResponseEntity.ok(formasPagamento);		
	}
	
	@Override
	@PutMapping("{formaPagamentoId}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void associarFormaPagamento(@PathVariable Long restauranteId,
			@PathVariable Long formaPagamentoId){
		this.restauranteFormasPagamento.associarFormaPagamento(restauranteId, formaPagamentoId);
	}
	
	@Override
	@DeleteMapping("{formaPagamentoId}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void desassociarFormaPagamento(@PathVariable Long restauranteId,
			@PathVariable Long formaPagamentoId){
		this.restauranteFormasPagamento.desassociarFormaPagamento(restauranteId, formaPagamentoId);
	}

}
