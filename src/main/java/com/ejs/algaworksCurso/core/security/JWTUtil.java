package com.ejs.algaworksCurso.core.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.ejs.algaworksCurso.domain.model.Usuario;


@Component
public class JWTUtil {

	@Value("${jwt.secret}")
	private String secret;
	
	@Value("${jwt.expiration}")
	private Long expiration;
	
	public String generateToken(Usuario usuario) {
		
		String token = "";
		
		token  = JWT.create()
					.withSubject(usuario.getEmail())
					.withClaim("id", usuario.getId().intValue())
					.withIssuer(usuario.getNome())
					.withExpiresAt(new Date(System.currentTimeMillis()+ expiration) )
					.sign(Algorithm.HMAC512(secret.getBytes()));
		
		return token;
	}
	
	public boolean tokenIsValid( String token) {
		
		DecodedJWT tokenDecoded = JWT.decode(token);
		Date expiration = tokenDecoded.getExpiresAt();
		Date now = new Date(System.currentTimeMillis());
		String userName = tokenDecoded.getSubject();
		if ( expiration.after(now) && userName != null) {
			return true;
		}
		return false;
	}
	
	public String getUserName(String token) {
		
		DecodedJWT tokenDecoded = JWT.decode(token);
		String userName = tokenDecoded.getSubject();
		if ( userName != null)
			return userName;
		return null;
	}
	
}
