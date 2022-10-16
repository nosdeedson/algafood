package com.ejs.algaworksCurso.api.v1.model.in.produto;

import java.io.IOException;
import java.io.InputStream;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;

import com.ejs.algaworksCurso.core.validation.FileContentType;
import com.ejs.algaworksCurso.core.validation.FileSize;
import com.ejs.algaworksCurso.domain.model.FotoProduto;

public class FotoProdutoIn {
	
	@NotNull
	@FileSize(max = "500KB")
	@FileContentType(allowed = {MediaType.IMAGE_PNG_VALUE, MediaType.IMAGE_JPEG_VALUE})
	private MultipartFile arquivo;
	
	@NotBlank
	private String descricao;
	
	public FotoProduto fotoProdutoInToFotoProduto() {
		FotoProduto fp = new FotoProduto();
		fp.setContentType(this.arquivo.getContentType());
		fp.setDescricao(this.getDescricao());
		fp.setNomeArquivo(this.arquivo.getOriginalFilename());
		fp.setTamanho(this.arquivo.getSize());
		return fp;
	}
	
	
	public InputStream getInputStreamFoto() throws IOException {
		if( this.getArquivo() != null)
			return this.getArquivo().getInputStream();
		return null;
	}
	
	public String getNomeArquivo() {
		if ( this.getArquivo().getOriginalFilename() != null)
			return this.getArquivo().getOriginalFilename();
		return null;
	}
	
	
	/**
	 * @return the arquivo
	 */
	public MultipartFile getArquivo() {
		return arquivo;
	}
	/**
	 * @param arquivo the arquivo to set
	 */
	public void setArquivo(MultipartFile arquivo) {
		this.arquivo = arquivo;
	}
	/**
	 * @return the descricao
	 */
	public String getDescricao() {
		return descricao;
	}
	/**
	 * @param descricao the descricao to set
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	

}
