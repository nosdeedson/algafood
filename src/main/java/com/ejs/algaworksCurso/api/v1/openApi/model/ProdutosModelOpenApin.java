package com.ejs.algaworksCurso.api.v1.openApi.model;

import java.util.List;

import org.springframework.hateoas.Links;

import com.ejs.algaworksCurso.api.v1.model.in.produto.ProdutoOut;

import io.swagger.annotations.ApiModel;

@ApiModel("produtos")
public class ProdutosModelOpenApin {

	private ProdutoEmbeddedOpenApi _embedded;
	private Links _links;
	
	
	/**
	 * @return the _embedded
	 */
	public ProdutoEmbeddedOpenApi get_embedded() {
		return _embedded;
	}


	/**
	 * @param _embedded the _embedded to set
	 */
	public void set_embedded(ProdutoEmbeddedOpenApi _embedded) {
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


	public class ProdutoEmbeddedOpenApi{
		private List<ProdutoOut> produtos;

		/**
		 * @return the produtos
		 */
		public List<ProdutoOut> getProdutos() {
			return produtos;
		}

		/**
		 * @param produtos the produtos to set
		 */
		public void setProdutos(List<ProdutoOut> produtos) {
			this.produtos = produtos;
		}
		
	}
}
