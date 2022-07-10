package com.ejs.algaworksCurso.domain.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.hateoas.CollectionModel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ejs.algaworksCurso.api.v1.model.in.grupo.GrupoIn;
import com.ejs.algaworksCurso.api.v1.model.in.grupo.GrupoPermissoesIn;
import com.ejs.algaworksCurso.api.v1.model.out.group.GrupoOut;
import com.ejs.algaworksCurso.api.v1.model.out.usuario.UsuarioOut;
import com.ejs.algaworksCurso.domain.exception.EntidadeEmUsoException;
import com.ejs.algaworksCurso.domain.exception.GrupoNaoEncontradoException;
import com.ejs.algaworksCurso.domain.exception.NegocioException;
import com.ejs.algaworksCurso.domain.exception.UsuarioNaoEncontradoException;
import com.ejs.algaworksCurso.domain.model.Grupo;
import com.ejs.algaworksCurso.domain.model.Usuario;
import com.ejs.algaworksCurso.domain.repository.GrupoRepository;
import com.ejs.algaworksCurso.domain.repository.UsuarioRepository;
import com.ejs.algaworksCurso.helper.grupo.GrupoAssembler;
import com.ejs.algaworksCurso.helper.grupo.GrupoDisAssembler;
import com.ejs.algaworksCurso.helper.usuario.UsuarioDisAssembler;

@Service
public class GrupoService {
	
	@Autowired
	private GrupoRepository grupoRepository;
	
	@Autowired
	private GrupoAssembler grupoAssembler;
	
	@Autowired
	private GrupoDisAssembler grupoDisAssembler;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private UsuarioDisAssembler usuarioDisAssembler;
	
	@Autowired
	private GrupoPermissaoService grupoPermissaoService;
	
	@Transactional
	public GrupoOut atualizar( GrupoIn grupoIn, Long grupoId) {
		Grupo grupo = this.buscarOuFalhar(grupoId);
		
		this.grupoAssembler.grupoIntoGrupo(grupoIn, grupo);
		
		grupo = this.grupoRepository.save(grupo);
		
		return this.grupoDisAssembler.toModel(grupo);
		
	}
	
	public GrupoOut buscar(Long grupoId) {
		Grupo grupo = this.buscarOuFalhar(grupoId);
		return this.grupoDisAssembler.toModel(grupo);
	}
	
	public CollectionModel<GrupoOut> listar(){
		List<Grupo> grupos = this.grupoRepository.findAll();
		CollectionModel<GrupoOut> grupoOuts = this.grupoDisAssembler.toCollectionModel(grupos);
		return grupoOuts;
	}

	public CollectionModel<UsuarioOut> listarUsuariosPorGrupo(Long grupoId) {
		try {
			Grupo grupo = this.buscarOuFalhar(grupoId);
			List<Usuario> usuarios = this.usuarioRepository.findUsuariosPorGrupoId(grupo.getId());
			return this.usuarioDisAssembler.toCollectionModel(usuarios);
		} catch (UsuarioNaoEncontradoException e) {
			throw new UsuarioNaoEncontradoException(String.format(
					"Não foram encontrados usuários pertencentes ao grupo de id: ", grupoId));
		}
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
		
		return this.grupoDisAssembler.toModel(grupo);
	}
	
	@Transactional
	public GrupoOut salvarGrupoAssociarPermissoes(GrupoPermissoesIn in) {
		Grupo grupo = new Grupo();
		try {
			grupo = this.grupoAssembler.novoGrupo(in.getNome());
			grupo = this.grupoRepository.saveAndFlush(grupo);
			if( in.getPermissoes().size() > 0) {
				for ( Long idPermissao: in.getPermissoes()) {
					try {
						this.grupoPermissaoService.associar(grupo.getId(), idPermissao);
					} catch (Exception e) {
						continue;
					}
				}
			}
		} catch (Exception e) {
			throw new NegocioException("Falha ao salvar o novo grupo.");
		}
		return this.grupoDisAssembler.toModel(grupo);
	}
	
	/**
	 * métodos auxiliares
	 */
	
	public Grupo buscarOuFalhar( Long grupoId) {
		return this.grupoRepository.findById(grupoId)
				.orElseThrow( () -> new GrupoNaoEncontradoException(grupoId));
	}
}
