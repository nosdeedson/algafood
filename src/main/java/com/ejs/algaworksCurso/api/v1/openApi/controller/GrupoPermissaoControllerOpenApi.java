package com.ejs.algaworksCurso.api.v1.openApi.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

import com.ejs.algaworksCurso.api.v1.model.out.permissao.PermissaoOut;

@Tag(name = "Grupos/Permissões")
@SecurityRequirement(name = "bearer-token")
public interface GrupoPermissaoControllerOpenApi {

	ResponseEntity<Void> associar(Long grupoId, Long permissaoId);

	ResponseEntity<Void> desassociar(Long grupoId, Long permissaoId);

	ResponseEntity<CollectionModel<PermissaoOut>> listar(Long grupoId);
	
	ResponseEntity<CollectionModel<PermissaoOut>> listarPermissoesNaoVinculadaGrupo( Long grupoId);

}