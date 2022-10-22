package com.ejs.algaworksCurso.api.v1.openApi.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;

import com.ejs.algaworksCurso.api.v1.model.in.pedido.PedidoIn;
import com.ejs.algaworksCurso.api.v1.model.out.formaPagamento.FormaPagamentoOut;
import com.ejs.algaworksCurso.api.v1.model.out.pedido.PedidoOut;
import com.ejs.algaworksCurso.api.v1.model.out.pedido.PedidoResumidoDTO;
import com.ejs.algaworksCurso.domain.model.filter.PedidoFilter;

@Tag(name = "Pedidos")
@SecurityRequirement(name = "bearer-token")
public interface PedidoControllerOpenApi {

	@Operation(summary = "Cancela pedido", 
			responses = {
					@ApiResponse(responseCode = "204"),
                    @ApiResponse(responseCode = "404", description = "código inexistente",
                    		content = @Content(schema = @Schema(ref = "Problem"))),
					@ApiResponse(responseCode = "500", description = "erro interno servidor",
							content = @Content(schema = @Schema(ref = "Problem")))
			})
	ResponseEntity<Void> cancelarPedido(String codigoPedido);

	@Operation(summary = "Confirma pedido", 
			responses = {
					@ApiResponse(responseCode = "200"),
                    @ApiResponse(responseCode = "404", description = "código inexistente",
                    		content = @Content(schema = @Schema(ref = "Problem"))),
					@ApiResponse(responseCode = "500", description = "erro interno servidor",
							content = @Content(schema = @Schema(ref = "Problem")))
			})
	ResponseEntity<?> confirmarPedido(String codigoPedido);

	@Operation(summary = "Remove Grupo", description = "Remove grupo de acordo com o Id", 
			responses = {
					@ApiResponse(responseCode = "200"),
                    @ApiResponse(responseCode = "404", description = "código inexistente",
                    		content = @Content(schema = @Schema(ref = "Problem"))),
					@ApiResponse(responseCode = "500", description = "erro interno servidor",
							content = @Content(schema = @Schema(ref = "Problem")))
			})
	ResponseEntity<PedidoOut> buscar(String codigoPedido);
	
	@Operation(summary = "Remove Grupo", description = "Remove grupo de acordo com o Id", 
			responses = {
					@ApiResponse(responseCode = "200"),
                    @ApiResponse(responseCode = "404", description = "código inexistente",
                    		content = @Content(schema = @Schema(ref = "Problem"))),
					@ApiResponse(responseCode = "500", description = "erro interno servidor",
							content = @Content(schema = @Schema(ref = "Problem")))
			})
	ResponseEntity<FormaPagamentoOut> buscarFormaPagamento(String codigoPedido);

	@Operation(summary = "Remove Grupo", description = "Remove grupo de acordo com o Id", 
			responses = {
					@ApiResponse(responseCode = "200"),
                    @ApiResponse(responseCode = "404", description = "código inexistente",
                    		content = @Content(schema = @Schema(ref = "Problem"))),
					@ApiResponse(responseCode = "500", description = "erro interno servidor",
							content = @Content(schema = @Schema(ref = "Problem")))
			})
	ResponseEntity<?> entregarPedido(String codigoPedido);

	@Operation(summary = "Lista pedidos", description = "Lista de pedidos de acordo com o filtro informado", 
			responses = {
					@ApiResponse(responseCode = "200"),
					@ApiResponse(responseCode = "500", description = "erro interno servidor",
							content = @Content(schema = @Schema(ref = "Problem")))
			})
	ResponseEntity<PagedModel<PedidoResumidoDTO>> listar(Pageable pageable, PedidoFilter filtro);

	@Operation(summary = "Salva pedido", 
			responses = {
					@ApiResponse(responseCode = "201"),
					@ApiResponse(responseCode = "500", description = "erro interno servidor",
							content = @Content(schema = @Schema(ref = "Problem")))
			})
	ResponseEntity<PedidoOut> salvar(PedidoIn pedidoIn);

}