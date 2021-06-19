package com.ejs.algaworksCurso.api.model.mixin;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.ejs.algaworksCurso.domain.model.Cozinha;
import com.ejs.algaworksCurso.domain.model.Endereco;
import com.ejs.algaworksCurso.domain.model.Produto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Component
public abstract class RestauranteMixin {
	/**
	 * Esta classe tem como intuito tirar as anotações do jackson da model de domain
	 * deixando a classe de domínio mais limpa
	 */
	
	@JsonIgnore
	OffsetDateTime dataCadastro;
	
	@JsonIgnore
	LocalDateTime dataAtualizacao;
	
//	@JsonIgnoreProperties("hibernateLazyInitializer") esta propriedade é criada pelo JPA quando se usa o FetchType.LAZY
	@JsonIgnoreProperties(allowGetters = true, value = {"nome"})
	private Cozinha cozinha;
	
	@JsonIgnore
	private Endereco endereco;
	
	@JsonIgnore
	private List<Produto> produtos = new ArrayList<Produto>();

}
