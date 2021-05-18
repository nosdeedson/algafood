package com.ejs.algaworksCurso.domain.services;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.ejs.algaworksCurso.domain.exception.EntidadeEmUsoException;
import com.ejs.algaworksCurso.domain.exception.EntidadeNaoEncontradaException;
import com.ejs.algaworksCurso.domain.model.Estado;
import com.ejs.algaworksCurso.domain.repository.EstadoRepository;

@Service
public class EstadoService {

	@Autowired
	private EstadoRepository estadoRepository;
	
	public Estado atualizar( Estado estado, Long id ) {
		Estado estadoAtual = this.estadoRepository.findById(id)
				.orElseThrow( () -> new EntidadeNaoEncontradaException(
					String.format("Estado de código %d não encontrado.", id)));
		
		BeanUtils.copyProperties(estado, estadoAtual, "id");
		return this.estadoRepository.save(estadoAtual);
	}
	
	public Estado buscar(Long estadoId) {
		Estado estado = this.estadoRepository.findById(estadoId)
				.orElseThrow( () -> new EntidadeNaoEncontradaException(
					String.format("Estado de código %d não existe.", estadoId)));
		return estado;
	}
	
	public List<Estado> listar(){
		return this.estadoRepository.findAll(Sort.by("nome"));
	}
	
	public void remover( Long id) {
		try {
			Estado estado = this.estadoRepository.findById(id)
					.orElseThrow(() ->  new EntidadeNaoEncontradaException(
						String.format("Estado de código %d não encontrado.", id)));
			this.estadoRepository.delete(estado);
		} catch ( DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
					String.format("Estado de código %d não pode ser removido, pois está em uso", id));
		}
	}
	
	public Estado salvar(Estado estado) {
		return this.estadoRepository.save(estado);
	}
	
	
	
}
