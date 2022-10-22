package com.ejs.algaworksCurso.api.v1.openApi.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.ServletWebRequest;

import com.ejs.algaworksCurso.api.v1.model.in.formaPagamento.FormaPagamentoIn;
import com.ejs.algaworksCurso.api.v1.model.out.formaPagamento.FormaPagamentoOut;

@Tag(name = "Formas Pagamento")
@SecurityRequirement(name = "bearer-token")
public interface FormaPagamentoControllerOpenApi {

	@Operation(summary = "Atualiza Forma Pagamento", description = "Atualiza Forma Pagamento de acordo com o Id", 
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
	ResponseEntity<FormaPagamentoOut> autalizar(FormaPagamentoIn formaPagamentoIn, Long formaPagamentoId);

	@Operation(summary = "Busca Forma Pagamento", description = "Busca forma pagamento de acordo com o id", 
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
	ResponseEntity<FormaPagamentoOut> buscar(Long formaPagamentoId, ServletWebRequest request);

	@Operation(summary = "Lista Formas Pagamento", description = "Lista todas as formas de pagamento",
			responses = {
					@ApiResponse(responseCode = "200"),
					@ApiResponse(responseCode = "500", description = "erro interno servidor",
							content = @Content(schema = @Schema(ref = "Problem")))
			})
	ResponseEntity<CollectionModel<FormaPagamentoOut>> listar(ServletWebRequest request);

	@Operation(summary = "Remove Forma Pagamento", 
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
	void remover(Long formaPagamentoId);

	@Operation(summary = "Salva Forma Pagamento", 
			responses = {
					@ApiResponse(responseCode = "201"),
					@ApiResponse(responseCode = "500", description = "erro interno servidor",
							content = @Content(schema = @Schema(ref = "Problem")))
			})
	ResponseEntity<FormaPagamentoOut> salvar(FormaPagamentoIn formaPagamentoIn);

}