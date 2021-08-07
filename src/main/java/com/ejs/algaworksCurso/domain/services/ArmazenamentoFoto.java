package com.ejs.algaworksCurso.domain.services;

import java.util.UUID;

import com.ejs.algaworksCurso.helper.foto.FotoRecuperada;
import com.ejs.algaworksCurso.helper.foto.NovaFoto;

public interface ArmazenamentoFoto {
	
	
	default String novoNomeFoto(String novoNome) {
		return UUID.randomUUID().toString() + "_" + novoNome;
	}
	
	void armazenar(NovaFoto novaFoto);
	
	FotoRecuperada recuperar(String nomeArquivo);
	
	void remover( String nomeArquivoAntigo);
	
	default void substituir( String nomeArquivoAntigo, NovaFoto novaFoto) {
		if ( nomeArquivoAntigo != null) {
			this.remover(nomeArquivoAntigo);
		}
		this.armazenar(novaFoto);
	}

}
