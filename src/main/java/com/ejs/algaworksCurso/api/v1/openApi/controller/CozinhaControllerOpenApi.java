package com.ejs.algaworksCurso.api.v1.openApi.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;

import com.ejs.algaworksCurso.api.v1.model.in.cozinha.CozinhaIn;
import com.ejs.algaworksCurso.api.v1.model.out.cozinha.CozinhaOut;

@Tag(name = "Cozinhas")
public interface CozinhaControllerOpenApi {

	@Operation(summary = "Atualiza cozinha", description = "Atualiza cozinha de acordo com o Id informado.",
			responses = {
					@ApiResponse(responseCode = "200"),
                    @ApiResponse(responseCode = "400", description = "Id da cozinha inválido",
                            content = @Content(schema = @Schema(ref = "Problem"))),
                    @ApiResponse(responseCode = "404", description = "Não existe o serviço solicitado",
                    		content = @Content(schema = @Schema(ref = "Problem"))),
                    @ApiResponse(responseCode = "409", description = "Existe divergência na requisição",
                    		content = @Content(schema = @Schema(ref = "Problem"))),
					@ApiResponse(responseCode = "500", description = "erro interno servidor",
							content = @Content(schema = @Schema(ref = "Problem")))
	})
	ResponseEntity<CozinhaOut> atualizar(CozinhaIn cozinhaIn, Long cozinhaId);

	@Operation(summary = "Busca cozinha", description = "Busca uma cozinha de acordo com o Id",
			responses = {
					@ApiResponse(responseCode = "200"),
                    @ApiResponse(responseCode = "400", description = "Id da ciade inválido",
                            content = @Content(schema = @Schema(ref = "Problem"))),
                    @ApiResponse(responseCode = "404", description = "Não existe o serviço solicitado",
                    		content = @Content(schema = @Schema(ref = "Problem"))),
					@ApiResponse(responseCode = "500", description = "erro interno servidor",
							content = @Content(schema = @Schema(ref = "Problem")))
			})
	ResponseEntity<CozinhaOut> buscar(Long id);

	@Operation(summary = "Busca cozinha", description = "Busca a primeira cozinha cadastrada", 
			responses = {
					@ApiResponse(responseCode = "200"),
					@ApiResponse(responseCode = "500", description = "erro interno servidor",
							content = @Content(schema = @Schema(ref = "Problem")))
			})
	ResponseEntity<CozinhaOut> buscarPrimeira();

	@Operation(summary = "Lista todas", description = "Lista todas as cozinhas",
			responses = {
					@ApiResponse(responseCode = "200"),
					@ApiResponse(responseCode = "500", description = "erro interno servidor",
							content = @Content(schema = @Schema(ref = "Problem")))
			})
	PagedModel<CozinhaOut> listar(Pageable pageable);

	@Operation(summary = "Remove cozinha", description = "Remove cozinha de acordo com o id informado",
			responses = {
					@ApiResponse(responseCode = "204"),
                    @ApiResponse(responseCode = "400", description = "Id da ciade inválido",
                            content = @Content(schema = @Schema(ref = "Problem"))),
                    @ApiResponse(responseCode = "404", description = "Não existe o serviço solicitado",
                    		content = @Content(schema = @Schema(ref = "Problem"))),
                    @ApiResponse(responseCode = "409", description = "Existe divergência na requisição",
                    		content = @Content(schema = @Schema(ref = "Problem"))),
					@ApiResponse(responseCode = "500", description = "erro interno servidor",
							content = @Content(schema = @Schema(ref = "Problem")))
			})
	void remover(Long cozinhaId);

	@Operation(summary = "Salva cozinha",
			responses = {
					@ApiResponse(responseCode = "201"),
                    @ApiResponse(responseCode = "400", description = "Id da ciade inválido",
                            content = @Content(schema = @Schema(ref = "Problem"))),
					@ApiResponse(responseCode = "500", description = "erro interno servidor",
							content = @Content(schema = @Schema(ref = "Problem")))
			})
	ResponseEntity<CozinhaOut> salvar(CozinhaIn cozinhaIn);

}