package com.ejs.algaworksCurso.api.v1.openApi.model;

import java.util.List;

import org.springframework.hateoas.Links;

import com.ejs.algaworksCurso.api.v1.model.out.formaPagamento.FormaPagamentoOut;

import io.swagger.annotations.ApiModel;

@ApiModel("formaspagamentoModel")
public class FormasPagamentoModelOpenApi {
	
	private FormasPagamentoEmbeddedOpenApin _embedded;
	private Links _links;
	

	/**
	 * @return the _embedded
	 */
	public FormasPagamentoEmbeddedOpenApin get_embedded() {
		return _embedded;
	}


	/**
	 * @param _embedded the _embedded to set
	 */
	public void set_embedded(FormasPagamentoEmbeddedOpenApin _embedded) {
		this._embedded = _embedded;
	}


	/**
	 * @return the _links
	 */
	public Links get_links() {
		return _links;
	}


	/**
	 * @param _links the _links to set
	 */
	public void set_links(Links _links) {
		this._links = _links;
	}

	@ApiModel("FormasPagamentoEmbeddedModel")
	public class FormasPagamentoEmbeddedOpenApin{
		private List<FormaPagamentoOut> formasPagamento;

		/**
		 * @return the formasPagamento
		 */
		public List<FormaPagamentoOut> getFormasPagamento() {
			return formasPagamento;
		}

		/**
		 * @param formasPagamento the formasPagamento to set
		 */
		public void setFormasPagamento(List<FormaPagamentoOut> formasPagamento) {
			this.formasPagamento = formasPagamento;
		}
		
	}
}
