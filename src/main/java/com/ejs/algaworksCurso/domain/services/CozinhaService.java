package com.ejs.algaworksCurso.domain.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ejs.algaworksCurso.api.model.in.cozinha.CozinhaIn;
import com.ejs.algaworksCurso.api.model.out.cozinha.CozinhaOut;
import com.ejs.algaworksCurso.domain.exception.CozinhaNaoEncontradaException;
import com.ejs.algaworksCurso.domain.exception.EntidadeEmUsoException;
import com.ejs.algaworksCurso.domain.model.Cozinha;
import com.ejs.algaworksCurso.domain.repository.CozinhaRepository;
import com.ejs.algaworksCurso.helper.cozinha.CozinhaAssembler;
import com.ejs.algaworksCurso.helper.cozinha.CozinhaDisAssembler;

@Service
public class CozinhaService {
		
	@Autowired
	private CozinhaRepository cozinhaRepository;
	
	@Autowired
	private CozinhaAssembler cozinhaAssembler;
	
	@Autowired
	private CozinhaDisAssembler cozinhaDisAssembler;
	
	@Autowired
	private PagedResourcesAssembler<Cozinha> pagedResourceAssembler;
	
	@Transactional
	public CozinhaOut atualizar(CozinhaIn cozinhaIn, Long cozinhaId) {
		Cozinha cozinhaAtual = this.buscarOuFalhar(cozinhaId);
		
		this.cozinhaAssembler.cozinhaInToCozinha(cozinhaAtual, cozinhaIn);
		cozinhaAtual = this.cozinhaRepository.save(cozinhaAtual);
		return cozinhaDisAssembler.toModel(cozinhaAtual);
	}
	
	public CozinhaOut buscar(Long id) {
		Cozinha cozinha = this.buscarOuFalhar(id);
		return this.cozinhaDisAssembler.toModel(cozinha);
	}
	
	public CozinhaOut buscarPrimeira() {
		Cozinha cozinha = this.cozinhaRepository.buscarPrimeiro()
				.orElseThrow(() -> new CozinhaNaoEncontradaException("Nenhum dado encontrado."));
		return this.cozinhaDisAssembler.toModel(cozinha);
	}
	
	public PagedModel<CozinhaOut> listar(Pageable pageable){
		Page<Cozinha> cozinhas = this.cozinhaRepository.findAll(pageable);
		
		PagedModel<CozinhaOut> cozinhasOut = this.pagedResourceAssembler.toModel(cozinhas, cozinhaDisAssembler);
	
		return cozinhasOut;
	}

	@Transactional
	public void remover(Long cozinhaId) {
		try {			
			cozinhaRepository.deleteById(cozinhaId);
			cozinhaRepository.flush();
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
					String.format("A cozinha de código %d não pode ser deletada, pois está em uso.", cozinhaId));
		} catch (EmptyResultDataAccessException e) {
			throw new CozinhaNaoEncontradaException(cozinhaId);
		}
	}
	
	@Transactional
	public CozinhaOut salvar(CozinhaIn cozinhaIn) {
		Cozinha cozinha = this.cozinhaAssembler.cozinhaInToCozinha(cozinhaIn);
		cozinha = cozinhaRepository.save(cozinha);
		return cozinhaDisAssembler.toModel(cozinha);
	}
	
	/*
	 * métodos auxiliares
	 */
	
	public Cozinha buscarOuFalhar( Long cozinhaId) {
		return this.cozinhaRepository.findById(cozinhaId)
				.orElseThrow( () -> new CozinhaNaoEncontradaException(cozinhaId));
	}

}
