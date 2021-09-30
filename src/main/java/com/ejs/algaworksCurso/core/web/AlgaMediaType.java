package com.ejs.algaworksCurso.core.web;

import org.springframework.http.MediaType;

public class AlgaMediaType {
	
	public static final String V1_APLICATION_JSON_VALUE = "application/vnd.algafood.v1+json";
	
	public static final MediaType V1_APLICATION_JSON = MediaType.valueOf(V1_APLICATION_JSON_VALUE);
	
	public static final String V2_APLICATION_JSON_VALUE = "application/vnd.algafood.v2+json";
	
	public static final MediaType V2_APLICATION_JSON = MediaType.valueOf(V2_APLICATION_JSON_VALUE);

}
