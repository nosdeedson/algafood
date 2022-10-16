package com.ejs.algaworksCurso.api.v1.model.in.grupo;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

public class GrupoPermissoesIn {

	@NotBlank
	private String nome;
	
	@NotEmpty
	private List<Long> permissoes;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Long> getPermissoes() {
		return permissoes;
	}

	public void setPermissoes(List<Long> permissoes) {
		this.permissoes = permissoes;
	}
	
}
