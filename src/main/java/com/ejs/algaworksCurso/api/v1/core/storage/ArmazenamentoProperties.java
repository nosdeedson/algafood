package com.ejs.algaworksCurso.api.v1.core.storage;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import com.amazonaws.regions.Regions;

@Component
@ConfigurationProperties("algafood.armazenamento")
public class ArmazenamentoProperties {
	
	private Local local = new Local();
	private S3 s3 = new S3();
	private TipoArmazenamento tipo = TipoArmazenamento.LOCAL;
	
	public enum TipoArmazenamento{
		LOCAL, S3;
	}
	
	public TipoArmazenamento getTipo() {
		return tipo;
	}
	
	public void setTipo(TipoArmazenamento tipo) {
		this.tipo = tipo;
	}

	public S3 getS3() {
		return s3;
	}

	public void setS3(S3 s3) {
		this.s3 = s3;
	}

	public Local getLocal() {
		return local;
	}

	public void setLocal(Local local) {
		this.local = local;
	}
	
	public class Local{
		private String diretorioFotos;

		public String getDiretorioFotos() {
			return diretorioFotos;
		}

		public void setDiretorioFotos(String diretorioFotos) {
			this.diretorioFotos = diretorioFotos;
		}
				
	}
	
	public class S3{
		private String idChaveAcesso;
		private String chaveAcessoSecreta;
		private String bucketName;
		private Regions regiao;
		private String diretorioFotos;
		public String getIdChaveAcesso() {
			return idChaveAcesso;
		}
		public void setIdChaveAcesso(String idChaveAcesso) {
			this.idChaveAcesso = idChaveAcesso;
		}
		public String getChaveAcessoSecreta() {
			return chaveAcessoSecreta;
		}
		public void setChaveAcessoSecreta(String chaveAcessoSecreta) {
			this.chaveAcessoSecreta = chaveAcessoSecreta;
		}
		public String getBucketName() {
			return bucketName;
		}
		public void setBucketName(String bucketName) {
			this.bucketName = bucketName;
		}
		public Regions getRegiao() {
			return regiao;
		}
		public void setRegiao(Regions regiao) {
			this.regiao = regiao;
		}
		public String getDiretorioFotos() {
			return diretorioFotos;
		}
		public void setDiretorioFotos(String diretorioFotos) {
			this.diretorioFotos = diretorioFotos;
		}
		
	}
	
}
