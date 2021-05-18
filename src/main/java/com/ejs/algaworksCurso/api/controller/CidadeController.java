package com.ejs.algaworksCurso.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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
import com.ejs.algaworksCurso.domain.model.Cidade;
import com.ejs.algaworksCurso.domain.services.CidadeService;

@RestController
@RequestMapping("cidades")
public class CidadeController {

	@Autowired
	private CidadeService cidadeService;
	
	@PutMapping("{id}")
	public ResponseEntity<?> atualizar(@PathVariable Long id, 
			@RequestBody Cidade cidade){
		try {
			cidade = this.cidadeService.atualizar(cidade, id);
			return ResponseEntity.status(HttpStatus.OK).body(cidade);
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@GetMapping("{id}")
	public ResponseEntity<?> buscar(@PathVariable Long id){
		try {
			Cidade cidade = this.cidadeService.buscar(id);
			return ResponseEntity.ok(cidade);
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@GetMapping()
	public ResponseEntity<?> listar(){
		return ResponseEntity.ok(this.cidadeService.listar());
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<?> remover(@PathVariable Long id){
		try {
			try {
				this.cidadeService.remover(id);						
			} catch (DataIntegrityViolationException e) {
				throw new EntidadeEmUsoException(
						String.format("Cidade de código %d não pode ser removida, pois está em uso.", id));
			}
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Deletado com sucesso.");
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@PostMapping
	public ResponseEntity<?> salvar( @RequestBody Cidade cidade){
		try {
			cidade = this.cidadeService.salvar(cidade);
			return ResponseEntity.status(HttpStatus.CREATED).body(cidade);
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
}
