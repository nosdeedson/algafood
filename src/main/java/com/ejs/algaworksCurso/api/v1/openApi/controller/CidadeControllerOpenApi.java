package com.ejs.algaworksCurso.api.v1.openApi.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

import com.ejs.algaworksCurso.api.v1.model.in.cidade.CidadeIn;
import com.ejs.algaworksCurso.api.v1.model.out.cidade.CidadeOut;


@SecurityRequirement(name = "bearer-key")
@Tag(name = "Cidades")
public interface CidadeControllerOpenApi {

	@Operation(summary = "Atualiza uma cidade",
			description = "Para atualizar uma cidade é necessário passar o id na url, e o um dto CidadeIn",
            responses = {
                    @ApiResponse(responseCode = "200"),
                    @ApiResponse(responseCode = "400", description = "Id da ciade inválido",
                            content = @Content(schema = @Schema)),
					@ApiResponse(responseCode = "500", description = "erro interno servidor",
							content = @Content(schema = @Schema(ref = "Problem")))
            }
    )
	ResponseEntity<CidadeOut> atualizar(@Parameter(name = "id", description = "o id da cidade a ser atualizada",
			required = true, example = "1") Long id, @RequestBody( description = "Dados a atualizar", required = true) CidadeIn cidadeIn);

	@Operation(summary = "Busca cidade por id", description = "É preciso passar o id da cidade.")
	ResponseEntity<CidadeOut> buscar(@Parameter(name = "id", description = "o id da cidade a ser selecionada",
			example = "1", required = true) Long id);

	@Operation(summary = "Lista todas as cidades")
	CollectionModel<CidadeOut> listar();

	@Operation(summary = "Remove uma cidade", description = "É preciso passar um id")
	void remover(@Parameter(name = "id", description = "o id da cidade a ser removida", required = true,
		example = "1")  Long id);

	@Operation(summary = "Salva uma cidade", description = "É preciso passar um dto CidadeIn")
	ResponseEntity<CidadeOut> salvar(@RequestBody( description = "Cidade dto a ser salva.", required = true) CidadeIn cidadeIn);

}