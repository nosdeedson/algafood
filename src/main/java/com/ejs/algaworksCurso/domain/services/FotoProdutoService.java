package com.ejs.algaworksCurso.domain.services;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ejs.algaworksCurso.api.model.in.produto.FotoProdutoIn;
import com.ejs.algaworksCurso.api.model.out.fotoProduto.FotoProdutoOut;
import com.ejs.algaworksCurso.domain.exception.ProdutoNaoEncontradoException;
import com.ejs.algaworksCurso.domain.model.FotoProduto;
import com.ejs.algaworksCurso.domain.model.Produto;
import com.ejs.algaworksCurso.domain.repository.ProdutoRepository;
import com.ejs.algaworksCurso.domain.repository.ProdutoRepositoryQuery;
import com.ejs.algaworksCurso.helper.foto.NovaFoto;
import com.ejs.algaworksCurso.helper.fotoProduto.FotoProdutoDisAssembler;

@Service
public class FotoProdutoService {

	@Autowired
	ProdutoRepository produtoRepository;

	@Autowired
	ProdutoRepositoryQuery produtoRepositoryQuery;

	@Autowired
	FotoProdutoDisAssembler fotoProdutoDisAssembler;
	
	@Autowired
	ArmazenamentoFoto armazenamentoFoto;

	@Transactional
	public FotoProdutoOut salvar(FotoProdutoIn in, Long restauranteId, Long produtoId) throws IOException {

		FotoProdutoOut out;
		Optional<Produto> produto = this.produtoRepository.findByIdAndRestaurante_Id(produtoId, restauranteId);
		
		Optional<FotoProduto> fotoProduto = this.produtoRepository.findByRestauranteIdProdutoId(restauranteId, produtoId);
		if ( fotoProduto.isPresent()) {
			this.produtoRepositoryQuery.delete(fotoProduto.get());
		}
		if (produto.isEmpty()) {
			throw new ProdutoNaoEncontradoException(produtoId, restauranteId);
		}
		FotoProduto fp = in.fotoProdutoInToFotoProduto();
		fp.setProduto(produto.get());
		fp = this.produtoRepositoryQuery.save(fp);
		
		String novoNome = armazenamentoFoto.novoNomeFoto(in.getNomeArquivo());
		NovaFoto foto = new NovaFoto.Builder().input(in.getInputStreamFoto())
				.nomeArquivo(novoNome)
				.build();
		
		this.armazenamentoFoto.armazenar(foto);
		
		out = fotoProdutoDisAssembler.FotoProdutoToFotoProdutoOut(fp);
		out.setNomeArquivo(novoNome);

		return out;
	}
}
