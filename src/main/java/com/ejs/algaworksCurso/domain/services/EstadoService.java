package com.ejs.algaworksCurso.domain.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ejs.algaworksCurso.api.model.in.estado.EstadoIn;
import com.ejs.algaworksCurso.api.model.out.estado.EstadoOut;
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
		return estadoDisAssembler.estadoToEstadoOut(estadoAtual);
	}
	
	public EstadoOut buscar(Long estadoId) {
		Estado estado = this.buscarOuFalhar(estadoId);
		return estadoDisAssembler.estadoToEstadoOut(estado);
	}
	
	public List<EstadoOut> listar(){
		List<Estado> estados = this.estadoRepository.findAll(Sort.by("nome"));
		return estados.stream()
				.map(estado -> this.estadoDisAssembler.estadoToEstadoOut(estado))
				.collect(Collectors.toList());
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
	public Estado salvar(EstadoIn estadoIn) {
		Estado estado = this.estadoAssembler.estadoInToEstado(estadoIn);
		return this.estadoRepository.save(estado);
	}
	
	/*
	 * métodos auxiliares
	 */
	
	public Estado buscarOuFalhar(Long estadoId) {
		return this.estadoRepository.findById(estadoId)
				.orElseThrow( () -> new EstadoNaoEncontradoException(estadoId));
	}
	
}
