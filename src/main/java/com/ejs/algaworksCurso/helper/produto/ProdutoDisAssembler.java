package com.ejs.algaworksCurso.helper.produto;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ejs.algaworksCurso.api.model.in.produto.ProdutoOut;
import com.ejs.algaworksCurso.domain.model.Produto;

@Component
public class ProdutoDisAssembler {

	@Autowired
	private ModelMapper mapper;
	
	public ProdutoOut produtoToProdutoOUt(Produto produto) {
		return this.mapper.map(produto, ProdutoOut.class);
	}
}
