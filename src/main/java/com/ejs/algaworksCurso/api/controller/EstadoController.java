package com.ejs.algaworksCurso.api.controller;

import java.util.List;

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

import com.ejs.algaworksCurso.domain.exception.EntidadeEmUsoException;
import com.ejs.algaworksCurso.domain.exception.EntidadeNaoEncontradaException;
import com.ejs.algaworksCurso.domain.model.Estado;
import com.ejs.algaworksCurso.domain.services.EstadoService;

@RestController
@RequestMapping("estados")
public class EstadoController {
	
	@Autowired
	private EstadoService estadoService;
	
	@PutMapping("{id}")
	public ResponseEntity<?> atualizar(@PathVariable Long id,
			@RequestBody Estado estado){
		try {
			estado = this.estadoService.atualizar(estado, id);
			return ResponseEntity.ok(estado);
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		} 
	}
	
	@GetMapping("{id}")
	public ResponseEntity<?> buscar(@PathVariable Long id){
		try {
			Estado estado = this.estadoService.buscar(id);
			return ResponseEntity.ok(estado);
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@GetMapping
	public ResponseEntity<?> listar(){
		List<Estado> estados = this.estadoService.listar();			
		return ResponseEntity.ok(estados);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<?> remover(@PathVariable Long id){
		try {
			this.estadoService.remover(id);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Deletado com sucesso!");
		} catch (EntidadeEmUsoException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@PostMapping
	public ResponseEntity<?> salvar(@RequestBody Estado estado){
		try {			
			estado = this.estadoService.salvar(estado);
			return ResponseEntity.status(HttpStatus.CREATED).body(estado);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("Falha ao salvar o Estado.");
		}
	}

}
