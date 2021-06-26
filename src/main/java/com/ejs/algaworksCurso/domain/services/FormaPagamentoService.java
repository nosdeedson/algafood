package com.ejs.algaworksCurso.domain.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ejs.algaworksCurso.api.model.dto.in.FormaPagamentoIn;
import com.ejs.algaworksCurso.api.model.dto.out.FormaPagamentoOut;
import com.ejs.algaworksCurso.domain.exception.EntidadeEmUsoException;
import com.ejs.algaworksCurso.domain.exception.EntidadeNaoEncontradaException;
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
	public void remover(Long formaPagamentoId) {
		try {
			this.formaPagamentoRespository.deleteById(formaPagamentoId);
		} catch (EntidadeEmUsoException e) {
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
	
	private FormaPagamento buscarOuFalhar(Long formaPagamentoId) {
		return this.formaPagamentoRespository.findById(formaPagamentoId)
				.orElseThrow( () -> new FormaPagamentoNaoEncontradoException(formaPagamentoId));
	}
	
}
