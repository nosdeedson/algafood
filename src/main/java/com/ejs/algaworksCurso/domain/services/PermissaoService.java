package com.ejs.algaworksCurso.domain.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.stereotype.Service;

import com.ejs.algaworksCurso.api.model.out.permissao.PermissaoOut;
import com.ejs.algaworksCurso.domain.exception.PermissaoNaoEncontradaException;
import com.ejs.algaworksCurso.domain.model.Permissao;
import com.ejs.algaworksCurso.domain.repository.PermissaoRepository;
import com.ejs.algaworksCurso.helper.permissao.PermissaoDisAssembler;

@Service
public class PermissaoService {

	@Autowired
	PermissaoRepository permissaoRepository;
	
	@Autowired
	PermissaoDisAssembler permissaoDisAssembler;
	
	public Permissao buscarOuFalhar(Long permissaoId) {
		return this.permissaoRepository.findById(permissaoId)
				.orElseThrow(() -> new PermissaoNaoEncontradaException(permissaoId));
	}
	
	public CollectionModel<PermissaoOut> listar(){
		List<Permissao> permissoes = this.permissaoRepository.findAll();
		CollectionModel<PermissaoOut> permissoesOut = this.permissaoDisAssembler.toCollectionModel(permissoes);
		return permissoesOut;		
	}
}
