package com.ejs.algaworksCurso.api.v1.openApi.controller;

import com.ejs.algaworksCurso.api.v1.model.dto.VendaDiariaDTO;
import com.ejs.algaworksCurso.domain.model.filter.VendaDiariaFilter;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@Tag(name = "Estatísticas")
@SecurityRequirement(name = "bearer-token")
public interface EstatisticasControllerOpenApi {
	
	@Operation(summary = "Busca vendas diários", 
			responses = {
					@ApiResponse(responseCode = "200"),
					@ApiResponse(responseCode = "500", description = "erro interno servidor",
							content = @Content(schema = @Schema(ref = "Problem")))
			})
    public List<VendaDiariaDTO> buscarVendasDiarias(VendaDiariaFilter filtro );
}
