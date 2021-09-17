package com.ejs.algaworksCurso.infrastructure.armazenamentoFoto;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FileCopyUtils;

import com.ejs.algaworksCurso.api.v1.core.storage.ArmazenamentoProperties;
import com.ejs.algaworksCurso.domain.services.ArmazenamentoFoto;
import com.ejs.algaworksCurso.helper.foto.FotoRecuperada;
import com.ejs.algaworksCurso.helper.foto.NovaFoto;

public class ArmazenaFotoLocal implements ArmazenamentoFoto{

//	@Value("${algafood.armazenamento.local.diretorio-fotos}")
//	private String diretorioFotos;
	
	@Autowired
	private ArmazenamentoProperties storageProperties;
	
	@Override
	public void armazenar(NovaFoto novaFoto) {
		Path caminho = Path.of(storageProperties.getLocal().getDiretorioFotos(), novaFoto.getNomeArquivo()) ;
		
		try {
			FileCopyUtils.copy(novaFoto.getInput(), Files.newOutputStream(caminho));
		} catch (IOException e) {
			throw new ArmazenamentoException("Falha ao salvar o arquivo", e);
		}
		
	}

	@Override
	public void remover(String nomeArquivoAntigo) {
		try {
			Path caminho = Path.of(storageProperties.getLocal().getDiretorioFotos(), nomeArquivoAntigo);
			Files.deleteIfExists(caminho);
		} catch (Exception e) {
			throw new ArmazenamentoException("Não foi possível excluir o arquivo antigo.", e);
		}
		
	}

	@Override
	public FotoRecuperada recuperar(String nomeArquivo) {
		Path caminho = Path.of(storageProperties.getLocal().getDiretorioFotos(), nomeArquivo);
		FotoRecuperada fotoRecuperada;
		try {
			InputStream input = Files.newInputStream(caminho);
			fotoRecuperada = new FotoRecuperada.Builder()
					.input(input).build();
		} catch (IOException e) {
			throw new ArmazenamentoException("Não foi possível ler o arquivo");
		}
		return fotoRecuperada;
	}

}
