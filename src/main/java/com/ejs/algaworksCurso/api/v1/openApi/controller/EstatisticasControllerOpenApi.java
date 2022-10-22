package com.ejs.algaworksCurso.api.v1.openApi.controller;

import com.ejs.algaworksCurso.api.v1.model.dto.VendaDiariaDTO;
import com.ejs.algaworksCurso.domain.model.filter.VendaDiariaFilter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@Tag(name = "Estatísticas")
@SecurityRequirement(name = "bearer-token")
public interface EstatisticasControllerOpenApi {
    public List<VendaDiariaDTO> buscarVendasDiarias(VendaDiariaFilter filtro );
}
