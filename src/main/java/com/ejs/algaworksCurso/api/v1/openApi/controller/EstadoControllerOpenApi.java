package com.ejs.algaworksCurso.api.v1.openApi.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

import com.ejs.algaworksCurso.api.v1.model.in.estado.EstadoIn;
import com.ejs.algaworksCurso.api.v1.model.out.estado.EstadoOut;
@Tag(name = "Estados")
@SecurityRequirement(name = "bearer-token")
public interface EstadoControllerOpenApi {

	@Operation(summary = "Atualiza estados", description = "Atualiza um estado de acordo com o Id",
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
	ResponseEntity<EstadoOut> atualizar(Long id, EstadoIn estadoIn);

	@Operation(summary = "Busca Estado", description = "Busca Estado de acordo com Id", 
			responses = {
					@ApiResponse(responseCode = "200"),
                    @ApiResponse(responseCode = "400", description = "Id inválido",
                            content = @Content(schema = @Schema(ref = "Problem"))),
                    @ApiResponse(responseCode = "404", description = "Não existe o serviço solicitado",
                    		content = @Content(schema = @Schema(ref = "Problem"))),
					@ApiResponse(responseCode = "500", description = "erro interno servidor",
							content = @Content(schema = @Schema(ref = "Problem")))
			})
	ResponseEntity<EstadoOut> buscar(Long id);

	@Operation(summary = "Lista Estados", description = "Lista todos estados", 
			responses = {
					@ApiResponse(responseCode = "200"),
					@ApiResponse(responseCode = "500", description = "erro interno servidor",
							content = @Content(schema = @Schema(ref = "Problem")))
			})
	CollectionModel<EstadoOut> listar();

	@Operation(summary = "Remove Estado", description = "Remove estado de acordo com Id",
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
	void remover(Long id);

	@Operation(summary = "Salva estado", 
			responses = {
					@ApiResponse(responseCode = "201"),
					@ApiResponse(responseCode = "500", description = "erro interno servidor",
							content = @Content(schema = @Schema(ref = "Problem")))
			})
	ResponseEntity<EstadoOut> salvar(EstadoIn estadoIn);

}