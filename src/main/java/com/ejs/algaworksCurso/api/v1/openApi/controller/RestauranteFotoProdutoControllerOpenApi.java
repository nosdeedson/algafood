package com.ejs.algaworksCurso.api.v1.openApi.controller;

import java.io.IOException;

import com.ejs.algaworksCurso.api.v1.model.in.produto.FotoProdutoIn;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.multipart.MultipartFile;

import com.ejs.algaworksCurso.api.v1.model.out.fotoProduto.FotoProdutoOut;
@Tag(name = "Restaurantes/Foto")
@SecurityRequirement(name = "bearer-token")
public interface RestauranteFotoProdutoControllerOpenApi {

	@Operation(summary = "Atualiza foto", description = "Atualiza foto de um produto de um restaurante específico", 
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
	ResponseEntity<FotoProdutoOut> atualizarFoto(@Parameter(description = "id restaurante", example = "1", required = true) Long restauranteId,
												 @Parameter(description = "id do produto", example = "1") Long produtoId,
												 @RequestBody(required = true) FotoProdutoIn fotoProdutoIn)
			throws IOException;

	@Operation(summary = "Busca Dados Foto", description = "busca dados de uma foto de um produto", 
			responses = {
					@ApiResponse(responseCode = "200"),
                    @ApiResponse(responseCode = "400", description = "Id inválido",
                            content = @Content(schema = @Schema(ref = "Problem"))),
                    @ApiResponse(responseCode = "404", description = "Não existe o serviço solicitado",
                    		content = @Content(schema = @Schema(ref = "Problem"))),
					@ApiResponse(responseCode = "500", description = "erro interno servidor",
							content = @Content(schema = @Schema(ref = "Problem")))
			})
	ResponseEntity<FotoProdutoOut> buscarDadosFoto(Long restauranteId, Long produtoId);

	@Operation(summary = "Busca foto", description = "busca uma foto de um produto", 
			responses = {
					@ApiResponse(responseCode = "200",
						content = {
								@Content(mediaType = "application/json", schema = @Schema(implementation = FotoProdutoOut.class)),
								@Content(mediaType = "png", schema = @Schema(type = "string", format = "bynary")),
								@Content(mediaType = "jpeg", schema = @Schema(type = "string", format = "bynary"))
						}
					),
                    @ApiResponse(responseCode = "400", description = "Id inválido",
                            content = @Content(schema = @Schema(ref = "Problem"))),
                    @ApiResponse(responseCode = "404", description = "Não existe o serviço solicitado",
                    		content = @Content(schema = @Schema(ref = "Problem"))),
					@ApiResponse(responseCode = "500", description = "erro interno servidor",
							content = @Content(schema = @Schema(ref = "Problem"))),
			}
	)
	ResponseEntity<?> buscarFoto(Long restauranteId, Long produtoId, String acceptType)
			throws HttpMediaTypeNotAcceptableException;

	@Operation(summary = "Deletar foto produto", description = "deleta foto de um produto", 
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
	void deletarFotoProduto(Long restauranteId, Long produtoId);

}