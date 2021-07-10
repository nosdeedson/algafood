package com.ejs.algaworksCurso.domain.repository;

import java.util.Optional;

import org.springframework.data.repository.query.Param;

import com.ejs.algaworksCurso.domain.model.Usuario;

public interface UsuarioRepository extends CustomJpaRepository<Usuario, Long> {

	Optional<String> findSenhaByIdAndSenha(@Param("usuarioId") Long usuarioId, @Param("senha") String senha);
	
	Optional<Usuario> findByEmail(String email);
}
