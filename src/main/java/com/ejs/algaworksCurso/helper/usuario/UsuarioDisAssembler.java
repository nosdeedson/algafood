package com.ejs.algaworksCurso.helper.usuario;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ejs.algaworksCurso.api.model.out.usuario.UsuarioOut;
import com.ejs.algaworksCurso.domain.model.Usuario;

@Component
public class UsuarioDisAssembler {

	@Autowired
	private ModelMapper mapper;
	
	public UsuarioOut usuarioToUsuarioOut(Usuario usuario) {
		return this.mapper.map(usuario, UsuarioOut.class);
	}
}
