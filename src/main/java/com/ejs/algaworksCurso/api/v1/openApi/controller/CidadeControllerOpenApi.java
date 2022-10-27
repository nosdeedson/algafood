package com.ejs.algaworksCurso.api.v1.openApi.controller;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

import com.ejs.algaworksCurso.api.v1.model.in.cidade.CidadeIn;
import com.ejs.algaworksCurso.api.v1.model.out.cidade.CidadeOut;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;


@SecurityRequirement(name = "bearer-key")
@Tag(name = "Cidades")
public interface CidadeControllerOpenApi {

	@Operation(summary = "Atualiza uma cidade",
			description = "Para atualizar uma cidade é necessário passar o id na url, e o um dto CidadeIn",
            responses = {
                    @ApiResponse(responseCode = "200"),
                    @ApiResponse(responseCode = "400", description = "Id da ciade inválido",
                            content = @Content(schema = @Schema(ref = "Problem"))),
					@ApiResponse(responseCode = "403", description = "Usuário não tem acesso ao recurso solicitado",
							content = @Content(schema = @Schema(ref = "Problem"))),
                    @ApiResponse(responseCode = "404", description = "Não existe o serviço solicitado",
                    		content = @Content(schema = @Schema(ref = "Problem"))),
                    @ApiResponse(responseCode = "409", description = "Existe divergência na requisição",
                    		content = @Content(schema = @Schema(ref = "Problem"))),
					@ApiResponse(responseCode = "500", description = "erro interno servidor",
							content = @Content(schema = @Schema(ref = "Problem")))
            }
    )
	ResponseEntity<CidadeOut> atualizar(@Parameter(name = "id", description = "o id da cidade a ser atualizada",
			required = true, example = "1") Long id, @RequestBody( description = "Dados a atualizar", required = true) CidadeIn cidadeIn);

	@Operation(summary = "Busca cidade por id", description = "É preciso passar o id da cidade.",
			responses = {
					@ApiResponse(responseCode = "200"),
					@ApiResponse(responseCode = "400", description = "Id inválido",
							content = @Content(schema = @Schema(ref = "Problem"))),
					@ApiResponse(responseCode = "404", description = "Não existe serviço solicitado",
					content = @Content(schema = @Schema(ref = "Problem"))),
					@ApiResponse(responseCode = "500", description = "erro interno servidor",
					content = @Content(schema = @Schema(ref = "Problem")))
			})
	ResponseEntity<CidadeOut> buscar(@Parameter(name = "id", description = "o id da cidade a ser selecionada",
			example = "1", required = true) Long id);

	@Operation(summary = "Lista todas as cidades",
			responses = {
					@ApiResponse(responseCode = "200"),
                    @ApiResponse(responseCode = "404", description = "Não existe o serviço solicitado",
                    		content = @Content(schema = @Schema(ref = "Problem"))),
					@ApiResponse(responseCode = "500", description = "erro interno servidor",
							content = @Content(schema = @Schema(ref = "Problem")))
			})
	CollectionModel<CidadeOut> listar();

	@Operation(summary = "Remove uma cidade", description = "É preciso passar um id",
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
	void remover(@Parameter(name = "id", description = "o id da cidade a ser removida", required = true,
		example = "1")  Long id);

	@Operation(summary = "Salva uma cidade", description = "É preciso passar um dto CidadeIn",
			responses = {
					@ApiResponse(responseCode = "201"),
                    @ApiResponse(responseCode = "409", description = "Existe divergência na requisição",
                    		content = @Content(schema = @Schema(ref = "Problem"))),
					@ApiResponse(responseCode = "500", description = "erro interno servidor",
							content = @Content(schema = @Schema(ref = "Problem")))
			})
	ResponseEntity<CidadeOut> salvar(@RequestBody( description = "Cidade dto a ser salva.", required = true) CidadeIn cidadeIn);

}