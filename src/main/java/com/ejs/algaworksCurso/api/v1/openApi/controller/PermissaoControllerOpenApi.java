package com.ejs.algaworksCurso.api.v1.openApi.controller;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

import com.ejs.algaworksCurso.api.v1.model.in.permissao.PermissaoIn;
import com.ejs.algaworksCurso.api.v1.model.out.permissao.PermissaoOut;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Permissões")
@SecurityRequirement(name = "bearer-token")
public interface PermissaoControllerOpenApi {

	@Operation(summary = "Atualiza Permissão", 
			responses = {
					@ApiResponse(responseCode = "200"),
                    @ApiResponse(responseCode = "400", description = "Id inválido",
                            content = @Content(schema = @Schema(ref = "Problem"))),
                    @ApiResponse(responseCode = "404", description = "Não existe o serviço solicitado",
                    		content = @Content(schema = @Schema(ref = "Problem"))),
                    @ApiResponse(responseCode = "409", description = "Existe divergência na requisição",
                    		content = @Content(schema = @Schema(ref = "Problem"))),
					@ApiResponse(responseCode = "500", description = "erro interno servidor",
							content = @Content(schema = @Schema(ref = "Problem")))
			})
	public ResponseEntity<PermissaoOut> atualizar(Long permissaoId, PermissaoIn in);
	
	@Operation(summary = "Listar permissões", 
			responses = {
					@ApiResponse(responseCode = "200"),
					@ApiResponse(responseCode = "500", description = "erro interno servidor",
							content = @Content(schema = @Schema(ref = "Problem")))
			})
	public CollectionModel<PermissaoOut> listar();
	
	@Operation(summary = "Salvar Permissão", description = "Remove grupo de acordo com o Id", 
			responses = {
					@ApiResponse(responseCode = "201"),
					@ApiResponse(responseCode = "500", description = "erro interno servidor",
							content = @Content(schema = @Schema(ref = "Problem")))
			})
	public ResponseEntity<PermissaoOut> salvar(PermissaoIn in);
}
