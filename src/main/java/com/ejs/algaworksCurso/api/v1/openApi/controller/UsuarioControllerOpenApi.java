package com.ejs.algaworksCurso.api.v1.openApi.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

import com.ejs.algaworksCurso.api.v1.model.in.senha.SenhaAtualizarIn;
import com.ejs.algaworksCurso.api.v1.model.in.usuario.UsuarioAtualizarIn;
import com.ejs.algaworksCurso.api.v1.model.in.usuario.UsuarioIn;
import com.ejs.algaworksCurso.api.v1.model.out.usuario.UsuarioOut;
@Tag(name = "Usuários")
@SecurityRequirement(name = "bearer-token")
public interface UsuarioControllerOpenApi {
	
	@Operation(summary = "Atualiza Usuário", 
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
	ResponseEntity<UsuarioOut> atualizar(Long usuarioId, UsuarioAtualizarIn usuarioIn);

	@Operation(summary = "Atualiza Senha", 
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
	void atualizarSenha(SenhaAtualizarIn senhaIn, Long usuarioId);

	@Operation(summary = "Busca Usuário",  
			responses = {
					@ApiResponse(responseCode = "200"),
                    @ApiResponse(responseCode = "400", description = "Id inválido",
                            content = @Content(schema = @Schema(ref = "Problem"))),
                    @ApiResponse(responseCode = "404", description = "Não existe o serviço solicitado",
                    		content = @Content(schema = @Schema(ref = "Problem"))),
					@ApiResponse(responseCode = "500", description = "erro interno servidor",
							content = @Content(schema = @Schema(ref = "Problem")))
			})
	ResponseEntity<UsuarioOut> buscar(Long usuarioId);

	@Operation(summary = "Lista Usuários",  
			responses = {
					@ApiResponse(responseCode = "200"),
					@ApiResponse(responseCode = "500", description = "erro interno servidor",
							content = @Content(schema = @Schema(ref = "Problem")))
			})
	ResponseEntity<CollectionModel<UsuarioOut>> listar();
	
	@Operation(summary = "Lista Usuários", description = "Lista usuários não vinculados a grupos", 
			responses = {
					@ApiResponse(responseCode = "200"),
					@ApiResponse(responseCode = "500", description = "erro interno servidor",
							content = @Content(schema = @Schema(ref = "Problem")))
			})
	ResponseEntity<CollectionModel<UsuarioOut>> listarUsuariosNãoVinculadosAoGrupo(Long grupoId);

	@Operation(summary = "Remove Usuário", 
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
	void remover(Long usuarioId);

	@Operation(summary = "Salva Usuário", 
			responses = {
					@ApiResponse(responseCode = "201"),
					@ApiResponse(responseCode = "500", description = "erro interno servidor",
							content = @Content(schema = @Schema(ref = "Problem")))
			})
	ResponseEntity<UsuarioOut> salvar(UsuarioIn usuarioIn);

}