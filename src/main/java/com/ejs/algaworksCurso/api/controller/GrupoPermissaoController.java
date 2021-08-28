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

import com.ejs.algaworksCurso.api.model.out.permissao.PermissaoOut;
import com.ejs.algaworksCurso.api.openApi.controller.GrupoPermissaoControllerOpenApi;
import com.ejs.algaworksCurso.domain.services.GrupoPermissaoService;

@RestController
@RequestMapping("grupos/{grupoId}/permissoes")
public class GrupoPermissaoController implements GrupoPermissaoControllerOpenApi {
	
	@Autowired
	private GrupoPermissaoService grupoPermissaoService;

	@Override
	@PutMapping("{permissaoId}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void associar(@PathVariable Long grupoId, @PathVariable Long permissaoId){
		this.grupoPermissaoService.associar(grupoId, permissaoId);
	}
	
	@Override
	@DeleteMapping("{permissaoId}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void desassociar(@PathVariable Long grupoId, @PathVariable Long permissaoId){
		this.grupoPermissaoService.desassociar(grupoId, permissaoId);
	}
	
	
	@Override
	@GetMapping
	public ResponseEntity<List<PermissaoOut>> listar(@PathVariable Long grupoId){
		List<PermissaoOut> permissoes = this.grupoPermissaoService.listar(grupoId);
		return ResponseEntity.ok(permissoes);
	}
	
}
