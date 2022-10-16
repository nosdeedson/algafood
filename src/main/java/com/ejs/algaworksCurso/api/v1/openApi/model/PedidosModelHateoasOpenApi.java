package com.ejs.algaworksCurso.api.v1.openApi.model;

import java.util.List;

import org.springframework.hateoas.Links;

import com.ejs.algaworksCurso.api.v1.model.out.pedido.PedidoOut;

public class PedidosModelHateoasOpenApi {
	
	private PedidosEmbeddedOpenApi _embedded;
	private Links _links;
	private PageModel page;
	
	
	/**
	 * @return the _embedded
	 */
	public PedidosEmbeddedOpenApi get_embedded() {
		return _embedded;
	}


	/**
	 * @param _embedded the _embedded to set
	 */
	public void set_embedded(PedidosEmbeddedOpenApi _embedded) {
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


	/**
	 * @return the page
	 */
	public PageModel getPage() {
		return page;
	}


	/**
	 * @param page the page to set
	 */
	public void setPage(PageModel page) {
		this.page = page;
	}


	public class PedidosEmbeddedOpenApi{
		private List<PedidoOut> pedidos;

		/**
		 * @return the pedidos
		 */
		public List<PedidoOut> getPedidos() {
			return pedidos;
		}

		/**
		 * @param pedidos the pedidos to set
		 */
		public void setPedidos(List<PedidoOut> pedidos) {
			this.pedidos = pedidos;
		}
		
	}

}
