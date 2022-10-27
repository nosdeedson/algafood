package com.ejs.algaworksCurso.helper.foto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.InputStream;

public class FotoRecuperada {

	@Schema(description = "arquivo em bytes")
	private InputStream inputStream;

	@Schema(description = "url da imagem armazenada no S3 da Amazon")
	private String url;

	@Schema(example = "true")
	public boolean temInputStream() {
		return inputStream != null;
	}

	@Schema(example = "true")
	public boolean temUrl() {
		return url != null;
	}
	
	/**
	 * @return the inputStream
	 */
	public InputStream getInputStream() {
		return inputStream;
	}
	/**
	 * @param inputStream the inputStream to set
	 */
	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}
	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}
	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	
	public static class Builder{
		private InputStream inputStream;
		private String url;
		
		public Builder(){}
		
		public Builder input(InputStream input) {
			this.inputStream = input;
			return this;
		}
		public Builder url(String url) {
			this.url = url;
			return this;
		}
		
		public FotoRecuperada build() {
			FotoRecuperada fotoRecuperada = new FotoRecuperada();
			fotoRecuperada.inputStream = this.inputStream;
			fotoRecuperada.url = this.url;
			return fotoRecuperada;
		}
	}

}
