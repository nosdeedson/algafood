package com.ejs.algaworksCurso.core.security;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsFilterConfig {

	@Bean
	public CorsFilter corsFiler() {
		CorsConfiguration config = new CorsConfiguration();
	    config.addAllowedOrigin("*");
	    config.addAllowedHeader("*");
	    config.setAllowedHeaders(Arrays.asList("*"));
	    config.setAllowedOrigins(Arrays.asList("*"));
	    config.setAllowedMethods(Arrays.asList("*"));

	    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	    source.registerCorsConfiguration("/**", config);

	    return new CorsFilter(source);
	}
}
