package com.ejs.algaworksCurso.domain.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ejs.algaworksCurso.api.model.dto.in.grupo.GrupoIn;
import com.ejs.algaworksCurso.api.model.dto.out.group.GrupoOut;
import com.ejs.algaworksCurso.domain.exception.EntidadeEmUsoException;
import com.ejs.algaworksCurso.domain.exception.GrupoNaoEncontradoException;
import com.ejs.algaworksCurso.domain.model.Grupo;
import com.ejs.algaworksCurso.domain.repository.GrupoRepository;
import com.ejs.algaworksCurso.helper.grupo.GrupoAssembler;
import com.ejs.algaworksCurso.helper.grupo.GrupoDisAssembler;

@Service
public class GrupoService {
	
	@Autowired
	private GrupoRepository grupoRepository;
	
	@Autowired
	private GrupoAssembler grupoAssembler;
	
	@Autowired
	private GrupoDisAssembler grupoDisAssembler;
	
	@Transactional
	public GrupoOut atualizar( GrupoIn grupoIn, Long grupoId) {
		Grupo grupo = this.buscarOuFalhar(grupoId);
		
		this.grupoAssembler.grupoIntoGrupo(grupoIn, grupo);
		
		grupo = this.grupoRepository.save(grupo);
		
		return this.grupoDisAssembler.grupoToGrupoOut(grupo);
		
	}
	
	public GrupoOut buscar(Long grupoId) {
		Grupo grupo = this.buscarOuFalhar(grupoId);
		return this.grupoDisAssembler.grupoToGrupoOut(grupo);
	}
	
	public List<GrupoOut> listar(){
		List<Grupo> grupos = this.grupoRepository.findAll();
		return grupos.stream()
				.map(grupo -> this.grupoDisAssembler.grupoToGrupoOut(grupo))
				.collect(Collectors.toList());
	}
	
	@Transactional
	public void remover( Long grupoId) {
		try {
			this.grupoRepository.deleteById(grupoId);
			this.grupoRepository.flush();
		} catch (EmptyResultDataAccessException e) {
			throw new GrupoNaoEncontradoException(grupoId);
		} catch( DataIntegrityViolationException e ) {
			throw new EntidadeEmUsoException(
					String.format("Grupo de código %d não pode ser deletado, pois está em uso", grupoId));
		}
	}
	
	@Transactional
	public GrupoOut salvar( GrupoIn grupoIn) {
		Grupo grupo = this.grupoAssembler.grupoInToGrupo(grupoIn);
		
		grupo = this.grupoRepository.save(grupo);
		
		return this.grupoDisAssembler.grupoToGrupoOut(grupo);
	}
	
	/**
	 * métodos auxiliares
	 */
	
	public Grupo buscarOuFalhar( Long grupoId) {
		return this.grupoRepository.findById(grupoId)
				.orElseThrow( () -> new GrupoNaoEncontradoException(grupoId));
	}
}
