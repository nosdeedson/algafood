package com.ejs.algaworksCurso.domain.services;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.HttpMediaTypeNotAcceptableException;

import com.ejs.algaworksCurso.api.model.in.produto.FotoProdutoIn;
import com.ejs.algaworksCurso.api.model.out.fotoProduto.FotoProdutoOut;
import com.ejs.algaworksCurso.api.model.out.fotoProduto.ImagemOut;
import com.ejs.algaworksCurso.domain.exception.FotoProdutoNaoEncontradaException;
import com.ejs.algaworksCurso.domain.exception.ProdutoNaoEncontradoException;
import com.ejs.algaworksCurso.domain.model.FotoProduto;
import com.ejs.algaworksCurso.domain.model.Produto;
import com.ejs.algaworksCurso.domain.repository.ProdutoRepository;
import com.ejs.algaworksCurso.domain.repository.ProdutoRepositoryQuery;
import com.ejs.algaworksCurso.helper.foto.FotoRecuperada;
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
		if (produto.isEmpty()) {
			throw new ProdutoNaoEncontradoException(produtoId, restauranteId);
		}
		
		Optional<FotoProduto> fotoProduto = this.produtoRepository.findByRestauranteIdProdutoId(restauranteId, produtoId);
		String nomeArquivoAntigo = null;
		if ( fotoProduto.isPresent()) {
			nomeArquivoAntigo = fotoProduto.get().getNomeArquivo();
			this.produtoRepositoryQuery.delete(fotoProduto.get());
		}
		FotoProduto fp = in.fotoProdutoInToFotoProduto();
		fp.setProduto(produto.get());
		fp.setNomeArquivo(armazenamentoFoto.novoNomeFoto(in.getNomeArquivo()));
		fp = this.produtoRepositoryQuery.save(fp);
		
		NovaFoto foto = new NovaFoto.Builder().input(in.getInputStreamFoto())
				.nomeArquivo(fp.getNomeArquivo())
				.contentType(in.getArquivo().getContentType())
				.build();
		
		this.armazenamentoFoto.substituir(nomeArquivoAntigo, foto);
		
		out = fotoProdutoDisAssembler.FotoProdutoToFotoProdutoOut(fp);
		return out;
	}
	
	public void deletarFotoProduto(Long restauranteId, Long produtoId) {
		FotoProduto fp = this.produtoRepository.findByRestauranteIdProdutoId(restauranteId, produtoId)
				.orElseThrow(() -> new FotoProdutoNaoEncontradaException(restauranteId, produtoId));
		
		String nomeArquivoDeletar = fp.getNomeArquivo();
		
		this.armazenamentoFoto.remover(nomeArquivoDeletar);
		
		this.produtoRepositoryQuery.delete(fp);
	}
	
	public FotoProdutoOut recuperar( Long restauranteId, Long produtoId) {
		FotoProduto fotoProduto = this.produtoRepository.findByRestauranteIdProdutoId(restauranteId, produtoId)
				.orElseThrow( () -> new FotoProdutoNaoEncontradaException(restauranteId, produtoId));
		return this.fotoProdutoDisAssembler.FotoProdutoToFotoProdutoOut(fotoProduto);
	}
	
	public ImagemOut recuperarFoto( Long restauranteId, Long produtoId, String acceptType) throws HttpMediaTypeNotAcceptableException {
		FotoProduto fp = this.produtoRepository.findByRestauranteIdProdutoId(restauranteId, produtoId)
				.orElseThrow( () -> new FotoProdutoNaoEncontradaException(restauranteId, produtoId));
		
		MediaType media = MediaType.parseMediaType(fp.getContentType());
		List<MediaType> mediasTypeAceitas =  MediaType.parseMediaTypes(acceptType);
		
		verificarMediaTypes(media, mediasTypeAceitas);
		
		FotoRecuperada fotoRecuperada = this.armazenamentoFoto.recuperar(fp.getNomeArquivo());
		ImagemOut imgOut = new ImagemOut(fotoRecuperada, MediaType.parseMediaType(fp.getContentType()));
		
		return imgOut;
	}

	private void verificarMediaTypes(MediaType media, List<MediaType> mediasTypeAceitas) throws HttpMediaTypeNotAcceptableException {
		boolean mediaTypeValido = mediasTypeAceitas.stream()
				.anyMatch(mediaType -> mediaType.isCompatibleWith(media));
		
		if ( !mediaTypeValido) {
			throw new HttpMediaTypeNotAcceptableException(mediasTypeAceitas);
		}
		
	}
}
