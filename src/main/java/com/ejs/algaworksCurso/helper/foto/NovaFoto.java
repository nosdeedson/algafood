package com.ejs.algaworksCurso.helper.foto;

import java.io.InputStream;

public class NovaFoto {
	
	private String nomeArquivo;
	private InputStream input;
	
	
	/**
	 * @return the nomeArquivo
	 */
	public String getNomeArquivo() {
		return nomeArquivo;
	}



	/**
	 * @param nomeArquivo the nomeArquivo to set
	 */
	public void setNomeArquivo(String nomeArquivo) {
		this.nomeArquivo = nomeArquivo;
	}



	/**
	 * @return the input
	 */
	public InputStream getInput() {
		return input;
	}



	/**
	 * @param input the input to set
	 */
	public void setInput(InputStream input) {
		this.input = input;
	}



	public static class Builder{
		private String nomeArquivo;
		private InputStream input;
		
		
		public Builder() {}
		
		public Builder nomeArquivo(String nomeArquivo) {
			this.nomeArquivo = nomeArquivo;
			return this;
		}
		
		public Builder input(InputStream input) {
			this.input = input;
			return this;
		}
		
		public NovaFoto build() {
			NovaFoto foto = new NovaFoto();
			foto.input = this.input;
			foto.nomeArquivo = this.nomeArquivo;
			return foto;
		}
		
		
	}

}
