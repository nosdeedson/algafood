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

import com.ejs.algaworksCurso.api.model.dto.out.permissao.PermissaoOut;
import com.ejs.algaworksCurso.domain.services.GrupoPermissaoService;

@RestController
@RequestMapping("grupos/{grupoId}/permissoes")
public class GrupoPermissaoController {
	
	@Autowired
	private GrupoPermissaoService grupoPermissaoService;

	@PutMapping("{permissaoId}")
	public ResponseEntity<?> associar(@PathVariable Long grupoId, @PathVariable Long permissaoId){
		this.grupoPermissaoService.associar(grupoId, permissaoId);
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping("{permissaoId}")
	public ResponseEntity<?> desassociar(@PathVariable Long grupoId, @PathVariable Long permissaoId){
		this.grupoPermissaoService.desassociar(grupoId, permissaoId);
		return ResponseEntity.noContent().build();
	}
	
	
	@GetMapping
	public ResponseEntity<?> listar(@PathVariable Long grupoId){
		List<PermissaoOut> permissoes = this.grupoPermissaoService.listar(grupoId);
		return ResponseEntity.ok(permissoes);
	}
	
}
