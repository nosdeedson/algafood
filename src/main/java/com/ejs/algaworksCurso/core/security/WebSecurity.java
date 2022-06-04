package com.ejs.algaworksCurso.core.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
		"/swagger-ui.html", "/"
	};
	
	private static final String[] PUBLIC_MATCHERS_POST = {
		"/login" , "/usuarios/**"
	};

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.cors().and().csrf().disable();
		http.sessionManagement()
		.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
		http.authorizeRequests()
			.antMatchers(PUBLIC_MATCHERS).permitAll()
			.antMatchers(HttpMethod.POST, PUBLIC_MATCHERS_POST).permitAll()
			.anyRequest().permitAll();
		
		http.addFilter(new JWTAuthenticationFilter(super.authenticationManager(), jwtUtil, usuarioRepository)); //corrigir
		http.addFilter(new JWTAuthorizationFilter(super.authenticationManager(), jwtUtil, userSecurityRepository));
		
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder( passwordEncoder);
	}
	
//	@Override
//	public void addCorsMappings(CorsRegistry registry) {
//		registry.addMapping("/**")
//			.allowedMethods("*")
//			.allowedHeaders("*")
//			.allowedOrigins("*")
//			.allowedOrigins(localHost);
//	}
	
//	@Bean
//	public CorsFilter corsFiler() {
//		CorsConfiguration config = new CorsConfiguration();
//	    config.addAllowedOrigin("http://127.0.0.1:4200");
//	    config.addAllowedOrigin("http://localhost:4200");
//	    config.addAllowedOrigin("/**");
//	    config.addAllowedHeader("*");
//	    config.setAllowedMethods(Arrays.asList("*"));
//
//	    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//	    source.registerCorsConfiguration("/**", config);
//
//	    return new CorsFilter(source);
//	}
	
//	@Bean
//	public CorsConfigurationSource corsConfiguration() {
//		CorsConfiguration cors = new CorsConfiguration().applyPermitDefaultValues();
//		cors.setAllowedMethods(Arrays.asList("*"));
//		cors.setAllowedOrigins(Arrays.asList("*")); 
//		cors.setAllowedHeaders(Arrays.asList("*"));
//		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//		source.registerCorsConfiguration("/**", cors);
//		return source;
//	}
		
}
