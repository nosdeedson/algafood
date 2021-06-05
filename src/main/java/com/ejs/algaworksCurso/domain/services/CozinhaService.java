package com.ejs.algaworksCurso.domain.services;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.ejs.algaworksCurso.domain.exception.CozinhaNaoEncontradaException;
import com.ejs.algaworksCurso.domain.exception.EntidadeEmUsoException;
import com.ejs.algaworksCurso.domain.model.Cozinha;
import com.ejs.algaworksCurso.domain.repository.CozinhaRepository;

@Service
public class CozinhaService {
		
	@Autowired
	private CozinhaRepository cozinhaRepository;
	
	public Cozinha atualizar(Cozinha cozinha, Long cozinhaId) {
		Cozinha cozinhaAtual = this.buscar(cozinhaId);
		
		BeanUtils.copyProperties(cozinha, cozinhaAtual, "id");
		
		return this.cozinhaRepository.save(cozinhaAtual);
	}
	
	public Cozinha buscar(Long id) {
		return this.cozinhaRepository.findById(id)
				.orElseThrow( () -> new CozinhaNaoEncontradaException(id));
	}
	
	public Cozinha buscarPrimeira() {
		return this.cozinhaRepository.buscarPrimeiro()
				.orElseThrow(() -> new CozinhaNaoEncontradaException("Nenhum dado encontrado."));
	}
	
	public List<Cozinha> listar(){
		Sort sort = Sort.by("nome");
		return this.cozinhaRepository.findAll(sort);
	}

	public Cozinha salvar(Cozinha cozinha) {
		return cozinhaRepository.save(cozinha);
	}
	
	public void remover(Long cozinhaId) {
		try {			
			cozinhaRepository.deleteById(cozinhaId);
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
					String.format("A cozinha de código %d não pode ser deletada, pois está em uso.", cozinhaId));
		} catch (EmptyResultDataAccessException e) {
			throw new CozinhaNaoEncontradaException(cozinhaId);
		}
	}
}
