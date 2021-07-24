package com.ejs.algaworksCurso.domain.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ejs.algaworksCurso.api.model.in.formaPagamento.FormaPagamentoIn;
import com.ejs.algaworksCurso.api.model.out.formaPagamento.FormaPagamentoOut;
import com.ejs.algaworksCurso.domain.exception.EntidadeEmUsoException;
import com.ejs.algaworksCurso.domain.exception.FormaPagamentoNaoEncontradoException;
import com.ejs.algaworksCurso.domain.model.FormaPagamento;
import com.ejs.algaworksCurso.domain.repository.FormaPagamentoRepository;
import com.ejs.algaworksCurso.helper.formaPagamento.FormaPagamentoAssembler;
import com.ejs.algaworksCurso.helper.formaPagamento.FormaPagamentoDisAssembler;

@Service
public class FormaPagamentoService {

	@Autowired
	private FormaPagamentoRepository formaPagamentoRespository;
	
	@Autowired
	private FormaPagamentoAssembler assembler;
	
	@Autowired
	private FormaPagamentoDisAssembler disAssembler;
	
	@Transactional
	public FormaPagamentoOut atualizar( FormaPagamentoIn formaPagamentoIn, Long formaPagamentoId) {
		FormaPagamento formaPagamento = this.buscarOuFalhar(formaPagamentoId);
		this.assembler.formaPagamentoInToFormaPagamento(formaPagamentoIn, formaPagamento);
		formaPagamento = this.formaPagamentoRespository.save(formaPagamento);
		return disAssembler.formaPagamentoToFormaPagamentoOut(formaPagamento);
	}
	
	public FormaPagamentoOut buscar( Long formaPagamentoId) {
		FormaPagamento fp = this.buscarOuFalhar(formaPagamentoId);
		return this.disAssembler.formaPagamentoToFormaPagamentoOut(fp);
	}
	
	public List<FormaPagamentoOut> listar(){
		List<FormaPagamento> formasPagamento = this.formaPagamentoRespository.findAll();
		return formasPagamento.stream()
				.map(fp -> this.disAssembler.formaPagamentoToFormaPagamentoOut(fp))
				.collect(Collectors.toList());
	}
	
	
	@Transactional
	public void remover(Long formaPagamentoId) {
		try {
			this.formaPagamentoRespository.deleteById(formaPagamentoId);
			this.formaPagamentoRespository.flush();
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
					String.format("FormaPagamento de id %d está em uso.", formaPagamentoId));
		} catch( EmptyResultDataAccessException e ) {
			throw new FormaPagamentoNaoEncontradoException(formaPagamentoId);
		}
	}
	
	
	
	@Transactional
	public FormaPagamentoOut salvar(FormaPagamentoIn formaPagamentoIn) {
		FormaPagamento formaPagamento = this.assembler.formaPagamentoInToFormaPagamento(formaPagamentoIn);
		formaPagamento = this.formaPagamentoRespository.save(formaPagamento);
		return this.disAssembler.formaPagamentoToFormaPagamentoOut(formaPagamento);
	}
	
	public FormaPagamento buscarOuFalhar(Long formaPagamentoId) {
		return this.formaPagamentoRespository.findById(formaPagamentoId)
				.orElseThrow( () -> new FormaPagamentoNaoEncontradoException(formaPagamentoId));
	}
	
}
