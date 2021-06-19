package com.ejs.algaworksCurso.domain.services;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ejs.algaworksCurso.domain.exception.CidadeNaoEncontradaException;
import com.ejs.algaworksCurso.domain.exception.EntidadeEmUsoException;
import com.ejs.algaworksCurso.domain.exception.EstadoNaoEncontradoException;
import com.ejs.algaworksCurso.domain.exception.NegocioException;
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
	
	@Transactional(rollbackFor = {NegocioException.class, Exception.class})
	public Cidade atualizar(Cidade cidade, Long id) {
		
		Estado estado = this.estadoRepository.findById(cidade.getEstado().getId())
				.orElseThrow( () -> new NegocioException(
						String.format("Estado de código %d não existe.", cidade.getEstado().getId())));

		Cidade cidadeAtual = this.buscar(id);
		
		cidade.setEstado(estado);
		
		BeanUtils.copyProperties(cidade, cidadeAtual, "id");
		
		return this.cidadeRepository.save(cidadeAtual);			
	}
	
	public Cidade buscar( Long id) {
		Cidade cidade = this.cidadeRepository.findById(id)
				.orElseThrow( () -> new CidadeNaoEncontradaException(id) );
		return cidade;
	}
	
	public List<Cidade> listar(){
		Sort sort = Sort.by("nome");
		return this.cidadeRepository.findAll(sort);
	}
	
	@Transactional(rollbackFor = {NegocioException.class, EmptyResultDataAccessException.class})
	public void remover( Long id) {
		try {			
			this.cidadeRepository.deleteById(id);			
		} catch (EntidadeEmUsoException e) {
			throw new EntidadeEmUsoException(String.format("Cidade de código %d não pode ser deletada, pois está em uso", id));
		} catch (EmptyResultDataAccessException e) {
			throw new CidadeNaoEncontradaException(id);
		}
	}
	
	@Transactional(rollbackFor = {Exception.class})
	public Cidade salvar( Cidade cidade) {
		Estado estado = this.estadoRepository.findById(cidade.getEstado().getId())
				.orElseThrow( () -> new EstadoNaoEncontradoException(cidade.getEstado().getId()));
		cidade.setEstado(estado);
		return this.cidadeRepository.save(cidade);
	}

}
