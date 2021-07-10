package com.ejs.algaworksCurso.domain.model;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import org.hibernate.annotations.CreationTimestamp;

@Entity
public class Usuario implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@CreationTimestamp
	private OffsetDateTime dataCadastro;
	
	private String email;
	private String nome;
	private String senha;
	
	@ManyToMany(targetEntity = Grupo.class)
	@JoinTable(name = "usuario_grupo",
			joinColumns = @JoinColumn(name = "usuario_id"), foreignKey = @ForeignKey(name = "fk_usuario_id"),
			inverseJoinColumns = @JoinColumn(name = "grupo_id"), inverseForeignKey = @ForeignKey(name = "fk_grupo"))
	private Set<Grupo> grupos = new HashSet<Grupo>();
	
	public Usuario() {}
	
	public Usuario(Long id, OffsetDateTime dataCadastro, String email, String nome, String senha) {
		this.id = id;
		this.dataCadastro = dataCadastro;
		this.email = email;
		this.nome = nome;
		this.senha = senha;
	}
	
	public void associarGrupo(Grupo grupo) {
		this.getGrupos().add(grupo);
	}
	
	public void desassociarGrupo(Grupo grupo) {
		this.getGrupos().remove(grupo);
	}
	
	/*
	 * Getters and Setters
	 */

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public OffsetDateTime getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(OffsetDateTime dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Set<Grupo> getGrupos() {
		return grupos;
	}

	public void setGrupos(Set<Grupo> grupos) {
		this.grupos = grupos;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
}
