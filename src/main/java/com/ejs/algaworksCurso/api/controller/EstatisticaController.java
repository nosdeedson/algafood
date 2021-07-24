package com.ejs.algaworksCurso.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ejs.algaworksCurso.api.model.dto.VendaDiariaDTO;
import com.ejs.algaworksCurso.domain.model.filter.VendaDiariaFilter;
import com.ejs.algaworksCurso.domain.services.VendaQueryService;

@RestController
@RequestMapping("estatisticas")
public class EstatisticaController {
	
	@Autowired
	private VendaQueryService vendaQueryService;

	@GetMapping("venda-diaria")
	public ResponseEntity<?> buscarVendasDiarias(VendaDiariaFilter filtro ){
		List<VendaDiariaDTO> vendasDiaria = this.vendaQueryService.consultarVendasDiarias(filtro);

		return ResponseEntity.ok(vendasDiaria);
	}
}
