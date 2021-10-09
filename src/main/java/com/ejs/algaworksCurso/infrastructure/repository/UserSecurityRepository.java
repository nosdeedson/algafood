package com.ejs.algaworksCurso.infrastructure.repository;

import java.util.Collection;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ejs.algaworksCurso.domain.model.Usuario;
import com.ejs.algaworksCurso.domain.repository.UsuarioRepository;

@Service
public class UserSecurityRepository implements UserDetailsService{
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public UserDetails loadUserByUsername(String username ) throws UsernameNotFoundException  {
		try {
			Usuario usuario = usuarioRepository.findByEmail(username)
					.orElseThrow(() -> new UsernameNotFoundException("Usuário inexistente."));
			Collection<SimpleGrantedAuthority> authorities = new HashSet<>();
			
			usuario.getGrupos().forEach(grupo ->{
				grupo.getPermissoes().forEach(permissao ->{
					authorities.add(new SimpleGrantedAuthority(permissao.getNome()));
				});
			});
			
			return new User(usuario.getEmail(), usuario.getSenha(), authorities);
		} catch (BadCredentialsException e) {
			throw new BadCredentialsException("Usuário ou senha inválidos");
		}
	}

}
