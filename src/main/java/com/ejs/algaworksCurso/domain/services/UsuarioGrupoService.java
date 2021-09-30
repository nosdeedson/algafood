package com.ejs.algaworksCurso.domain.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ejs.algaworksCurso.api.v1.model.out.group.GrupoOut;
import com.ejs.algaworksCurso.domain.exception.GrupoNaoEncontradoException;
import com.ejs.algaworksCurso.domain.exception.NegocioException;
import com.ejs.algaworksCurso.domain.exception.UsuarioNaoEncontradoException;
import com.ejs.algaworksCurso.domain.model.Grupo;
import com.ejs.algaworksCurso.domain.model.Usuario;
import com.ejs.algaworksCurso.helper.grupo.GrupoDisAssembler;

@Service
public class UsuarioGrupoService {

	@Autowired 
	private UsuarioService usuarioService;
	
	@Autowired
	private GrupoDisAssembler grupoDisAssembler;
	
	@Autowired
	private GrupoService grupoService;
	
	@Transactional
	public void associar(Long usuarioId, Long grupoId) {
		try {
			Usuario user = this.usuarioService.buscarOuFalhar(usuarioId);
			Grupo grupo = this.grupoService.buscarOuFalhar(grupoId);
			user.associarGrupo(grupo);
		} catch (UsuarioNaoEncontradoException | GrupoNaoEncontradoException e) {
			String entity = "";
			Long id = 0L;
			if ( e instanceof UsuarioNaoEncontradoException) {
				entity = "Usuário";
				id = usuarioId;
			}else {
				entity  = "Grupo";
				id = grupoId;
			}
			throw new NegocioException(String.format("%s de códito %d não existe", entity, id));
		}
	}
	
	@Transactional
	public void desassociar(Long usuarioId, Long grupoId) {
		try {
			Usuario user = this.usuarioService.buscarOuFalhar(usuarioId);
			Grupo grupo = this.grupoService.buscarOuFalhar(grupoId);
			user.desassociarGrupo(grupo);
		} catch (UsuarioNaoEncontradoException | GrupoNaoEncontradoException e) {
			String entity = "";
			Long id = 0L;
			if ( e instanceof UsuarioNaoEncontradoException) {
				entity = "Usuário";
				id = usuarioId;
			}else {
				entity  = "Grupo";
				id = grupoId;
			}
			throw new NegocioException(String.format("%s de códito %d não existe", entity, id));
		}
	}
	
	
	public CollectionModel<GrupoOut> listar(Long usuarioId) {
		try {
			Usuario user = this.usuarioService.buscarOuFalhar(usuarioId);
			return this.grupoDisAssembler.toCollectionModel(user.getGrupos());
		} catch (UsuarioNaoEncontradoException e) {
			throw new NegocioException(String.format("Usuário de código %d não existe.", usuarioId));
		}
	}
}
