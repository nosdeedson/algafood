package com.ejs.algaworksCurso.helper.fotoProduto;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ejs.algaworksCurso.api.model.out.fotoProduto.FotoProdutoOut;
import com.ejs.algaworksCurso.domain.model.FotoProduto;

@Component
public class FotoProdutoDisAssembler {

	@Autowired
	private ModelMapper mapper;
	
	public FotoProdutoOut FotoProdutoToFotoProdutoOut(FotoProduto fp) {
		return this.mapper.map(fp, FotoProdutoOut.class);
	}
}
