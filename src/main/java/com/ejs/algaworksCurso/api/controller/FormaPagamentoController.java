package com.ejs.algaworksCurso.api.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.ejs.algaworksCurso.api.model.dto.in.FormaPagamentoIn;
import com.ejs.algaworksCurso.api.model.dto.out.FormaPagamentoOut;
import com.ejs.algaworksCurso.domain.services.FormaPagamentoService;

@RestController
@RequestMapping("forma-pagamentos")
public class FormaPagamentoController {

	@Autowired
	private FormaPagamentoService formaPagamentoService;
	
	@PutMapping("{formaPagamentoId}")
	public ResponseEntity<?> autalizar(@RequestBody @Valid FormaPagamentoIn formaPagamentoIn,
			@PathVariable(name = "formaPagamentoId") Long formaPagamentoId ){
		FormaPagamentoOut formaPagamentoOut = this.formaPagamentoService.atualizar(formaPagamentoIn, formaPagamentoId);
		return ResponseEntity.ok(formaPagamentoOut);
	}
	
	@GetMapping("{formaPagamentoId}")
	public ResponseEntity<?> buscar(@PathVariable Long formaPagamentoId) {
		FormaPagamentoOut out = formaPagamentoService.buscar(formaPagamentoId);
		return ResponseEntity.ok(out);
	}
	
	@GetMapping
	public ResponseEntity<?> listar(){
		List<FormaPagamentoOut> formasPagamentos = this.formaPagamentoService.listar();
		return ResponseEntity.ok(formasPagamentos);
	}
	
	@DeleteMapping("{formaPagamentoId}")
	public ResponseEntity<?> remover(@PathVariable Long formaPagamentoId){
		this.formaPagamentoService.remover(formaPagamentoId);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
	@PostMapping
	public ResponseEntity<?> salvar(@RequestBody @Valid FormaPagamentoIn formaPagamentoIn){
		FormaPagamentoOut formaPagamentoOut = this.formaPagamentoService.salvar(formaPagamentoIn);
		URI uri = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{formaPagamentoId}")
				.buildAndExpand(formaPagamentoOut.getId())
				.toUri();
		return ResponseEntity.status(HttpStatus.CREATED).body(uri);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
