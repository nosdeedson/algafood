package com.ejs.algaworksCurso.core.modelMapper;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ejs.algaworksCurso.api.model.in.itensPedido.ItensPedidoIn;
import com.ejs.algaworksCurso.domain.model.ItemPedido;

@Configuration
public class ModelMapperConfig {

	@Bean
	public ModelMapper createModelMapper() {
		var mapper = new ModelMapper();
		
		mapper.createTypeMap(ItensPedidoIn.class, ItemPedido.class)
			.addMappings(map -> map.skip(ItemPedido::setId));
		
		mapper.createTypeMap(ItemPedido.class, ItemPedido.class)
			.addMappings(map -> map.skip(ItemPedido::setPedido));
		return mapper;
	}
}
