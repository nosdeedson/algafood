package com.ejs.algaworksCurso.domain.services.v2;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ejs.algaworksCurso.api.v2.model.in.cidade.CidadeInV2;
import com.ejs.algaworksCurso.api.v2.model.out.cidade.CidadeOutV2;
import com.ejs.algaworksCurso.domain.exception.CidadeNaoEncontradaException;
import com.ejs.algaworksCurso.domain.exception.EntidadeEmUsoException;
import com.ejs.algaworksCurso.domain.exception.NegocioException;
import com.ejs.algaworksCurso.domain.model.Cidade;
import com.ejs.algaworksCurso.domain.repository.CidadeRepository;
import com.ejs.algaworksCurso.domain.services.EstadoService;
import com.ejs.algaworksCurso.helper.v2.CidadeAssemblerV2;
import com.ejs.algaworksCurso.helper.v2.CidadeDisAssemblerV2;

@Service
public class CidadeServiceV2 {
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private EstadoService estadoService;
	
	@Autowired
	private CidadeAssemblerV2 cidadeAssemblerV2;
	
	@Autowired
	private CidadeDisAssemblerV2 cidadeDisAssemblerV2;
	
	@Transactional(rollbackFor = {NegocioException.class, Exception.class})
	public CidadeOutV2 atualizar(CidadeInV2 cidadeIn, Long id) {
		
		this.estadoService.buscar(id);

		Cidade cidadeAtual = this.buscarOuFalhar(id);
		
		this.cidadeAssemblerV2.cidadeIntoCidade(cidadeAtual, cidadeIn);
		
		cidadeAtual = this.cidadeRepository.save(cidadeAtual);	
		return this.cidadeDisAssemblerV2.toModel(cidadeAtual);
	}
	
	public CidadeOutV2 buscar( Long id) {
		Cidade cidade = this.buscarOuFalhar(id);
		return this.cidadeDisAssemblerV2.toModel(cidade);
	}
	
	public List<CidadeOutV2>listar(){
		Sort sort = Sort.by("nome");
		List<Cidade> cidades = this.cidadeRepository.findAll(sort);
		return cidades.stream().map(item -> this.cidadeDisAssemblerV2.toModel(item))
				.collect(Collectors.toList());
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
	public CidadeOutV2 salvar( CidadeInV2 cidadeIn) {
		this.estadoService.buscarOuFalhar(cidadeIn.getEstadoId());
		Cidade cidade = this.cidadeAssemblerV2.cidadeInToCidade(cidadeIn);
		
		cidade = this.cidadeRepository.save(cidade);
		return this.cidadeDisAssemblerV2.toModel(cidade);
	}
	
	public Cidade buscarOuFalhar( Long cidadeId) {
		return this.cidadeRepository.findById(cidadeId)
				.orElseThrow( () -> new CidadeNaoEncontradaException(cidadeId) );
	}

}
