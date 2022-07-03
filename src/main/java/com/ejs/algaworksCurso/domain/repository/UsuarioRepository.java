package com.ejs.algaworksCurso.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ejs.algaworksCurso.domain.model.Usuario;

public interface UsuarioRepository extends CustomJpaRepository<Usuario, Long> {

	Optional<String> findSenhaByIdAndSenha(@Param("usuarioId") Long usuarioId, @Param("senha") String senha);
	
	Optional<Usuario> findByEmail(String email);
	
	@Query("SELECT CASE WHEN COUNT(1) > 0 THEN TRUE ELSE FALSE END "
			+ " FROM Restaurante rest JOIN rest.responsaveis resp "
			+ " WHERE rest.id = :restauranteId AND resp.email = :emailUsuario")
	boolean existsResponsavel(@Param("emailUsuario") String emailUsuario, @Param("restauranteId") Long restauranteId);

	@Query("SELECT u FROM Usuario u INNER JOIN u.grupos g WHERE g.id= :grupoId")
	List<Usuario> findUsuariosPorGrupoId(@Param("grupoId") Long grupoId);
	
	@Query( value = "SELECT DISTINCT u.* FROM usuario u "
			+ " LEFT OUTER JOIN usuario_grupo ug "
			+ " ON u.id = ug.usuario_id "
			+ " LEFT OUTER JOIN grupo g"
			+ " ON g.id = ug.grupo_id"
			+ " WHERE g.id <> :grupoId OR g.id IS NULL "
			+ " ORDER BY u.nome ASC", nativeQuery = true)
	List<Usuario> findUsuariosNãoVinculadosAoGrupo(@Param("grupoId") Long grupoId);
}
