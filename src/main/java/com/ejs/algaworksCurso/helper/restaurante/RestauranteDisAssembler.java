package com.ejs.algaworksCurso.helper.restaurante;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
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
import com.ejs.algaworksCurso.api.v1.controller.RestauranteController;
import com.ejs.algaworksCurso.api.v1.controller.RestauranteFormasPagamentoController;
import com.ejs.algaworksCurso.api.v1.controller.RestauranteProdutoController;
import com.ejs.algaworksCurso.api.v1.controller.RestauranteUsuarioController;
import com.ejs.algaworksCurso.api.v1.model.out.restautante.RestauranteOut;
import com.ejs.algaworksCurso.domain.model.Restaurante;

@Component
public class RestauranteDisAssembler extends RepresentationModelAssemblerSupport<Restaurante, RestauranteOut> {
	
	public RestauranteDisAssembler() {
		super(RestauranteController.class, RestauranteOut.class);
	}

	@Autowired
	private ModelMapper mapper;

	@Override
	public RestauranteOut toModel(Restaurante entity) {
		RestauranteOut out = createModelWithId(entity.getId(), entity);
		mapper.map(entity, out);
		
		String restaurantesUrl = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(RestauranteController.class)
				.listar(null, null, null)).withSelfRel().toUri().toString();
		
		TemplateVariables templateVariables = new TemplateVariables(
					new TemplateVariable("nome", VariableType.REQUEST_PARAM),
					new TemplateVariable("taxaFreteInicial", VariableType.REQUEST_PARAM),
					new TemplateVariable("taxaFreteFinal", VariableType.REQUEST_PARAM)
				);
		
		out.add(Link.of(UriTemplate.of(restaurantesUrl, templateVariables), "restaurantes"));
		out.add(WebMvcLinkBuilder.linkTo(RestauranteController.class).withRel("restaurantes"));
		
		
		out.getCozinha().add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CozinhaController.class)
				.buscar(out.getCozinha().getId())).withSelfRel());
		
		if ( entity.getAtivo()) {
			out.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(RestauranteController.class)
					.ativar(out.getId())).withRel("ativar/inativar"));
		}else {
			out.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(RestauranteController.class)
					.inativar(out.getId())).withRel("ativar/inativar"));
		}
		
		if ( entity.getAberto()) {
			out.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(RestauranteController.class)
					.fechar(out.getId())).withRel("abrir/fechar"));
		}else {
			out.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(RestauranteController.class)
					.abrir(out.getId())).withRel("abrir/fechar"));
		}
		
		entity.getFormasPagamento().stream().forEach(formaPagamento ->{
			out.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(RestauranteFormasPagamentoController.class)
					.buscar(out.getId(), formaPagamento.getId())).withRel("formasPagamento"));
		});
		
		if ( Optional.ofNullable(out.getEndereco()).isPresent()) {
			out.getEndereco().getCidade().add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CidadeController.class)
					.buscar(out.getEndereco().getCidade().getId())).withSelfRel());
			out.getEndereco().getCidade().getEstado().add(WebMvcLinkBuilder.linkTo(
					WebMvcLinkBuilder.methodOn(EstadoController.class).buscar(out.getEndereco().getCidade().getEstado().getId()))
					.withSelfRel());
		}
		
		if ( Optional.ofNullable(entity.getFormasPagamento()).isPresent()) {
			entity.getFormasPagamento().stream().forEach(formaPagamento ->{
				out.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(RestauranteFormasPagamentoController.class)
						.desassociarFormaPagamento(out.getId(), formaPagamento.getId())).withRel("desassociarFormapagamento"));
			});
		}
		
		if ( Optional.ofNullable(entity.getResponsaveis()).isPresent()) {
			out.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(RestauranteUsuarioController.class)
					.listarResponsaveis(out.getId())).withRel("responsaveis"));
		}
		
		out.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(RestauranteProdutoController.class)
					.listarProduto(entity.getId(), null)).withRel("produtos"));
		
		return out;
	}
	
	
	@Override
	public CollectionModel<RestauranteOut> toCollectionModel(Iterable<? extends Restaurante> entities) {
		return super.toCollectionModel(entities);
	}
	
	
}
