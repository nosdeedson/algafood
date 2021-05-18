package com.ejs.algaworksCurso.infrastructure.repository.spec;

import org.springframework.data.jpa.domain.Specification;

import com.ejs.algaworksCurso.domain.model.Restaurante;

public class RestauranteSpecs {
	
	public static Specification<Restaurante> comFreteGratis(){
		return new RestauranteComFreteGratisSpec();
	}
	
	public static Specification<Restaurante> comNomeSemelhante(String nome){
		return new RestauranteComNomeSemelhanteSpec(nome);
	}

}
