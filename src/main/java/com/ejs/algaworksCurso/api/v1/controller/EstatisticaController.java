package com.ejs.algaworksCurso.api.v1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ejs.algaworksCurso.api.v1.model.dto.VendaDiariaDTO;
import com.ejs.algaworksCurso.core.security.CheckSecurity;
import com.ejs.algaworksCurso.domain.model.filter.VendaDiariaFilter;
import com.ejs.algaworksCurso.domain.services.VendaQueryService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "Estatisticas")
@RestController
@RequestMapping("estatisticas")
public class EstatisticaController {
	
	@Autowired
	private VendaQueryService vendaQueryService;

	@CheckSecurity.Estatisticas.PodeConsular
	@ApiOperation(value = "Vendas diárias")
	@GetMapping("venda-diaria")
	@ResponseStatus(code = HttpStatus.OK)
	public List<VendaDiariaDTO> buscarVendasDiarias(VendaDiariaFilter filtro ){
		List<VendaDiariaDTO> vendasDiaria = this.vendaQueryService.consultarVendasDiarias(filtro);

		return vendasDiaria;
	}
}
