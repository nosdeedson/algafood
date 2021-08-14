package com.ejs.algaworksCurso.infrastructure.email;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.map.HashedMap;

public class Mensagem {

	private Set<String> destinatarios = new HashSet<>();
	private String assunto;
	private String corpo;
	@SuppressWarnings("unchecked")
	private Map<String, Object> variaveis =  new HashedMap();
	private String tipoSmtp;

	public Set<String> getDestinatarios() {
		return destinatarios;
	}

	public void setDestinatarios(Set<String> destinatarios) {
		this.destinatarios = destinatarios;
	}

	public String getAssunto() {
		return assunto;
	}

	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}

	public String getCorpo() {
		return corpo;
	}

	public void setCorpo(String corpo) {
		this.corpo = corpo;
	}

	public Map<String, Object> getVariaveis() {
		return variaveis;
	}

	public void setVariaveis(Map<String, Object> variaveis) {
		this.variaveis = variaveis;
	}

	public String getTipoSmtp() {
		return tipoSmtp;
	}

	public void setTipoSmtp(String tipoSmtp) {
		this.tipoSmtp = tipoSmtp;
	}

	public static class Builder {
		private Set<String> destinatarios = new HashSet<String>();
		private String assunto;
		private String corpo;
		@SuppressWarnings("unchecked")
		private Map<String, Object> variaveis =  new HashedMap();
		private String tipoSmtp;
		
		public Builder() {
		}

		public Builder destinatarios(String destinatario) {
			this.destinatarios.add(destinatario);
			return this;
		}

		public Builder assunto(String assunto) {
			this.assunto = assunto;
			return this;
		}

		public Builder corpo(String corpo) {
			this.corpo = corpo;
			return this;
		}
		
		public Builder variaveis(String chave, Object valor) {
			this.variaveis.put(chave, valor);
			return this;
		}
		
		public Builder tipoSmtp(String tipoSmtp) {
			this.tipoSmtp = tipoSmtp;
			return this;
		}

		public Mensagem build() {
			Mensagem mensagem = new Mensagem();
			mensagem.assunto = this.assunto;
			mensagem.corpo = this.corpo;
			mensagem.destinatarios = this.destinatarios;
			mensagem.variaveis  = this.variaveis;
			mensagem.tipoSmtp = this.tipoSmtp;
			return mensagem;
		}
	}

}
