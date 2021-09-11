package com.ejs.algaworksCurso.domain.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ejs.algaworksCurso.api.model.out.permissao.PermissaoOut;
import com.ejs.algaworksCurso.domain.exception.GrupoNaoEncontradoException;
import com.ejs.algaworksCurso.domain.exception.NegocioException;
import com.ejs.algaworksCurso.domain.exception.PermissaoNaoEncontradaException;
import com.ejs.algaworksCurso.domain.model.Grupo;
import com.ejs.algaworksCurso.domain.model.Permissao;
import com.ejs.algaworksCurso.helper.permissao.PermissaoDisAssembler;

@Service
public class GrupoPermissaoService {

	
	@Autowired
	private PermissaoDisAssembler permissaoDisAssembler;
	
	@Autowired
	private GrupoService grupoService;
	
	@Autowired
	private PermissaoService permissaoService;
	
	
	@Transactional
	public void associar(Long grupoId, Long permissaoId) {
		Grupo grupo = this.grupoService.buscarOuFalhar(grupoId);
		Permissao permissao = this.permissaoService.buscarOuFalhar(permissaoId);
		grupo.associar(permissao);
	}
	
	@Transactional
	public void desassociar(Long grupoId, Long permissaoId) {
		try {
			Grupo grupo = this.grupoService.buscarOuFalhar(grupoId);
			Permissao permissao = this.permissaoService.buscarOuFalhar(permissaoId);
			grupo.desassociar(permissao);
		} catch (GrupoNaoEncontradoException | PermissaoNaoEncontradaException e) {
			String entity = "";
			Long id = 0L;
			if ( e instanceof GrupoNaoEncontradoException){
				entity = "Grupo";
				id = grupoId;
			}else {
				entity = "permissao";
				id = permissaoId;
			}
			throw new NegocioException(String.format("%s de código %d não existe", entity, id));
			 
		} 
	}
	
	public CollectionModel<PermissaoOut> listar(Long grupoId){
		Grupo grupo = this.grupoService.buscarOuFalhar(grupoId);
		return this.permissaoDisAssembler.toCollectionModel(grupo.getPermissoes());
	}
}
