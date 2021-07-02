package com.ejs.algaworksCurso.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.ejs.algaworksCurso.domain.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	Optional<String> findSenhaByIdAndSenha(@Param("usuarioId") Long usuarioId, @Param("senha") String senha);
}
