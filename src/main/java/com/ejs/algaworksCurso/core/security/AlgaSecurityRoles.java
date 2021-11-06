package com.ejs.algaworksCurso.core.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import com.ejs.algaworksCurso.domain.exception.UsuarioNaoEncontradoException;
import com.ejs.algaworksCurso.domain.model.Usuario;
import com.ejs.algaworksCurso.domain.repository.PedidoRepository;
import com.ejs.algaworksCurso.domain.repository.UsuarioRepository;

@Component
public class AlgaSecurityRoles {

	 @Autowired
	 private UsuarioRepository usuarioRepository;
	
	 @Autowired PedidoRepository pedidoRepository;
	
	 public boolean ehResponsavelPeloRestaurante(Long restauranteId) {
	 	User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
	 	boolean ehResponsavel = this.usuarioRepository.existsResponsavel(principal.getUsername(), restauranteId); 
	 	return ehResponsavel;
	 }
	
	 public Long getUsuarioId() {
	 	User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	 	Usuario user = this.usuarioRepository.findByEmail(principal.getUsername())
	 			.orElseThrow(() -> new UsuarioNaoEncontradoException("Usuário não encontrado."));
	 	return user.getId();
	 }
	
	 public boolean podeConfirmarEntregarPedido(String codigoPedido) {
	 	User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	 	Usuario user = this.usuarioRepository.findByEmail(principal.getUsername())
	 			.orElseThrow(() -> new UsuarioNaoEncontradoException("Usuário não encontrado."));
	 	boolean pedido = this.pedidoRepository.findByCodigoAndResponsavelId(codigoPedido, user.getId());
		
	 	return pedido;
	 }
	
	 public boolean podeCancelarPedido(String codigoPedido) {
	 	User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	 	Usuario user = this.usuarioRepository.findByEmail(principal.getUsername())
	 			.orElseThrow(() -> new UsuarioNaoEncontradoException("Usuário não encontrado."));
	 	boolean pedido = this.pedidoRepository.findByCodigoAndUsuarioId(codigoPedido, user.getId());
		
	 	return pedido;
	 }
	
	
	
	
	
	
}
