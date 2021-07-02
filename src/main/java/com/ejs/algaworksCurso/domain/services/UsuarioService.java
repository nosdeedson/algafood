package com.ejs.algaworksCurso.domain.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ejs.algaworksCurso.api.model.dto.in.senha.SenhaAtualizarIn;
import com.ejs.algaworksCurso.api.model.dto.in.usuario.UsuarioAtualizarIn;
import com.ejs.algaworksCurso.api.model.dto.in.usuario.UsuarioIn;
import com.ejs.algaworksCurso.api.model.dto.out.usuario.UsuarioOut;
import com.ejs.algaworksCurso.domain.exception.EntidadeEmUsoException;
import com.ejs.algaworksCurso.domain.exception.SenhaNaoEncontradoException;
import com.ejs.algaworksCurso.domain.exception.UsuarioNaoEncontradoException;
import com.ejs.algaworksCurso.domain.model.Usuario;
import com.ejs.algaworksCurso.domain.repository.UsuarioRepository;
import com.ejs.algaworksCurso.helper.usuario.UsuarioAssembler;
import com.ejs.algaworksCurso.helper.usuario.UsuarioDisAssembler;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private UsuarioAssembler usuarioAssembler;
	
	@Autowired
	private UsuarioDisAssembler usuarioDisAssembler;
	
	@Transactional
	public UsuarioOut atualizar(UsuarioAtualizarIn usuarioAtualizarIn, Long usuarioId) {
		Usuario user = this.buscarOuFalhar(usuarioId);
		usuarioAssembler.usuarioAtualizarInToUsuario(usuarioAtualizarIn, user);
		user = this.usuarioRepository.save(user);
		return this.usuarioDisAssembler.usuarioToUsuarioOut(user);
	}
	
	@Transactional
	public void atualizarSenha(SenhaAtualizarIn senhaIn, Long usuarioId) {
		Usuario user = this.buscarOuFalhar(usuarioId);
		
		this.usuarioRepository.findSenhaByIdAndSenha(usuarioId, senhaIn.getSenhaAtual())
			.orElseThrow( () -> new SenhaNaoEncontradoException(
					String.format("Esta senha %s não corresponde a sua senha atual.", senhaIn.getSenhaAtual()) ));
		user.setSenha(senhaIn.getNovaSenha());
		
		this.usuarioRepository.save(user);
		
		
	}
	
	public UsuarioOut buscar(Long usuarioId) {
		Usuario user = this.buscarOuFalhar(usuarioId);
		return this.usuarioDisAssembler.usuarioToUsuarioOut(user);
	}
	
	public List<UsuarioOut> listar(){
		List<Usuario> users = this.usuarioRepository.findAll();
		return users.stream()
				.map(user -> this.usuarioDisAssembler.usuarioToUsuarioOut(user))
				.collect(Collectors.toList());
	}
	
	@Transactional
	public void remover( Long usuarioId) {
		try {
			this.usuarioRepository.deleteById(usuarioId);
		} catch (DataIntegrityViolationException  e) {
			throw new EntidadeEmUsoException(String.format("Usuário de código %d não pode ser excluído"
					+ ", pois está em uso.", usuarioId));
		} catch(EmptyResultDataAccessException e) {
			throw new UsuarioNaoEncontradoException(usuarioId);
		}
	}
	
	@Transactional
	public UsuarioOut salvar( UsuarioIn usuarioIn) {
		Usuario user = this.usuarioAssembler.usuarioInToUsuario(usuarioIn);
		user = this.usuarioRepository.save(user);
		return this.usuarioDisAssembler.usuarioToUsuarioOut(user);
	}

	/*
	 * Metodos auxiliares
	 */
	
	public Usuario buscarOuFalhar( Long UsuarioId) {
		return this.usuarioRepository.findById(UsuarioId)
				.orElseThrow( () -> new UsuarioNaoEncontradoException(UsuarioId));
	}

}
