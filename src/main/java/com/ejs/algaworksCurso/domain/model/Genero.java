package com.ejs.algaworksCurso.domain.model;

import io.swagger.v3.oas.annotations.media.Schema;

public enum Genero {

	@Schema(example = "M")
	M("Masculino"),
	F("Feminino");
	
	private String descricao;
	
	private Genero(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return this.descricao;
	}
}
