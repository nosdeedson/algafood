package com.ejs.algaworksCurso.infrastructure.armazenamentoFoto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.ejs.algaworksCurso.api.core.storage.ArmazenamentoProperties;
import com.ejs.algaworksCurso.api.core.storage.ArmazenamentoProperties.TipoArmazenamento;
import com.ejs.algaworksCurso.domain.services.ArmazenamentoFoto;

@Configuration
public class ArmazenamentoConfig {
	
	@Autowired
	private ArmazenamentoProperties storageProperties;

	@Bean
	@ConditionalOnProperty(name = "algafood.armazenamento.tipo", havingValue = "s3")
	public AmazonS3 amazonS3() {
		
		var credencials = new BasicAWSCredentials(
				storageProperties.getS3().getIdChaveAcesso(), 
				storageProperties.getS3().getChaveAcessoSecreta());
		
		return AmazonS3ClientBuilder
				.standard()
				.withCredentials(new AWSStaticCredentialsProvider(credencials))
				.withRegion(storageProperties.getS3().getRegiao())
				.build();
	}
	
	@Bean
	public ArmazenamentoFoto getTipoArmazenamentoFoto() {
		if ( TipoArmazenamento.S3.equals(storageProperties.getTipo())) {
			return new ArmazenamentoFotoS3();
		}else {
			return new ArmazenaFotoLocal();
		}
	}
}
