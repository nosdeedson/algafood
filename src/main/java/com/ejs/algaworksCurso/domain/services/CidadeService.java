package com.ejs.algaworksCurso.domain.services;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.ejs.algaworksCurso.domain.exception.EntidadeNaoEncontradaException;
import com.ejs.algaworksCurso.domain.model.Cidade;
import com.ejs.algaworksCurso.domain.model.Estado;
import com.ejs.algaworksCurso.domain.repository.CidadeRepository;
import com.ejs.algaworksCurso.domain.repository.EstadoRepository;

@Service
public class CidadeService {
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	public Cidade atualizar(Cidade cidade, Long id) {
		Cidade cidadeAtual = this.cidadeRepository.findById(id)
				.orElseThrow( () -> new EntidadeNaoEncontradaException(
						String.format("Cidade de código %d não encontrada", id)));
		
		Estado estado = this.estadoRepository.findById(cidade.getEstado().getId())
				.orElseThrow( () -> new EntidadeNaoEncontradaException(
						String.format("Estado de código %d não encontrado.", cidade.getEstado().getId())));

		cidade.setEstado(estado);
		
		BeanUtils.copyProperties(cidade, cidadeAtual, "id");
		
		return this.cidadeRepository.save(cidadeAtual);			
	}
	
	public Cidade buscar( Long id) {
		Cidade cidade = this.cidadeRepository.findById(id)
				.orElseThrow( () -> new EntidadeNaoEncontradaException(
						String.format("Cidade de código %d não encontrada", id)) );
		return cidade;
	}
	
	public List<Cidade> listar(){
		Sort sort = Sort.by("nome");
		return this.cidadeRepository.findAll(sort);
	}
	
	public void remover( Long id) {
		Cidade cidade = this.cidadeRepository.findById(id)
				.orElseThrow( () -> new EntidadeNaoEncontradaException(
						String.format("Cidade de código %d não encontrada", id)) );
		this.cidadeRepository.delete(cidade);			
	}
	
	public Cidade salvar( Cidade cidade) {
		Estado estado = this.estadoRepository.findById(cidade.getEstado().getId())
				.orElseThrow( () -> new EntidadeNaoEncontradaException(
						String.format("Estado de código %d não encontrado.", cidade.getEstado().getId())));
		cidade.setEstado(estado);
		return this.cidadeRepository.save(cidade);
	}

}
