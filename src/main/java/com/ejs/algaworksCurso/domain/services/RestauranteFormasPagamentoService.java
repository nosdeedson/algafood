package com.ejs.algaworksCurso.domain.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ejs.algaworksCurso.api.controller.RestauranteFormasPagamentoController;
import com.ejs.algaworksCurso.api.model.out.formaPagamento.FormaPagamentoOut;
import com.ejs.algaworksCurso.domain.model.FormaPagamento;
import com.ejs.algaworksCurso.domain.model.Restaurante;
import com.ejs.algaworksCurso.domain.repository.RestauranteRepository;
import com.ejs.algaworksCurso.helper.formaPagamento.FormaPagamentoDisAssembler;

@Service
public class RestauranteFormasPagamentoService {

	@Autowired
	private RestauranteService restauranteService;
	
	@Autowired
	private FormaPagamentoDisAssembler formaPagamentoDisAssembler;
	
	@Autowired
	private FormaPagamentoService formaPagamentoService;
	
	@Autowired
	private RestauranteRepository restauranteRepository;
		
	@Transactional
	public void associarFormaPagamento(Long restauranteId, Long FormaPagamentoId) {
		/*Ambos os objetos abaixo são gerenciados pelo JPA por isto não é necessário chamar o respository
		 * como houve mudança de estado do restaurante ao chamar o método associarFormaPagamento o próprio JPA 
		 * faz a sincronização com o banco*/
		Restaurante restaurante = this.restauranteService.buscarOuFalhar(restauranteId);
		FormaPagamento formaPagamento = this.formaPagamentoService.buscarOuFalhar(FormaPagamentoId);
		restaurante.associarFormaPagamento(formaPagamento);
	}
	
	public FormaPagamentoOut buscar(Long restauranteId, Long FormaPagamentoId) {
		this.restauranteService.buscarOuFalhar(restauranteId);
		FormaPagamento fp = this.formaPagamentoService.buscarOuFalhar(FormaPagamentoId);
		FormaPagamento formaPagamento = this.restauranteRepository.findByIdAndFormaPagamento(restauranteId, fp);
		return this.formaPagamentoDisAssembler.toModel(formaPagamento);
	}
	
	@Transactional
	public void desassociarFormaPagamento(Long restauranteId, Long FormaPagamentoId) {
		Restaurante restaurante = this.restauranteService.buscarOuFalhar(restauranteId);
		FormaPagamento formaPagamento = this.formaPagamentoService.buscarOuFalhar(FormaPagamentoId);
		restaurante.desassociarFormaPagamento(formaPagamento);
	}
	
	public CollectionModel<FormaPagamentoOut> listar(Long restauranteId){
		Restaurante restaurante = this.restauranteService.buscarOuFalhar(restauranteId);
		CollectionModel<FormaPagamentoOut> formasPagamentoOuts = this.formaPagamentoDisAssembler.toCollectionModel(restaurante.getFormasPagamento());
		formasPagamentoOuts
				.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(RestauranteFormasPagamentoController.class)
						.associarFormaPagamento(restaurante.getId(), null)).withRel("associarFormaPagamento"));
		
		return formasPagamentoOuts;
	}
}
