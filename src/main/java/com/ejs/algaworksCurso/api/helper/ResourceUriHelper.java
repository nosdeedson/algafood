package com.ejs.algaworksCurso.api.helper;

import java.net.URI;

import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpHeaders;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

public class ResourceUriHelper {
	
	public static void addUriHeaderUpdate() {
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.buildAndExpand().toUri();
		        
        HttpServletResponse response = ((ServletRequestAttributes)
        		RequestContextHolder.getRequestAttributes()).getResponse();
        response.setHeader(HttpHeaders.LOCATION, uri.toString());
        
	}
	
	public static void addUriHeaderSave(Object resourceId) {
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(resourceId).toUri();
		        
        HttpServletResponse response = ((ServletRequestAttributes)
        		RequestContextHolder.getRequestAttributes()).getResponse();
        response.setHeader(HttpHeaders.LOCATION, uri.toString());
        
	}

}
