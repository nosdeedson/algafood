package com.ejs.algaworksCurso.api.model.out.pedido;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import com.ejs.algaworksCurso.api.model.out.restautante.RestauranteResumidoDTO;
import com.ejs.algaworksCurso.api.model.out.usuario.UsuarioOut;

import io.swagger.annotations.ApiModel;

//@JsonFilter("pedidosFilter") usar com a getmapping 2
@Relation(collectionRelation = "pedidos")
@ApiModel("PedidoResumido")
public class PedidoResumidoDTO extends RepresentationModel<PedidoResumidoDTO> {

	private String codigo;
	private BigDecimal subTotal;
	private BigDecimal taxaFrete;
	private BigDecimal valorTotal;
	private String status;
	private OffsetDateTime dataCriacao;
	private RestauranteResumidoDTO restaurante;
	private UsuarioOut cliente;
	
	/**
	 * @return the codigo
	 */
	public String getCodigo() {
		return codigo;
	}
	/**
	 * @param id the codigo to set
	 */
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	/**
	 * @return the subTotal
	 */
	public BigDecimal getSubTotal() {
		return subTotal;
	}
	/**
	 * @param subTotal the subTotal to set
	 */
	public void setSubTotal(BigDecimal subTotal) {
		this.subTotal = subTotal;
	}
	/**
	 * @return the taxaFrete
	 */
	public BigDecimal getTaxaFrete() {
		return taxaFrete;
	}
	/**
	 * @param taxaFrete the taxaFrete to set
	 */
	public void setTaxaFrete(BigDecimal taxaFrete) {
		this.taxaFrete = taxaFrete;
	}
	/**
	 * @return the valorTotal
	 */
	public BigDecimal getValorTotal() {
		return valorTotal;
	}
	/**
	 * @param valorTotal the valorTotal to set
	 */
	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}
	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * @return the dataCriacao
	 */
	public OffsetDateTime getDataCriacao() {
		return dataCriacao;
	}
	/**
	 * @param dataCriacao the dataCriacao to set
	 */
	public void setDataCriacao(OffsetDateTime dataCriacao) {
		this.dataCriacao = dataCriacao;
	}
	/**
	 * @return the restaurante
	 */
	public RestauranteResumidoDTO getRestaurante() {
		return restaurante;
	}
	/**
	 * @param restaurante the restaurante to set
	 */
	public void setRestaurante(RestauranteResumidoDTO restaurante) {
		this.restaurante = restaurante;
	}
	/**
	 * @return the cliente
	 */
	public UsuarioOut getCliente() {
		return cliente;
	}
	/**
	 * @param cliente the cliente to set
	 */
	public void setCliente(UsuarioOut cliente) {
		this.cliente = cliente;
	}
	
	
}
