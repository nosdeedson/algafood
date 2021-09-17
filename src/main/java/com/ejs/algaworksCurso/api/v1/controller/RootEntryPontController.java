package com.ejs.algaworksCurso.api.v1.controller;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;

@Api(tags = "RootEntryPoint")
@RestController
@RequestMapping("root")
public class RootEntryPontController {

	@GetMapping
	public RootEntryPointModel root() {
		var root = new RootEntryPointModel();
		root.add(WebMvcLinkBuilder.linkTo(CidadeController.class).withRel("cidades"));
		root.add(WebMvcLinkBuilder.linkTo(CozinhaController.class).withRel("cozinhas"));
		root.add(WebMvcLinkBuilder.linkTo(EstadoController.class).withRel("estados"));
		root.add(WebMvcLinkBuilder.linkTo(FormaPagamentoController.class).withRel("formasPagamento"));
		root.add(WebMvcLinkBuilder.linkTo(GrupoController.class).withRel("grupos"));
		root.add(WebMvcLinkBuilder.linkTo(PedidoController.class).withRel("pedidos"));
		root.add(WebMvcLinkBuilder.linkTo(PermissaoController.class).withRel("permissoes"));
		root.add(WebMvcLinkBuilder.linkTo(RestauranteController.class).withRel("restaurantes"));
		root.add(WebMvcLinkBuilder.linkTo(UsuarioController.class).withRel("usuarios"));
		root.add(WebMvcLinkBuilder.linkTo(EstatisticaController.class).withRel("estatisticas"));
		return root;
	}
	
	private static class RootEntryPointModel extends RepresentationModel<RootEntryPointModel>{}
}
