package com.ejs.algaworksCurso.api.v1.model.in.pedido;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.ejs.algaworksCurso.api.v1.model.in.endereco.EnderecoIn;
import com.ejs.algaworksCurso.api.v1.model.in.formaPagamento.FormaPagamentoIdIn;
import com.ejs.algaworksCurso.api.v1.model.in.itensPedido.ItensPedidoIn;
import com.ejs.algaworksCurso.api.v1.model.in.restaurante.RestauranteIdIn;
import io.swagger.v3.oas.annotations.media.Schema;

public class PedidoIn {

	@Schema(example = "1", description = "restaurante di")
	@Valid
	@NotBlank
	private RestauranteIdIn restaurante;

	@Schema(example = "1", description = "forma de pagamento id")
	@Valid
	@NotBlank
	private FormaPagamentoIdIn formaPagamento;

	@Valid
	@NotNull
	private EnderecoIn enderecoEntrega;
	
	@NotNull
	@Size(min = 1)
	private List<ItensPedidoIn> itens = new ArrayList<>();

	@Schema(example = "1")
	@NotNull
	private Long usuarioId;

	/**
	 * @return the usuarioId
	 */
	public Long getUsuarioId() {
		return usuarioId;
	}

	/**
	 * @param usuarioId the usuarioId to set
	 */
	public void setUsuarioId(Long usuarioId) {
		this.usuarioId = usuarioId;
	}

	/**
	 * @return the restaurante
	 */
	public RestauranteIdIn getRestaurante() {
		return restaurante;
	}

	/**
	 * @param restaurante the restaurante to set
	 */
	public void setRestaurante(RestauranteIdIn restaurante) {
		this.restaurante = restaurante;
	}

	/**
	 * @return the formaPagamento
	 */
	public FormaPagamentoIdIn getFormaPagamento() {
		return formaPagamento;
	}

	/**
	 * @param formaPagamento the formaPagamento to set
	 */
	public void setFormaPagamento(FormaPagamentoIdIn formaPagamento) {
		this.formaPagamento = formaPagamento;
	}

	/**
	 * @return the enderecoEntrega
	 */
	public EnderecoIn getEnderecoEntrega() {
		return enderecoEntrega;
	}

	/**
	 * @param enderecoEntrega the enderecoEntrega to set
	 */
	public void setEnderecoEntrega(EnderecoIn enderecoEntrega) {
		this.enderecoEntrega = enderecoEntrega;
	}

	/**
	 * @return the itens
	 */
	public List<ItensPedidoIn> getItens() {
		return itens;
	}

	/**
	 * @param itens the itens to set
	 */
	public void setItens(List<ItensPedidoIn> itens) {
		this.itens = itens;
	}
	
	

}
