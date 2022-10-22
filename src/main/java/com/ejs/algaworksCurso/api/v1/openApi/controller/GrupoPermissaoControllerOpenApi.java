package com.ejs.algaworksCurso.api.v1.openApi.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

import com.ejs.algaworksCurso.api.v1.model.out.permissao.PermissaoOut;

@Tag(name = "Grupos/Permissões")
@SecurityRequirement(name = "bearer-token")
public interface GrupoPermissaoControllerOpenApi {

	@Operation(summary = "Associa Grupo a Permissão", 
			responses = {
					@ApiResponse(responseCode = "204"),
                    @ApiResponse(responseCode = "400", description = "Id inválido",
                            content = @Content(schema = @Schema(ref = "Problem"))),
                    @ApiResponse(responseCode = "404", description = "Não existe o serviço solicitado",
                    		content = @Content(schema = @Schema(ref = "Problem"))),
                    @ApiResponse(responseCode = "409", description = "Existe divergência na requisição",
                    		content = @Content(schema = @Schema(ref = "Problem"))),
					@ApiResponse(responseCode = "500", description = "erro interno servidor",
							content = @Content(schema = @Schema(ref = "Problem")))
			})
	ResponseEntity<Void> associar(Long grupoId, Long permissaoId);

	@Operation(summary = "Desassocia Grupo de permissão", 
			responses = {
					@ApiResponse(responseCode = "204"),
                    @ApiResponse(responseCode = "400", description = "Id inválido",
                            content = @Content(schema = @Schema(ref = "Problem"))),
                    @ApiResponse(responseCode = "404", description = "Não existe o serviço solicitado",
                    		content = @Content(schema = @Schema(ref = "Problem"))),
                    @ApiResponse(responseCode = "409", description = "Existe divergência na requisição",
                    		content = @Content(schema = @Schema(ref = "Problem"))),
					@ApiResponse(responseCode = "500", description = "erro interno servidor",
							content = @Content(schema = @Schema(ref = "Problem")))
			})
	ResponseEntity<Void> desassociar(Long grupoId, Long permissaoId);

	@Operation(summary = "Lista permissões", description = "Lista permissões de acordo com o Id", 
			responses = {
					@ApiResponse(responseCode = "200"),
					@ApiResponse(responseCode = "500", description = "erro interno servidor",
							content = @Content(schema = @Schema(ref = "Problem")))
			})
	ResponseEntity<CollectionModel<PermissaoOut>> listar(Long grupoId);
	
	@Operation(summary = "Lista Permissões", description = "Lista permissões não vinculadas ao id do grupo", 
			responses = {
					@ApiResponse(responseCode = "200"),
                    @ApiResponse(responseCode = "400", description = "Id inválido",
                            content = @Content(schema = @Schema(ref = "Problem"))),
                    @ApiResponse(responseCode = "404", description = "Não existe o serviço solicitado",
                    		content = @Content(schema = @Schema(ref = "Problem"))),
					@ApiResponse(responseCode = "500", description = "erro interno servidor",
							content = @Content(schema = @Schema(ref = "Problem")))
			})
	ResponseEntity<CollectionModel<PermissaoOut>> listarPermissoesNaoVinculadaGrupo( Long grupoId);

}