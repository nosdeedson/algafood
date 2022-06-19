package com.ejs.algaworksCurso.core.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.ejs.algaworksCurso.infrastructure.repository.UserSecurityRepository;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter{

	 private JWTUtil jwtUtil;
	
	 private UserSecurityRepository userSecurityRepository;
	
	 public JWTAuthorizationFilter(AuthenticationManager authenticationManager, JWTUtil jwtUtil,
			 UserSecurityRepository userSecurityRepository) {
	 	super(authenticationManager);
	 	this.jwtUtil = jwtUtil;
	 	this.userSecurityRepository = userSecurityRepository;
	 }
	
	 @Override
	 protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
	 		throws IOException, ServletException {
	 	String header = request.getHeader("Authorization");
	 	if ( header != null && header.startsWith("Bearer ")) {
	 		UsernamePasswordAuthenticationToken auth = this.getAuthentication(header.substring(7));
	 		if ( auth != null) {
	 			SecurityContextHolder.getContext().setAuthentication(auth);
	 		}
	 	}
	 	chain.doFilter(request, response);
	 }

	 private UsernamePasswordAuthenticationToken getAuthentication(String token) {
		
	 	if ( jwtUtil.tokenIsValid(token)) {
	 		try {
				String userName = jwtUtil.getUserName(token);
				UserDetails user = userSecurityRepository.loadUserByUsername(userName);
				return new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
			} catch (BadCredentialsException e) {
				throw new BadCredentialsException("Usuário ou senha inválidos");
			}
	 	}
	 	
	 	return null;
	 }
}
