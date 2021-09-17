package com.ejs.algaworksCurso.helper.pedido;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import com.ejs.algaworksCurso.api.v1.controller.GrupoController;
import com.ejs.algaworksCurso.api.v1.controller.PedidoController;
import com.ejs.algaworksCurso.api.v1.controller.RestauranteController;
import com.ejs.algaworksCurso.api.v1.controller.UsuarioController;
import com.ejs.algaworksCurso.api.v1.model.out.pedido.PedidoResumidoDTO;
import com.ejs.algaworksCurso.domain.model.Pedido;

@Component
public class PedidoResumidoDisAssembler extends RepresentationModelAssemblerSupport<Pedido, PedidoResumidoDTO> {

	@Autowired
	private ModelMapper mapper;
	
	public PedidoResumidoDisAssembler() {
		super(PedidoController.class, PedidoResumidoDTO.class);
	}

	@Override
	public PedidoResumidoDTO toModel(Pedido entity) {
		PedidoResumidoDTO pedidoResumidoDTO = createModelWithId(entity.getId(), entity);
		mapper.map(entity, pedidoResumidoDTO);
		
		pedidoResumidoDTO.getRestaurante().add(WebMvcLinkBuilder.linkTo(
				WebMvcLinkBuilder.methodOn(RestauranteController.class)
				.buscar(pedidoResumidoDTO.getRestaurante().getId())).withSelfRel());
		
		
		pedidoResumidoDTO.getCliente().add(WebMvcLinkBuilder.linkTo(
				WebMvcLinkBuilder.methodOn(UsuarioController.class)
				.buscar(pedidoResumidoDTO.getCliente().getId())).withSelfRel());
		
		pedidoResumidoDTO.getCliente().getGrupos().stream().forEach(grupo ->{
			grupo.add(WebMvcLinkBuilder.linkTo(
					WebMvcLinkBuilder.methodOn(GrupoController.class)
					.buscar(grupo.getId())).withSelfRel());
		});
		return pedidoResumidoDTO;
	}

}
