package com.ejs.algaworksCurso.api.v1.model.out.usuario;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import com.ejs.algaworksCurso.api.v1.model.out.group.GrupoOut;

@Relation(collectionRelation = "usuarios")
public class UsuarioOut extends RepresentationModel<UsuarioOut>{

	@Schema(example = "1")
	private Long id;

	@Schema(example = "edson@gmail.com")
	private String email;

	@Schema(example = "Edson Jose de Souza")
	private String nome;

	private List<GrupoOut> grupos;
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}
	/**
	 * @param nome the nome to set
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}
	/**
	 * @return the grupos
	 */
	public List<GrupoOut> getGrupos() {
		return grupos;
	}
	/**
	 * @param grupos the grupos to set
	 */
	public void setGrupos(List<GrupoOut> grupos) {
		this.grupos = grupos;
	}
	
}
