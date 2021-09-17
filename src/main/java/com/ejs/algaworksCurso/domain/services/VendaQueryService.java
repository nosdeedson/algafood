package com.ejs.algaworksCurso.domain.services;

import java.util.List;

import com.ejs.algaworksCurso.api.v1.model.dto.VendaDiariaDTO;
import com.ejs.algaworksCurso.domain.model.filter.VendaDiariaFilter;

public interface VendaQueryService {
	
	List<VendaDiariaDTO> consultarVendasDiarias(VendaDiariaFilter filtro);
}
