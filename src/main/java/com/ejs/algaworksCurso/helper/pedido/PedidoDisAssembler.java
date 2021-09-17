package com.ejs.algaworksCurso.helper.pedido;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.TemplateVariable;
import org.springframework.hateoas.TemplateVariable.VariableType;
import org.springframework.hateoas.TemplateVariables;
import org.springframework.hateoas.UriTemplate;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import com.ejs.algaworksCurso.api.v1.controller.CidadeController;
import com.ejs.algaworksCurso.api.v1.controller.CozinhaController;
import com.ejs.algaworksCurso.api.v1.controller.EstadoController;
import com.ejs.algaworksCurso.api.v1.controller.FormaPagamentoController;
import com.ejs.algaworksCurso.api.v1.controller.GrupoController;
import com.ejs.algaworksCurso.api.v1.controller.PedidoController;
import com.ejs.algaworksCurso.api.v1.controller.RestauranteController;
import com.ejs.algaworksCurso.api.v1.controller.RestauranteProdutoController;
import com.ejs.algaworksCurso.api.v1.controller.UsuarioController;
import com.ejs.algaworksCurso.api.v1.model.out.pedido.PedidoOut;
import com.ejs.algaworksCurso.domain.model.Pedido;

@Component
public class PedidoDisAssembler extends RepresentationModelAssemblerSupport<Pedido, PedidoOut>{

	public PedidoDisAssembler() {
		super(PedidoController.class, PedidoOut.class);
	}

	@Autowired
	private ModelMapper mapper;
	

	@Override
	public PedidoOut toModel(Pedido entity) {
		PedidoOut out = createModelWithId(entity.getId(), entity);
		mapper.map(entity, out);
		
		String urlPedidos = WebMvcLinkBuilder.linkTo(PedidoController.class).withRel("pedidos").toUri().toString();
		
		if ( entity.podeConfirmar()) {
			out.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(PedidoController.class)
					.confirmarPedido(out.getCodigo())).withRel("confirmar"));
		}
		if ( entity.podeCancelar()) {
			out.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(PedidoController.class)
					.cancelarPedido(out.getCodigo())).withRel("cancelar"));
		}
		if ( entity.podeEntregar()) {
			out.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(PedidoController.class)
					.entregarPedido(out.getCodigo())).withRel("entregar"));
		}
		
		TemplateVariables templateVariables = new TemplateVariables(
				 new TemplateVariable("page", VariableType.REQUEST_PARAM),
				 new TemplateVariable("size", VariableType.REQUEST_PARAM),
				 new TemplateVariable("sort", VariableType.REQUEST_PARAM));
		
		TemplateVariables templateFiltro = new TemplateVariables(
				 new TemplateVariable("clienteId", VariableType.REQUEST_PARAM),
				 new TemplateVariable("restauranteId", VariableType.REQUEST_PARAM),
				 new TemplateVariable("dataCriacaoInicio", VariableType.REQUEST_PARAM),
				 new TemplateVariable("dataCriacaoFim", VariableType.REQUEST_PARAM));
				
		out.add(Link.of(UriTemplate.of(urlPedidos, templateVariables.concat(templateFiltro)), "pedidos"));
		
		out.getRestaurante().add(WebMvcLinkBuilder.linkTo(
				WebMvcLinkBuilder.methodOn(RestauranteController.class).buscar(out.getRestaurante().getId())).withSelfRel());
		
		out.getRestaurante().getCozinha().add(WebMvcLinkBuilder.linkTo(
				WebMvcLinkBuilder.methodOn(CozinhaController.class).buscar(out.getRestaurante().getCozinha().getId())).withSelfRel());
		
		out.getCliente().add(WebMvcLinkBuilder.linkTo(
				WebMvcLinkBuilder.methodOn(UsuarioController.class).buscar(out.getCliente().getId())).withSelfRel());
		
		out.getCliente().getGrupos().stream().forEach(grupo ->{
			grupo.add(WebMvcLinkBuilder.linkTo(
					WebMvcLinkBuilder.methodOn(GrupoController.class).buscar(grupo.getId())).withSelfRel());	
		});
		
		out.getFormaPagamento().add(WebMvcLinkBuilder.linkTo(
				WebMvcLinkBuilder.methodOn(FormaPagamentoController.class).buscar(out.getFormaPagamento().getId(), null)).withSelfRel());
		
		
		out.getEnderecoEntrega().getCidade().add(WebMvcLinkBuilder.linkTo(
				WebMvcLinkBuilder.methodOn(CidadeController.class).buscar(out.getEnderecoEntrega().getCidade().getId()
						)).withSelfRel());
		
		out.getEnderecoEntrega().getCidade().getEstado().add(
				WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(EstadoController.class)
						.buscar(out.getEnderecoEntrega().getCidade().getEstado().getId())).withSelfRel());
		
		out.getItens().stream().forEach(item -> {
			Long produtoId = item.getProdutoId();
			item.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(RestauranteProdutoController.class)
					.buscarProduto(out.getRestaurante().getId(), produtoId)).withSelfRel());
		});
				
		return out;
	}
}
