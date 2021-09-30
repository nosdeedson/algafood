package com.ejs.algaworksCurso.domain.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Sort;
import org.springframework.hateoas.CollectionModel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ejs.algaworksCurso.api.v1.model.in.estado.EstadoIn;
import com.ejs.algaworksCurso.api.v1.model.out.estado.EstadoOut;
import com.ejs.algaworksCurso.domain.exception.EntidadeEmUsoException;
import com.ejs.algaworksCurso.domain.exception.EstadoNaoEncontradoException;
import com.ejs.algaworksCurso.domain.model.Estado;
import com.ejs.algaworksCurso.domain.repository.EstadoRepository;
import com.ejs.algaworksCurso.helper.estado.EstadoAssembler;
import com.ejs.algaworksCurso.helper.estado.EstadoDisAssembler;

@Service
public class EstadoService {

	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private EstadoAssembler estadoAssembler;
	
	@Autowired
	private EstadoDisAssembler estadoDisAssembler;
	
	@Transactional
	public EstadoOut atualizar( EstadoIn estadoIn, Long id ) {
		Estado estadoAtual = this.buscarOuFalhar(id);
		this.estadoAssembler.estadoInToEstado(estadoAtual, estadoIn);
		estadoAtual = this.estadoRepository.save(estadoAtual);
		return estadoDisAssembler.toModel(estadoAtual);
	}
	
	public EstadoOut buscar(Long estadoId) {
		Estado estado = this.buscarOuFalhar(estadoId);
		return estadoDisAssembler.toModel(estado);
	}
	
	public CollectionModel<EstadoOut> listar(){
		List<Estado> estados = this.estadoRepository.findAll(Sort.by("nome"));
		return this.estadoDisAssembler.toCollectionModel(estados);
	}
	
	@Transactional
	public void remover( Long id) {
		try {
			this.estadoRepository.deleteById(id);
			estadoRepository.flush();
		} catch ( DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
					String.format("Estado de código %d não pode ser removido, pois está em uso.", id));
		} catch (EmptyResultDataAccessException e) {
			throw new EstadoNaoEncontradoException(id);
		}
	}
	
	@Transactional
	public EstadoOut salvar(EstadoIn estadoIn) {
		Estado estado = this.estadoAssembler.estadoInToEstado(estadoIn);
		estado = this.estadoRepository.save(estado);
		return estadoDisAssembler.toModel(estado);
	}
	
	/*
	 * métodos auxiliares
	 */
	
	public Estado buscarOuFalhar(Long estadoId) {
		return this.estadoRepository.findById(estadoId)
				.orElseThrow( () -> new EstadoNaoEncontradoException(estadoId));
	}
	
}
