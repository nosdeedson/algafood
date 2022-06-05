package com.ejs.algaworksCurso.core.security;


import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.ejs.algaworksCurso.domain.repository.UsuarioRepository;
import com.ejs.algaworksCurso.infrastructure.repository.UserSecurityRepository;

 @Configuration
 @EnableWebSecurity
// @EnableAutoConfiguration
 @EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurity extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private JWTUtil jwtUtil;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private UserSecurityRepository userSecurityRepository;
	
	@Value("${appName.allowedApi}")
	private String localHost;
	
	private static final String[] PUBLIC_MATCHERS = {
		"/swagger-ui.html", "/", "/root"
	};
	
	private static final String[] PUBLIC_MATCHERS_POST = {
		"/login" , "/usuarios/**"
	};

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.csrf().disable();
		http.cors().disable();
		http.cors().configurationSource(corsConfiguration());
		http.sessionManagement()
		.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
		http.authorizeRequests()
			.antMatchers(HttpMethod.GET, PUBLIC_MATCHERS).permitAll()
			.antMatchers(HttpMethod.POST, PUBLIC_MATCHERS_POST).permitAll()
			.anyRequest().authenticated();
		http.addFilter(new JWTAuthenticationFilter(super.authenticationManager(), jwtUtil, usuarioRepository)); //corrigir
		http.addFilter(new JWTAuthorizationFilter(super.authenticationManager(), jwtUtil, userSecurityRepository));
		
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder( passwordEncoder);
	}
		
	@Bean
	public CorsConfigurationSource corsConfiguration() {
		CorsConfiguration cors = new CorsConfiguration().applyPermitDefaultValues();
		cors.addAllowedOrigin("*");
		cors.addAllowedHeader("*");
		cors.addAllowedMethod("*");
		cors.setAllowedMethods(Arrays.asList("*"));
		cors.setAllowedOrigins(Arrays.asList("*")); 
		cors.setAllowedHeaders(Arrays.asList("*"));
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", cors);
		return source;
	}
		
}
