package com.ejs.algaworksCurso.domain.services;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.ejs.algaworksCurso.domain.exception.EntidadeEmUsoException;
import com.ejs.algaworksCurso.domain.exception.EstadoNaoEncontradoException;
import com.ejs.algaworksCurso.domain.model.Estado;
import com.ejs.algaworksCurso.domain.repository.EstadoRepository;

@Service
public class EstadoService {

	@Autowired
	private EstadoRepository estadoRepository;
	
	public Estado atualizar( Estado estado, Long id ) {
		Estado estadoAtual = this.buscar(id);
		
		BeanUtils.copyProperties(estado, estadoAtual, "id");
		return this.estadoRepository.save(estadoAtual);
	}
	
	public Estado buscar(Long estadoId) {
		Estado estado = this.estadoRepository.findById(estadoId)
				.orElseThrow( () -> new EstadoNaoEncontradoException( estadoId));
		return estado;
	}
	
	public List<Estado> listar(){
		return this.estadoRepository.findAll(Sort.by("nome"));
	}
	
	public void remover( Long id) {
		try {
			this.estadoRepository.deleteById(id);
		} catch ( DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
					String.format("Estado de código %d não pode ser removido, pois está em uso.", id));
		} catch (EmptyResultDataAccessException e) {
			throw new EstadoNaoEncontradoException(id);
		}
	}
	
	public Estado salvar(Estado estado) {
		return this.estadoRepository.save(estado);
	}
	
}
