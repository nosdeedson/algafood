package com.ejs.algaworksCurso.infrastructure.armazenamentoFoto;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;

import com.ejs.algaworksCurso.domain.services.ArmazenamentoFoto;
import com.ejs.algaworksCurso.helper.foto.NovaFoto;

@Component
public class ArmazenaFotoLocal implements ArmazenamentoFoto{

	@Value("${algafood.armazenamento.local.diretorio-fotos}")
	private String diretorioFotos;
	
	@Override
	public void armazenar(NovaFoto novaFoto) {
		Path caminho = Path.of(diretorioFotos, novaFoto.getNomeArquivo()) ;
		
		try {
			FileCopyUtils.copy(novaFoto.getInput(), Files.newOutputStream(caminho));
		} catch (IOException e) {
			throw new ArmazenamentoException("Falha ao salvar o arquivo", e);
		}
		
	}

}
