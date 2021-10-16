package com.ejs.algaworksCurso.api.v1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ejs.algaworksCurso.api.v1.model.out.permissao.PermissaoOut;
import com.ejs.algaworksCurso.api.v1.openApi.controller.GrupoPermissaoControllerOpenApi;
import com.ejs.algaworksCurso.core.security.CheckSecurity;
import com.ejs.algaworksCurso.domain.services.GrupoPermissaoService;

@RestController
@RequestMapping("grupos/{grupoId}/permissoes")
public class GrupoPermissaoController implements GrupoPermissaoControllerOpenApi {
	
	@Autowired
	private GrupoPermissaoService grupoPermissaoService;

	@CheckSecurity.GruposPermissoesUsuarios.PodeEditar
	@Override
	@PutMapping("{permissaoId}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> associar(@PathVariable Long grupoId, @PathVariable Long permissaoId){
		this.grupoPermissaoService.associar(grupoId, permissaoId);
		return ResponseEntity.noContent().build();
	}
	
	@CheckSecurity.GruposPermissoesUsuarios.PodeEditar
	@Override
	@DeleteMapping("{permissaoId}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> desassociar(@PathVariable Long grupoId, @PathVariable Long permissaoId){
		this.grupoPermissaoService.desassociar(grupoId, permissaoId);
		return ResponseEntity.noContent().build();
	}
	
	@CheckSecurity.GruposPermissoesUsuarios.PodeConsultar
	@Override
	@GetMapping
	public ResponseEntity<CollectionModel<PermissaoOut>> listar(@PathVariable Long grupoId){
		CollectionModel<PermissaoOut> permissoes = this.grupoPermissaoService.listar(grupoId);
		permissoes.removeLinks();
		permissoes.getContent().stream().forEach(permissao ->{
			permissao.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(GrupoPermissaoController.class)
					.desassociar(grupoId, permissao.getId())).withRel("associar"));
			permissao.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(GrupoPermissaoController.class)
					.associar(grupoId, permissao.getId())).withRel("desassociar"));
		});
		permissoes.add(WebMvcLinkBuilder.linkTo(PermissaoController.class).withRel("permissoes"));
		return ResponseEntity.ok(permissoes);
	}
	
}
