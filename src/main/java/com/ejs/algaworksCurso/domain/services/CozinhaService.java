package com.ejs.algaworksCurso.domain.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.ejs.algaworksCurso.domain.exception.EntidadeEmUsoException;
import com.ejs.algaworksCurso.domain.exception.EntidadeNaoEncontradaException;
import com.ejs.algaworksCurso.domain.model.Cozinha;
import com.ejs.algaworksCurso.domain.repository.CozinhaRepository;

@Service
public class CozinhaService {
	
	@Autowired
	private CozinhaRepository cozinhaRepository;
	
	public Cozinha atualizar(Cozinha cozinha, Long cozinhaId) {
		Cozinha cozinhaAtual = this.buscar(cozinhaId);
		if ( Optional.ofNullable(cozinhaAtual).isEmpty()) {
			return null;
		}
		
		BeanUtils.copyProperties(cozinhaAtual, cozinha, "id");
		
		return this.cozinhaRepository.save(cozinhaAtual);
	}
	
	public Cozinha buscarPrimeira() {
		return this.cozinhaRepository.buscarPrimeiro()
				.orElseThrow(() -> new EntidadeNaoEncontradaException("Nenhum dado encontrado."));
	}
	
	public List<Cozinha> listar(){
		Sort sort = Sort.by("nome");
		return this.cozinhaRepository.findAll(sort);
	}

	public Cozinha buscar(Long id) {
		return this.cozinhaRepository.findById(id)
				.orElseThrow( () -> new EntidadeNaoEncontradaException(
						String.format("Cozinda de código %d não encontrada.", id)));
	}
		
	public Cozinha salvar(Cozinha cozinha) {
		return cozinhaRepository.save(cozinha);
	}
	
	public boolean remover(Long cozinhaId) {
		Cozinha deletar = this.cozinhaRepository.findById(cozinhaId)
				.orElseThrow( () -> new EntidadeNaoEncontradaException(
						String.format("Cozinha de código %d não encontrada", cozinhaId)));
		try {			
			cozinhaRepository.delete(deletar);
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
					String.format("Cozinha de código %d não pode ser removida, pois está em uso.",
							deletar.getId()));
		}
		return true;
	}
}
