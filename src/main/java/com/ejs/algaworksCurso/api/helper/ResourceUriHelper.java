package com.ejs.algaworksCurso.api.helper;

import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpHeaders;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

public class ResourceUriHelper {
	
	public static void addUriHeaderUpdate() {
		
		String uri = getUri(null);
		        
        HttpServletResponse response = ((ServletRequestAttributes)
        		RequestContextHolder.getRequestAttributes()).getResponse();
        response.setHeader(HttpHeaders.LOCATION, uri);
        
	}
	
	public static void addUriHeaderSave(Object resourceId) {
		
		String uri = getUri(resourceId);
		        
        HttpServletResponse response = ((ServletRequestAttributes)
        		RequestContextHolder.getRequestAttributes()).getResponse();
        response.setHeader(HttpHeaders.LOCATION, uri);
        
	}
	
	public static String uriRequest(Object resourceId) {
		return getUri(resourceId);
	}
	
	private static String getUri(Object resourceId) {
		if ( resourceId == null) {
			return ServletUriComponentsBuilder.fromCurrentRequest()
					.buildAndExpand().toUri().toString();
		}else {
			return ServletUriComponentsBuilder.fromCurrentRequest()
					.path("/{id}")
					.buildAndExpand(resourceId).toUri().toString();
		}
	}

}
