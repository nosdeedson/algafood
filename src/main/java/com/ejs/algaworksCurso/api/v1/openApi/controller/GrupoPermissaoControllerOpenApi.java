package com.ejs.algaworksCurso.api.v1.openApi.controller;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

import com.ejs.algaworksCurso.api.v1.model.out.permissao.PermissaoOut;

public interface GrupoPermissaoControllerOpenApi {

	ResponseEntity<Void> associar(Long grupoId, Long permissaoId);

	ResponseEntity<Void> desassociar(Long grupoId, Long permissaoId);

	ResponseEntity<CollectionModel<PermissaoOut>> listar(Long grupoId);
	
	ResponseEntity<CollectionModel<PermissaoOut>> listarPermissoesNaoVinculadaGrupo( Long grupoId);

}