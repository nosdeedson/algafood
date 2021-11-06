package com.ejs.algaworksCurso.core.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.ejs.algaworksCurso.api.v1.model.dto.CredencialDTO;
import com.ejs.algaworksCurso.domain.model.Usuario;
import com.ejs.algaworksCurso.domain.repository.UsuarioRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

 	private AuthenticationManager authenticationManager;
	
 	private JWTUtil jwtUtil;
	
 	private UsuarioRepository usuarioRepository;

 	public JWTAuthenticationFilter(AuthenticationManager authenticationManager, JWTUtil jwtUtil,
 			UsuarioRepository usuarioRepository) {
		
 		this.authenticationManager = authenticationManager;
 		this.jwtUtil = jwtUtil;
 		this.usuarioRepository = usuarioRepository;
 	}
	
 	@Override
 	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
 			throws AuthenticationException {
 		try {
 			CredencialDTO dto = new ObjectMapper()
 					.readValue(request.getInputStream(), CredencialDTO.class);
 			UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getSenha(), new ArrayList<>());
 			Authentication auth = authenticationManager.authenticate(token);
			
 			return auth;
 		} catch (Exception e) {
 			throw new BadCredentialsException("Usuário ou senha inválido.");
 		}
 	}
	
 	@Override
 	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
 			Authentication authResult) throws IOException, ServletException {
 		User user =  (User) authResult.getPrincipal();
 		Optional<Usuario> usuario = usuarioRepository.findByEmail(user.getUsername());
 		String token = jwtUtil.generateToken(usuario.get());
 //		response.addHeader("Authorization", "Bearer " + token);
 //		response.addHeader("access-control-expose-headers", "Authorization");
 		response.setContentType(MediaType.APPLICATION_JSON_VALUE);
 		Map<String, Object> res = new HashMap<String, Object>();
 		res.put("token", token);
 		res.put("Authorities", user.getAuthorities());
 		new ObjectMapper().writeValue(response.getOutputStream(), res);
 	}
	
 	@Override
 	protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
 			AuthenticationException failed) throws IOException, ServletException {
 		response.setStatus(401);
 		response.setContentType("application/json");
 		try {
 			response.getWriter().append(json());
 		} catch (IOException | JSONException e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 		}
 	}
	
	private String json() throws JSONException {
		long date = new Date().getTime();
		
		JSONObject json = new JSONObject();
		json.put("timestamp", date);
		json.put("status", 401);
		json.put("error", "Não autorizado");
		json.put("message", "Senha ou usuário inválido");
		json.put("path", "/login");
		return json.toString();

	}
	
}
