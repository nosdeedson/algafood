package com.ejs.algaworksCurso.domain.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ejs.algaworksCurso.domain.exception.PermissaoNaoEncontradaException;
import com.ejs.algaworksCurso.domain.model.Permissao;
import com.ejs.algaworksCurso.domain.repository.PermissaoRepository;

@Service
public class PermissaoService {

	@Autowired PermissaoRepository permissaoRepository;
	
	public Permissao buscarOuFalhar(Long permissaoId) {
		return this.permissaoRepository.findById(permissaoId)
				.orElseThrow(() -> new PermissaoNaoEncontradaException(permissaoId));
	}
}
