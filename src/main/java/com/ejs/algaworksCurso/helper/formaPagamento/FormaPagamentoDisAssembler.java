package com.ejs.algaworksCurso.helper.formaPagamento;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import com.ejs.algaworksCurso.api.controller.FormaPagamentoController;
import com.ejs.algaworksCurso.api.model.out.formaPagamento.FormaPagamentoOut;
import com.ejs.algaworksCurso.domain.model.FormaPagamento;

@Component
public class FormaPagamentoDisAssembler extends RepresentationModelAssemblerSupport<FormaPagamento, FormaPagamentoOut> {

	public FormaPagamentoDisAssembler() {
		super(FormaPagamentoController.class, FormaPagamentoOut.class);
	}

	@Autowired
	private ModelMapper mapper;
	
	@Override
	public FormaPagamentoOut toModel(FormaPagamento entity) {
		FormaPagamentoOut out = createModelWithId(entity.getId(), entity);
		mapper.map(entity, out);
		out.add(WebMvcLinkBuilder.linkTo(FormaPagamentoController.class).withRel("formasPagamento"));
		
		return out;
	}
	
	@Override
	public CollectionModel<FormaPagamentoOut> toCollectionModel(Iterable<? extends FormaPagamento> entities) {
		return super.toCollectionModel(entities);
	}
}
