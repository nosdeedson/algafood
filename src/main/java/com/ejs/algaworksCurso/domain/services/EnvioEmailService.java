package com.ejs.algaworksCurso.domain.services;

import java.util.Set;

public interface EnvioEmailService {
	
	void enviar( Menssagem menssagem);
	
	class Menssagem{
		private Set<String> destinatarios;
		private String assunto;
		private String corpo;
		
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
		
		class Builder{
			private Set<String> destinatarios;
			private String assunto;
			private String corpo;
			
			public Builder() {}
			
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
			
			public Menssagem build() {
				Menssagem menssagem = new Menssagem();
				menssagem.assunto = this.assunto;
				menssagem.corpo = this.corpo;
				menssagem.destinatarios = this.destinatarios;
				return menssagem;
			}
		}
		
	}

}
