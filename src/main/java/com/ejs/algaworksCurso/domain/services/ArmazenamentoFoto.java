package com.ejs.algaworksCurso.domain.services;

import java.util.UUID;

import com.ejs.algaworksCurso.helper.foto.NovaFoto;

public interface ArmazenamentoFoto {
	
	
	default String novoNomeFoto(String novoNome) {
		return UUID.randomUUID().toString() + "_" + novoNome;
	}
	
	void armazenar(NovaFoto novaFoto);

}
