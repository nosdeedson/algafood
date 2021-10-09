package com.ejs.algaworksCurso.core.security;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
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

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private JWTUtil jwtUtil;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	
	private static final String[] PUBLIC_MATCHERS = {
		"/swagger-ui.html", "/"
	};
	
	private static final String[] PUBLIC_MATCHERS_POST = {
		"/login" , "usuarios"
	};

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.cors().and().csrf().disable();
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http.sessionManagement().maximumSessions(1).maxSessionsPreventsLogin(true);
		http.authorizeRequests()
			.antMatchers(HttpMethod.GET).permitAll()
			.antMatchers(PUBLIC_MATCHERS).permitAll()
			.antMatchers(HttpMethod.POST, PUBLIC_MATCHERS_POST).permitAll()
			.anyRequest().authenticated();
		
		http.addFilter(new JWTAuthenticationFilter(super.authenticationManagerBean(), jwtUtil, usuarioRepository));
		http.addFilter(new JWTAuthorizationFilter(super.authenticationManager(), jwtUtil, userDetailsService));
		
		
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder( passwordEncoder);
	}
	
	@Bean
	public CorsConfigurationSource corsConfiguration() {
		CorsConfiguration cors = new CorsConfiguration().applyPermitDefaultValues();
		cors.setAllowedMethods(Arrays.asList("POST", "GET", "DELETE", "PUT", "OPTIONS", "HEAD", "PATCH"));
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", cors);
		return source;
	}
	
}
