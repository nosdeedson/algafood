package com.ejs.algaworksCurso.api.v2.model.openApi;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.ejs.algaworksCurso.api.v2.model.in.cidade.CidadeInV2;
import com.ejs.algaworksCurso.api.v2.model.out.cidade.CidadeOutV2;

import io.swagger.annotations.Api;

@Api(tags = "Cidades V2")
public interface CidadeControllerOpenApiV2 {
	
	ResponseEntity<CidadeOutV2> atualizar(Long id, CidadeInV2 cidadeIn);

	ResponseEntity<CidadeOutV2> buscar(Long id);

	List<CidadeOutV2> listar();

	void remover(Long id);

	ResponseEntity<CidadeOutV2> salvar(CidadeInV2 cidadeIn);

}