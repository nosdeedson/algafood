package com.ejs.algaworksCurso.domain.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class Grupo implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nome;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "grupo_permissao",
			joinColumns = @JoinColumn(name = "grupo_id"), foreignKey = @ForeignKey(name="fk_grupo_id"),
			inverseJoinColumns = @JoinColumn(name = "permissao_id"), inverseForeignKey = @ForeignKey(name = "fk_permissao_id"))
	private Set<Permissao> permissoes = new HashSet<Permissao>();
	
	public Grupo() {}
	
	public Grupo(Long id, String nome, Set<Permissao> permissoes) {
		this.id = id;
		this.nome = nome;
		this.permissoes = permissoes;
	}

	public void associar( Permissao permissao) {
		this.getPermissoes().add(permissao);
	}
	
	public void desassociar(Permissao permissao) {
		this.getPermissoes().remove(permissao);
	}
	
	/*
	 * Getters and setters
	 */
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Set<Permissao> getPermissoes() {
		return permissoes;
	}

	public void setPermissoes(Set<Permissao> permissoes) {
		this.permissoes = permissoes;
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
		Grupo other = (Grupo) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	
	
}
