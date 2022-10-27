package com.ejs.algaworksCurso.api.v1.model.out.pedido;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import com.ejs.algaworksCurso.api.v1.model.out.endereco.EnderecoOut;
import com.ejs.algaworksCurso.api.v1.model.out.formaPagamento.FormaPagamentoOut;
import com.ejs.algaworksCurso.api.v1.model.out.itemPedido.ItemPedidoOut;
import com.ejs.algaworksCurso.api.v1.model.out.restautante.RestauranteOut;
import com.ejs.algaworksCurso.api.v1.model.out.usuario.UsuarioOut;

@Relation(collectionRelation = "pedidos")
public class PedidoOut extends RepresentationModel<PedidoOut> {

	@Schema(example = "cbc73cc8-d568-4ac2-9f5c-33fcaf864d5c")
	private String codigo;

	@Schema(example = "50")
    private BigDecimal subtotal;

	@Schema(example = "5")
    private BigDecimal taxaFrete;

	@Schema(example = "55")
	private BigDecimal valorTotal;

	@Schema(example = "Confirmado")
	private String status;

	@Schema(example = "2022-10-24T21:34:34.987654321")
	private OffsetDateTime dataCriacao;

	@Schema(example = "2022-10-24T21:34:34.987654321")
    private OffsetDateTime dataConfirmacao;

	@Schema(example = "2022-10-24T21:34:34.987654321")
    private OffsetDateTime dataEntrega;

	@Schema(example = "2022-10-24T21:34:34.987654321")
    private OffsetDateTime dataCancelamento;
    private RestauranteOut restaurante;
    private UsuarioOut cliente;
    private FormaPagamentoOut formaPagamento;
    private EnderecoOut enderecoEntrega;
    private List<ItemPedidoOut> itens;
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
	 * @return the subtotal
	 */
	public BigDecimal getSubtotal() {
		return subtotal;
	}
	/**
	 * @param subtotal the subtotal to set
	 */
	public void setSubtotal(BigDecimal subtotal) {
		this.subtotal = subtotal;
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
	 * @return the dataConfirmacao
	 */
	public OffsetDateTime getDataConfirmacao() {
		return dataConfirmacao;
	}
	/**
	 * @param dataConfirmacao the dataConfirmacao to set
	 */
	public void setDataConfirmacao(OffsetDateTime dataConfirmacao) {
		this.dataConfirmacao = dataConfirmacao;
	}
	/**
	 * @return the dataEntrega
	 */
	public OffsetDateTime getDataEntrega() {
		return dataEntrega;
	}
	/**
	 * @param dataEntrega the dataEntrega to set
	 */
	public void setDataEntrega(OffsetDateTime dataEntrega) {
		this.dataEntrega = dataEntrega;
	}
	/**
	 * @return the dataCancelamento
	 */
	public OffsetDateTime getDataCancelamento() {
		return dataCancelamento;
	}
	/**
	 * @param dataCancelamento the dataCancelamento to set
	 */
	public void setDataCancelamento(OffsetDateTime dataCancelamento) {
		this.dataCancelamento = dataCancelamento;
	}
	/**
	 * @return the restaurante
	 */
	public RestauranteOut getRestaurante() {
		return restaurante;
	}
	/**
	 * @param restaurante the restaurante to set
	 */
	public void setRestaurante(RestauranteOut restaurante) {
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
	/**
	 * @return the formaPagamento
	 */
	public FormaPagamentoOut getFormaPagamento() {
		return formaPagamento;
	}
	/**
	 * @param formaPagamento the formaPagamento to set
	 */
	public void setFormaPagamento(FormaPagamentoOut formaPagamento) {
		this.formaPagamento = formaPagamento;
	}
	/**
	 * @return the enderecoEntrega
	 */
	public EnderecoOut getEnderecoEntrega() {
		return enderecoEntrega;
	}
	/**
	 * @param enderecoEntrega the enderecoEntrega to set
	 */
	public void setEnderecoEntrega(EnderecoOut enderecoEntrega) {
		this.enderecoEntrega = enderecoEntrega;
	}
	/**
	 * @return the itens
	 */
	public List<ItemPedidoOut> getItens() {
		return itens;
	}
	/**
	 * @param itens the itens to set
	 */
	public void setItens(List<ItemPedidoOut> itens) {
		this.itens = itens;
	}   
    
    
}
