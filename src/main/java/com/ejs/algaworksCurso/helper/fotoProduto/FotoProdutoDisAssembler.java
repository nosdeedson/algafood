package com.ejs.algaworksCurso.helper.fotoProduto;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.HttpMediaTypeNotAcceptableException;

import com.ejs.algaworksCurso.api.v1.controller.RestauranteFotoProdutoContoller;
import com.ejs.algaworksCurso.api.v1.model.out.fotoProduto.FotoProdutoOut;
import com.ejs.algaworksCurso.domain.model.FotoProduto;

@Component
public class FotoProdutoDisAssembler extends RepresentationModelAssemblerSupport<FotoProduto, FotoProdutoOut> {

	public FotoProdutoDisAssembler() {
		super(RestauranteFotoProdutoContoller.class, FotoProdutoOut.class);
	}

	@Autowired
	private ModelMapper mapper;

	@Override
	public FotoProdutoOut toModel(FotoProduto entity) {
		FotoProdutoOut out = mapper.map(entity, FotoProdutoOut.class);
		
		out.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(RestauranteFotoProdutoContoller.class)
				.buscarDadosFoto(entity.getProduto().getRestaurante().getId(), entity.getProduto().getId())).withSelfRel());

		try {
			out.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(RestauranteFotoProdutoContoller.class)
					.buscarFoto(entity.getProduto().getRestaurante().getId(),
							entity.getProduto().getId(), null)).withRel("foto"));
		} catch (HttpMediaTypeNotAcceptableException e) {
			e.printStackTrace();
		}
		return out;
	}
}
