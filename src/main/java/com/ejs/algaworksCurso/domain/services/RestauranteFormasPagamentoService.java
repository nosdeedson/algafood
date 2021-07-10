package com.ejs.algaworksCurso.domain.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ejs.algaworksCurso.api.model.dto.out.formaPagamento.FormaPagamentoOut;
import com.ejs.algaworksCurso.domain.model.FormaPagamento;
import com.ejs.algaworksCurso.domain.model.Restaurante;
import com.ejs.algaworksCurso.helper.formaPagamento.FormaPagamentoDisAssembler;

@Service
public class RestauranteFormasPagamentoService {

	@Autowired
	private RestauranteService restauranteService;
	
	@Autowired
	private FormaPagamentoDisAssembler formaPagamentoDisAssembler;
	
	@Autowired
	private FormaPagamentoService formaPagamentoService;
	
	public List<FormaPagamentoOut> listar(Long restauranteId){
		Restaurante restaurante = this.restauranteService.buscarOuFalhar(restauranteId);
		return restaurante.getFormasPagamento().stream()
				.map(item -> formaPagamentoDisAssembler.formaPagamentoToFormaPagamentoOut(item))
				.collect(Collectors.toList());
	}
	
	@Transactional
	public void associarFormaPagamento(Long restauranteId, Long FormaPagamentoId) {
		/*Ambos os objetos abaixo são gerenciados pelo JPA por isto não é necessário chamar o respository
		 * como houve mudança de estado do restaurante ao chamar o método associarFormaPagamento o próprio JPA 
		 * faz a sincronização com o banco*/
		Restaurante restaurante = this.restauranteService.buscarOuFalhar(restauranteId);
		FormaPagamento formaPagamento = this.formaPagamentoService.buscarOuFalhar(FormaPagamentoId);
		restaurante.associarFormaPagamento(formaPagamento);
	}
	
	@Transactional
	public void desassociarFormaPagamento(Long restauranteId, Long FormaPagamentoId) {
		Restaurante restaurante = this.restauranteService.buscarOuFalhar(restauranteId);
		FormaPagamento formaPagamento = this.formaPagamentoService.buscarOuFalhar(FormaPagamentoId);
		restaurante.desassociarFormaPagamento(formaPagamento);
	}
}
