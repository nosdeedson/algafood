package com.ejs.algaworksCurso.helper.produto;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ejs.algaworksCurso.api.model.dto.in.produto.ProdutoIn;
import com.ejs.algaworksCurso.domain.model.Produto;

@Component
public class ProdutoAssembler {

	@Autowired
	private ModelMapper mapper;
	
	public Produto produtoInToProduto(ProdutoIn produtoIn) {
		return this.mapper.map(produtoIn, Produto.class);
	}
	
	public void produtoInToProduto(ProdutoIn produtoIn, Produto produto) {
		this.mapper.map(produtoIn, produto);
	}
}
