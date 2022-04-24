package com.ejs.algaworksCurso.domain.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Sort;
import org.springframework.hateoas.CollectionModel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ejs.algaworksCurso.api.v1.model.in.cidade.CidadeIn;
import com.ejs.algaworksCurso.api.v1.model.out.cidade.CidadeOut;
import com.ejs.algaworksCurso.domain.exception.CidadeNaoEncontradaException;
import com.ejs.algaworksCurso.domain.exception.EntidadeEmUsoException;
import com.ejs.algaworksCurso.domain.exception.NegocioException;
import com.ejs.algaworksCurso.domain.model.Cidade;
import com.ejs.algaworksCurso.domain.repository.CidadeRepository;
import com.ejs.algaworksCurso.helper.cidade.CidadeAssembler;
import com.ejs.algaworksCurso.helper.cidade.CidadeDisAssembler;

@Service
public class CidadeService {
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private EstadoService estadoService;
	
	@Autowired
	private CidadeAssembler cidadeAssembler;
	
	@Autowired
	private CidadeDisAssembler cidadeDisAssembler;
	
	@Transactional(rollbackFor = {NegocioException.class, Exception.class})
	public CidadeOut atualizar(CidadeIn cidadeIn, Long id) {
		
		this.estadoService.buscar(cidadeIn.getEstado().getId());

		Cidade cidadeAtual = this.buscarOuFalhar(id);
		
		this.cidadeAssembler.cidadeIntoCidade(cidadeAtual, cidadeIn);
		
		cidadeAtual = this.cidadeRepository.save(cidadeAtual);	
		return this.cidadeDisAssembler.toModel(cidadeAtual);
	}
	
	public CidadeOut buscar( Long id) {
		Cidade cidade = this.buscarOuFalhar(id);
		return this.cidadeDisAssembler.toModel(cidade);
	}
	
	public CollectionModel<CidadeOut>listar(){
		Sort sort = Sort.by("nome");
		List<Cidade> cidades = this.cidadeRepository.findAll(sort);
		return this.cidadeDisAssembler.toCollectionModel(cidades);
	}
	
	@Transactional(rollbackFor = {NegocioException.class, EmptyResultDataAccessException.class})
	public void remover( Long id) {
		try {			
			this.cidadeRepository.deleteById(id);	
			cidadeRepository.flush();
		} catch (EntidadeEmUsoException e) {
			throw new EntidadeEmUsoException(String.format("Cidade de código %d não pode ser deletada, pois está em uso", id));
		} catch (EmptyResultDataAccessException e) {
			throw new CidadeNaoEncontradaException(id);
		}
	}
	
	@Transactional(rollbackFor = {Exception.class})
	public CidadeOut salvar( CidadeIn cidadeIn) {
		this.estadoService.buscarOuFalhar(cidadeIn.getEstado().getId());
		Cidade cidade = this.cidadeAssembler.cidadeInToCidade(cidadeIn);
		
		cidade = this.cidadeRepository.save(cidade);
		return this.cidadeDisAssembler.toModel(cidade);
	}
	
	public Cidade buscarOuFalhar( Long cidadeId) {
		return this.cidadeRepository.findById(cidadeId)
				.orElseThrow( () -> new CidadeNaoEncontradaException(cidadeId) );
	}

}
