package com.ejs.algaworksCurso.domain.services;

import java.util.List;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.hateoas.CollectionModel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ejs.algaworksCurso.api.v1.model.in.permissao.PermissaoIn;
import com.ejs.algaworksCurso.api.v1.model.out.permissao.PermissaoOut;
import com.ejs.algaworksCurso.domain.exception.EntidadeEmUsoException;
import com.ejs.algaworksCurso.domain.exception.NegocioException;
import com.ejs.algaworksCurso.domain.exception.PermissaoNaoEncontradaException;
import com.ejs.algaworksCurso.domain.model.Permissao;
import com.ejs.algaworksCurso.domain.repository.PermissaoRepository;
import com.ejs.algaworksCurso.helper.permissao.PermissaoAssembler;
import com.ejs.algaworksCurso.helper.permissao.PermissaoDisAssembler;

@Service
public class PermissaoService {

	@Autowired
	PermissaoRepository permissaoRepository;
	
	@Autowired
	PermissaoDisAssembler permissaoDisAssembler;

	@Autowired
	PermissaoAssembler permissaoAssembler;

	@Transactional(rollbackFor = Exception.class)
	public PermissaoOut atualizar(Long permissaoId, PermissaoIn in) {
		Permissao permissaoAtualizar = this.buscarOuFalhar(permissaoId);
		permissaoAssembler.permissaoInToPermissao(in, permissaoAtualizar);
		permissaoAtualizar = this.permissaoRepository.save(permissaoAtualizar);
		return permissaoDisAssembler.toModel(permissaoAtualizar);
	}
	
	public Permissao buscarOuFalhar(Long permissaoId) {
		return this.permissaoRepository.findById(permissaoId)
				.orElseThrow(() -> new PermissaoNaoEncontradaException(permissaoId));
	}
	
	public CollectionModel<PermissaoOut> listar(){
		List<Permissao> permissoes = this.permissaoRepository.findAll();
		CollectionModel<PermissaoOut> permissoesOut = this.permissaoDisAssembler.toCollectionModel(permissoes);
		return permissoesOut;		
	}

	@Transactional(rollbackFor = {NegocioException.class, EmptyResultDataAccessException.class})
	public void remover(Long permissaoId) {
		Permissao deletar = this.buscarOuFalhar(permissaoId);
		try {
			this.permissaoRepository.delete(deletar);
		} catch (EntidadeEmUsoException | ConstraintViolationException | DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
					String.format(
							"Permissão de id: %d não pode ser removida, pois está vinculada a grupo ", permissaoId));
		}		
	}

	@Transactional(rollbackFor = Exception.class)
	public PermissaoOut salvar(PermissaoIn in) {
		Permissao nova = this.permissaoAssembler.permissaoInToPermissao(in);
		nova = this.permissaoRepository.save(nova);
		return this.permissaoDisAssembler.toModel(nova);
	}
}
