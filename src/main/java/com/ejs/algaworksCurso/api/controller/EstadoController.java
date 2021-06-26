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

import com.ejs.algaworksCurso.api.model.dto.in.EstadoIn;
import com.ejs.algaworksCurso.api.model.dto.out.EstadoOut;
import com.ejs.algaworksCurso.domain.model.Estado;
import com.ejs.algaworksCurso.domain.services.EstadoService;

@RestController
@RequestMapping("estados")
public class EstadoController {
	
	@Autowired
	private EstadoService estadoService;
	
	@PutMapping("{id}")
	public ResponseEntity<?> atualizar(@PathVariable Long id,
			@RequestBody @Valid EstadoIn estadoIn){
			this.estadoService.atualizar(estadoIn, id);
			URI uri = ServletUriComponentsBuilder.fromCurrentRequest().buildAndExpand().toUri();
			return ResponseEntity.ok(uri);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<?> buscar(@PathVariable Long id){
		EstadoOut estado = this.estadoService.buscar(id);
		return ResponseEntity.ok(estado);
	}
	
	@GetMapping
	public ResponseEntity<?> listar(){
		List<EstadoOut> estados = this.estadoService.listar();			
		return ResponseEntity.ok(estados);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<?> remover(@PathVariable Long id){
			this.estadoService.remover(id);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Deletado com sucesso!");
	}
	
	@PostMapping
	public ResponseEntity<?> salvar(@RequestBody @Valid EstadoIn estadoIn){
		Estado estado = this.estadoService.salvar(estadoIn);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(estado.getId())
				.toUri();
		return ResponseEntity.status(HttpStatus.CREATED).body(uri);
	}

}
