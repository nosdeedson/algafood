package com.ejs.algaworksCurso.api.v1.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ejs.algaworksCurso.api.helper.ResourceUriHelper;
import com.ejs.algaworksCurso.api.v1.model.in.cozinha.CozinhaIn;
import com.ejs.algaworksCurso.api.v1.model.out.cozinha.CozinhaOut;
import com.ejs.algaworksCurso.api.v1.openApi.controller.CozinhaControllerOpenApi;
import com.ejs.algaworksCurso.core.security.CheckSecurity;
import com.ejs.algaworksCurso.domain.services.CozinhaService;

@RestController
@RequestMapping("cozinhas")
public class CozinhaController implements CozinhaControllerOpenApi {

	@Autowired
	private CozinhaService cozinhaService;

	@CheckSecurity.Cozinhas.PodeEditar
	@Override
	@PutMapping("{cozinhaId}")
	public ResponseEntity<CozinhaOut> atualizar(@RequestBody
			@Valid CozinhaIn cozinhaIn, @PathVariable("cozinhaId") Long cozinhaId) {
		CozinhaOut out = this.cozinhaService.atualizar(cozinhaIn, cozinhaId);
		
		ResourceUriHelper.addUriHeaderUpdate();
		
		return ResponseEntity.ok(out);
	}

	@CheckSecurity.Cozinhas.PodeConsultar
	@Override
	@GetMapping("{id}")
	public ResponseEntity<CozinhaOut> buscar(@PathVariable("id") Long id) {
		CozinhaOut cozinha = this.cozinhaService.buscar(id);
		return ResponseEntity.ok(cozinha);					
	}
	
	@CheckSecurity.Cozinhas.PodeConsultar
	@Override
	@GetMapping("buscar-primeira")
	public ResponseEntity<CozinhaOut> buscarPrimeira(){
		return ResponseEntity.ok(this.cozinhaService.buscarPrimeira());
	}

	@CheckSecurity.Cozinhas.PodeConsultar
	@Override
	@GetMapping
	public PagedModel<CozinhaOut> listar(@PageableDefault(size = 10) Pageable pageable) {
		System.out.println(SecurityContextHolder.getContext().getAuthentication().isAuthenticated());
		return this.cozinhaService.listar(pageable);
	}
	
	@CheckSecurity.Cozinhas.PodeEditar
	@Override
	@DeleteMapping("{cozinhaId}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long cozinhaId){
			this.cozinhaService.remover(cozinhaId);
	}

	@CheckSecurity.Cozinhas.PodeEditar
	@Override
	@PostMapping
	public ResponseEntity<CozinhaOut> salvar(@RequestBody @Valid CozinhaIn cozinhaIn) {
		CozinhaOut cozinha = this.cozinhaService.salvar(cozinhaIn);
		ResourceUriHelper.addUriHeaderSave(cozinha.getId());
		return ResponseEntity.status(HttpStatus.CREATED).body(cozinha);
	}
	
}
