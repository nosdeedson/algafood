package com.ejs.algaworksCurso.api.controller;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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

import com.ejs.algaworksCurso.api.model.in.cozinha.CozinhaIn;
import com.ejs.algaworksCurso.api.model.out.cozinha.CozinhaOut;
import com.ejs.algaworksCurso.domain.services.CozinhaService;

@RestController
@RequestMapping("cozinhas")
public class CozinhaController {

	@Autowired
	private CozinhaService cozinhaService;

	@PutMapping("{cozinhaId}")
	public ResponseEntity<?> atualizar(@RequestBody
			@Valid CozinhaIn cozinhaIn, @PathVariable("cozinhaId") Long cozinhaId) {
		this.cozinhaService.atualizar(cozinhaIn, cozinhaId);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().buildAndExpand().toUri();
		return ResponseEntity.ok(uri);
	}

	@GetMapping("{id}")
	public ResponseEntity<?> buscar(@PathVariable("id") Long id) {
		CozinhaOut cozinha = this.cozinhaService.buscar(id);
		return ResponseEntity.ok(cozinha);					
	}
	
	@GetMapping("buscar-primeira")
	public ResponseEntity<?> buscarPrimeira(){
		return ResponseEntity.ok(this.cozinhaService.buscarPrimeira());
	}

	@GetMapping
	public ResponseEntity<?> listar(@PageableDefault(size = 10) Pageable pageable) {
		return ResponseEntity.ok(this.cozinhaService.listar(pageable));
	}
	
	@DeleteMapping("{cozinhaId}")
	public ResponseEntity<?> remover(@PathVariable Long cozinhaId){
			this.cozinhaService.remover(cozinhaId);
			return ResponseEntity.noContent().build();
	}

	@PostMapping
	public ResponseEntity<?> salvar(@RequestBody @Valid CozinhaIn cozinhaIn) {
		CozinhaOut cozinha = this.cozinhaService.salvar(cozinhaIn);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(cozinha.getId())
				.toUri();
		return ResponseEntity.created(uri).body(cozinha);
	}
	
}
