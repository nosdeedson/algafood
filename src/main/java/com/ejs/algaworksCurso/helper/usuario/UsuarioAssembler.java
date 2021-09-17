package com.ejs.algaworksCurso.helper.usuario;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ejs.algaworksCurso.api.v1.model.in.usuario.UsuarioAtualizarIn;
import com.ejs.algaworksCurso.api.v1.model.in.usuario.UsuarioIn;
import com.ejs.algaworksCurso.domain.model.Usuario;

@Component
public class UsuarioAssembler {
	
	@Autowired
	private ModelMapper mapper;
	
	public Usuario usuarioInToUsuario(UsuarioIn usuarioIn) {
		return this.mapper.map(usuarioIn, Usuario.class);
	}
	
	public void usuarioInToUsuario(UsuarioIn usuarioIn, Usuario usuario ) {
		this.mapper.map(usuarioIn, usuario);
	}
	
	public void usuarioAtualizarInToUsuario(UsuarioAtualizarIn usuarioAtualizarIn, Usuario usuario) {
		this.mapper.map(usuarioAtualizarIn, usuario);
	}

}
