package com.ejs.algaworksCurso.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ejs.algaworksCurso.domain.model.Permissao;

@Repository
public interface PermissaoRepository extends JpaRepository<Permissao, Long> {
		
	@Query(value = "SELECT per.* FROM permissao per "
			+ " WHERE per.id NOT IN ("
			+ " SELECT DISTINCT p.id FROM grupo g "
			+ " INNER JOIN grupo_permissao gp "
			+ " ON g.id = gp.grupo_id "
			+ " INNER JOIN permissao p "
			+ " ON p.id = gp.permissao_id "
			+ " WHERE g.id = :grupoId OR p.id IS NULL )"
			+ " ORDER BY per.nome ASC", nativeQuery = true)
	List<Permissao> findPermissaoSemVinculoGrupo(@Param("grupoId") Long grupoId);
}
