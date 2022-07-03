package com.ejs.algaworksCurso.api.v1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ejs.algaworksCurso.api.v1.model.in.permissao.PermissaoIn;
import com.ejs.algaworksCurso.api.v1.model.out.permissao.PermissaoOut;
import com.ejs.algaworksCurso.api.v1.openApi.controller.PermissaoControllerOpenApi;
import com.ejs.algaworksCurso.core.security.CheckSecurity;
import com.ejs.algaworksCurso.domain.exception.EntidadeEmUsoException;
import com.ejs.algaworksCurso.domain.services.PermissaoService;

@RestController
@RequestMapping("permissoes")
public class PermissaoController implements PermissaoControllerOpenApi {
	
	@Autowired
	PermissaoService permissaoService;

	@CheckSecurity.GruposPermissoesUsuarios.PodeEditar
	@Override
	@PutMapping("{permissaoId}")
	public ResponseEntity<PermissaoOut> atualizar(@PathVariable("permissaoId") Long permissaoId, 
			@RequestBody() PermissaoIn in) {
		PermissaoOut out = this.permissaoService.atualizar(permissaoId, in);
		return ResponseEntity.ok(out);
	}
	
	@CheckSecurity.GruposPermissoesUsuarios.PodeConsultar
	@Override
	@GetMapping
	@ResponseStatus(code = HttpStatus.OK)
	public CollectionModel<PermissaoOut> listar() {
		CollectionModel<PermissaoOut> permissoes = this.permissaoService.listar();
		return permissoes;
	}

	@CheckSecurity.GruposPermissoesUsuarios.PodeEditar
	@Override
	@DeleteMapping("{permissaoId}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void remover(@PathVariable("permissaoId") Long permissaoId) {
		try {
			this.permissaoService.remover(permissaoId);
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(String.format(
							"Permissão de id: %d não pode ser removida, pois está vinculada a grupo ", permissaoId));
		}
	}

	@CheckSecurity.GruposPermissoesUsuarios.PodeEditar
	@Override
	@PostMapping
	public ResponseEntity<PermissaoOut> salvar(@RequestBody PermissaoIn in) {
		PermissaoOut out = this.permissaoService.salvar(in);
		return ResponseEntity.ok(out);
	}

}
