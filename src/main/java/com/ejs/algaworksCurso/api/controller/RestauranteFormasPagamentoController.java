package com.ejs.algaworksCurso.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ejs.algaworksCurso.api.model.dto.out.formaPagamento.FormaPagamentoOut;
import com.ejs.algaworksCurso.domain.services.RestauranteFormasPagamentoService;

@RestController
@RequestMapping("restaurantes/{restauranteId}/formas-pagamento")
public class RestauranteFormasPagamentoController {
	
	@Autowired
	private RestauranteFormasPagamentoService restauranteFormasPagamento;
	
	@GetMapping
	public ResponseEntity<?> listar(@PathVariable Long restauranteId){
		List<FormaPagamentoOut> formasPagamento = this.restauranteFormasPagamento.listar(restauranteId);
		return ResponseEntity.ok(formasPagamento);		
	}
	
	@PutMapping("{formaPagamentoId}")
	public ResponseEntity<?> associarFormaPagamento(@PathVariable Long restauranteId,
			@PathVariable Long formaPagamentoId){
		this.restauranteFormasPagamento.associarFormaPagamento(restauranteId, formaPagamentoId);
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping("{formaPagamentoId}")
	public ResponseEntity<?> desassociarFormaPagamento(@PathVariable Long restauranteId,
			@PathVariable Long formaPagamentoId){
		this.restauranteFormasPagamento.desassociarFormaPagamento(restauranteId, formaPagamentoId);
		return ResponseEntity.noContent().build();
	}

}
