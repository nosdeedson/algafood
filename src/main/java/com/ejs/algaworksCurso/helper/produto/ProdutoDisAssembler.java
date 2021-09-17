package com.ejs.algaworksCurso.helper.produto;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import com.ejs.algaworksCurso.api.v1.controller.RestauranteProdutoController;
import com.ejs.algaworksCurso.api.v1.model.in.produto.ProdutoOut;
import com.ejs.algaworksCurso.domain.model.Produto;

@Component
public class ProdutoDisAssembler extends RepresentationModelAssemblerSupport<Produto, ProdutoOut> {

	public ProdutoDisAssembler() {
		super(RestauranteProdutoController.class, ProdutoOut.class);
	}

	@Autowired
	private ModelMapper mapper;
	
	@Override
	public ProdutoOut toModel(Produto entity) {
		ProdutoOut out = createModelWithId(entity.getId(), entity, entity.getRestaurante().getId());
		mapper.map(entity, out);
		out.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(RestauranteProdutoController.class)
				.listarProduto( entity.getId(), null)).withRel("produtos"));
		return out;
	}
	
	@Override
	public CollectionModel<ProdutoOut> toCollectionModel(Iterable<? extends Produto> entities) {
		return super.toCollectionModel(entities);
	}
}
