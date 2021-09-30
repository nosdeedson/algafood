package com.ejs.algaworksCurso.infrastructure.armazenamentoFoto;

import java.net.URL;

import org.springframework.beans.factory.annotation.Autowired;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.ejs.algaworksCurso.api.v1.core.storage.ArmazenamentoProperties;
import com.ejs.algaworksCurso.domain.services.ArmazenamentoFoto;
import com.ejs.algaworksCurso.helper.foto.FotoRecuperada;
import com.ejs.algaworksCurso.helper.foto.NovaFoto;


public class ArmazenamentoFotoS3 implements ArmazenamentoFoto {
	
	@Autowired
	private ArmazenamentoProperties storageProperties;

	
	@Autowired
	private AmazonS3 amazonS3;
	
	@Override
	public void armazenar(NovaFoto novaFoto) {
		
		try {
			String caminho = this.getCaminhoArquivo( novaFoto.getNomeArquivo());
			ObjectMetadata objectMetadata = new ObjectMetadata();
			objectMetadata.setContentType(novaFoto.getContentType());
			
			PutObjectRequest putObjectRequest = new PutObjectRequest(
					storageProperties.getS3().getBucketName(),
					caminho, novaFoto.getInput(),
					objectMetadata)
					.withCannedAcl(CannedAccessControlList.PublicRead);
			amazonS3.putObject(putObjectRequest);
		} catch (Exception e) {
			throw new ArmazenamentoException("Não foi possível armazenar a imagem na Amazon", e);
		}
	}


	@Override
	public FotoRecuperada recuperar(String nomeArquivo) {
		String caminho = this.getCaminhoArquivo( nomeArquivo);
		
		URL url =  amazonS3.getUrl(storageProperties.getS3().getBucketName(), caminho);
		
		FotoRecuperada fotoRecuperada = new FotoRecuperada.Builder()
				.url(url.toString()).build();
		
		return fotoRecuperada;
	}	

	@Override
	public void remover(String nomeArquivoAntigo) {
		String caminho = this.getCaminhoArquivo( nomeArquivoAntigo);
		DeleteObjectRequest deleteObjectRequest = new DeleteObjectRequest(storageProperties.getS3().getBucketName(), caminho);
		amazonS3.deleteObject(deleteObjectRequest);
	}

	private String getCaminhoArquivo(String nomeArquivo) {
		return String.format("%s/%s", storageProperties.getS3().getDiretorioFotos(), nomeArquivo);
	}
}
