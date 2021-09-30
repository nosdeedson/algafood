package com.ejs.algaworksCurso.api.v1.model.out.fotoProduto;

import org.springframework.http.MediaType;

import com.ejs.algaworksCurso.helper.foto.FotoRecuperada;

public class ImagemOut {
	
	private FotoRecuperada fotoRecuperada;
	private MediaType mediaType;
	
	public ImagemOut(FotoRecuperada fotoRecuperada, MediaType mediaType) {
		this.fotoRecuperada = fotoRecuperada;
		this.mediaType = mediaType;
	}

	public FotoRecuperada getFotoRecuperada() {
		return fotoRecuperada;
	}

	public MediaType getMediaType() {
		return mediaType;
	}
	
	
	

}
