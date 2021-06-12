package com.ejs.algaworksCurso.api.controller;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.ejs.algaworksCurso.domain.model.Cozinha;
import com.ejs.algaworksCurso.domain.services.CozinhaService;

@RestController
@RequestMapping("cozinhas")
public class CozinhaController {

	@Autowired
	private CozinhaService cozinhaService;

	@PutMapping("{cozinhaId}")
	public ResponseEntity<?> atualizar(@RequestBody
			@Valid Cozinha cozinha, @PathVariable("cozinhaId") Long cozinhaId) {
		cozinha = this.cozinhaService.atualizar(cozinha, cozinhaId);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().buildAndExpand().toUri();
		return ResponseEntity.ok(uri);
	}

	@GetMapping("{id}")
	public ResponseEntity<?> buscar(@PathVariable("id") Long id) {
		Cozinha cozinha = this.cozinhaService.buscar(id);
		return ResponseEntity.ok(cozinha);					
	}
	
	@GetMapping("buscar-primeira")
	public ResponseEntity<?> buscarPrimeira(){
		return ResponseEntity.ok(this.cozinhaService.buscarPrimeira());
	}

	@GetMapping
	public ResponseEntity<?> listar() {
		return ResponseEntity.ok(this.cozinhaService.listar());
	}

	@PostMapping
	public ResponseEntity<?> salvar(@RequestBody @Valid Cozinha cozinha) {
		cozinha = this.cozinhaService.salvar(cozinha);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(cozinha.getId())
				.toUri();
		return ResponseEntity.created(uri).body(cozinha);
	}
	
	@DeleteMapping("{cozinhaId}")
	public ResponseEntity<?> remover(@PathVariable Long cozinhaId){
			this.cozinhaService.remover(cozinhaId);
			return ResponseEntity.noContent().build();
	}
	
}
