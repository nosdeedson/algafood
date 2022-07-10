package com.ejs.algaworksCurso.api.v1.model.in.grupo;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import io.swagger.annotations.ApiModelProperty;

public class GrupoPermissoesIn {

	@ApiModelProperty(example = "Gerencia", value = "Nome de Grupo para acesso a aplicação")
	@NotBlank
	private String nome;
	
	@ApiModelProperty(example = " 1, 2, 3", value = "Ids de permissões já cadastradas")
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
