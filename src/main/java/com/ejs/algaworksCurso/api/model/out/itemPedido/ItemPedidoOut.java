package com.ejs.algaworksCurso.api.model.out.itemPedido;

import java.math.BigDecimal;

public class ItemPedidoOut {

	private Long produtoId;
    private String produtoNome;
    private Integer quantidade;
    private BigDecimal precoUnitario;
    private BigDecimal precoTotal;
    private String observacao;
    
	/**
	 * @return the produtoId
	 */
	public Long getProdutoId() {
		return produtoId;
	}
	/**
	 * @param produtoId the produtoId to set
	 */
	public void setProdutoId(Long produtoId) {
		this.produtoId = produtoId;
	}
	/**
	 * @return the produtoNome
	 */
	public String getProdutoNome() {
		return produtoNome;
	}
	/**
	 * @param produtoNome the produtoNome to set
	 */
	public void setProdutoNome(String produtoNome) {
		this.produtoNome = produtoNome;
	}
	/**
	 * @return the quantidade
	 */
	public Integer getQuantidade() {
		return quantidade;
	}
	/**
	 * @param quantidade the quantidade to set
	 */
	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
	/**
	 * @return the precoUnitario
	 */
	public BigDecimal getPrecoUnitario() {
		return precoUnitario;
	}
	/**
	 * @param precoUnitario the precoUnitario to set
	 */
	public void setPrecoUnitario(BigDecimal precoUnitario) {
		this.precoUnitario = precoUnitario;
	}
	/**
	 * @return the precoTotal
	 */
	public BigDecimal getPrecoTotal() {
		return precoTotal;
	}
	/**
	 * @param precoTotal the precoTotal to set
	 */
	public void setPrecoTotal(BigDecimal precoTotal) {
		this.precoTotal = precoTotal;
	}
	/**
	 * @return the observacao
	 */
	public String getObservacao() {
		return observacao;
	}
	/**
	 * @param observacao the observacao to set
	 */
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
    
    
}
