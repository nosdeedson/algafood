package com.ejs.algaworksCurso.core.security;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;

public @interface CheckSecurity {
	
	/** Security cidades */
	public @interface Cidades{
		@PreAuthorize("isAuthenticated()")
		@Retention(RetentionPolicy.RUNTIME)
		@Target(ElementType.METHOD)
		public @interface PodeConsultar{}
		
		@PreAuthorize("hasAuthority('EDITAR_CIDADES')")
		@Retention(RetentionPolicy.RUNTIME)
		@Target(ElementType.METHOD)
		public @interface PodeEditar{}
	}
	
	
	/** Security cozinhas */
	public @interface Cozinhas{
		
		@PreAuthorize("isAuthenticated()") 
		@Retention(RetentionPolicy.RUNTIME)
		@Target(ElementType.METHOD)
		public @interface PodeConsultar{}
		
		@PreAuthorize("hasAuthority('EDITAR_COZINHA')")
		@Retention(RetentionPolicy.RUNTIME)
		@Target(ElementType.METHOD)
		public @interface PodeEditar{}
	}
	
	/** Security estados */
	public @interface Estados{
		@PreAuthorize("isAuthenticated()")
		@Retention(RetentionPolicy.RUNTIME)
		@Target(ElementType.METHOD)
		public @interface PodeConsultar{}
		
		@PreAuthorize("hasAuthority('EDITAR_ESTADOS')")
		@Retention(RetentionPolicy.RUNTIME)
		@Target(ElementType.METHOD)
		public @interface PodeEditar{}
	}
	
	/** Security Gerar relatórios */
	public @interface Estatisticas{		
		@PreAuthorize("hasAuthority('GERAR_RELATORIOS')")
		@Retention(RetentionPolicy.RUNTIME)
		@Target(ElementType.METHOD)
		public @interface PodeConsular{}
	}
	
	/** Security formas pagamento */
	public @interface FormasPagamento{
		@PreAuthorize("isAuthenticated()")
		@Retention(RetentionPolicy.RUNTIME)
		@Target(ElementType.METHOD)
		public @interface PodeConsultar{}
		
		@PreAuthorize("hasAuthority('EDITAR_FORMAS_PAGAMENTO')")
		@Retention(RetentionPolicy.RUNTIME)
		@Target(ElementType.METHOD)
		public @interface PodeEditar{}
	}
	
	public @interface GruposPermissoesUsuarios{
		
		@PreAuthorize("isAuthenticated()")
		@Retention(RetentionPolicy.RUNTIME)
		@Target(ElementType.METHOD)
		public @interface PodeConsultar{}
		
		@PreAuthorize("hasAuthority('EDITAR_USUARIOS_GRUPOS_PERMISSOES')")
		@Retention(RetentionPolicy.RUNTIME)
		@Target(ElementType.METHOD)
		public @interface PodeEditar{}
		
		@PreAuthorize("hasAuthority('EDITAR_USUARIOS_GRUPOS_PERMISSOES') or "
				+ "@algaSecurityRoles.getUsuarioId() == #usuarioId")
		@Retention(RetentionPolicy.RUNTIME)
		@Target(ElementType.METHOD)
		public @interface PodeEditarUsuario{}
		
		@PreAuthorize("isAuthenticated() and "
				+ "@algaSecurityRoles.getUsuarioId() == #usuarioId")
		@Retention(RetentionPolicy.RUNTIME)
		@Target(ElementType.METHOD)
		public @interface PodeAlterarSenha{}
	}
	
	/** Security pedidos */
	public @interface Pedidos{
		
		@PreAuthorize("isAuthenticated()")
		@PostAuthorize("@algaSecurityRoles.getUsuarioId() == returnObject.getBody().getCliente().getId() or "
				+ " @algaSecurityRoles.ehResponsavelPeloRestaurante(returnObject.getBody().getRestaurante().getId()) or "
				+ " hasAuthority('CONSULTAR_PEDIDOS')")
		@Retention(RetentionPolicy.RUNTIME)
		@Target(ElementType.METHOD)
		public @interface PodeBuscar{}
		
		@PreAuthorize("@algaSecurityRoles.ehResponsavelPeloRestaurante(#filtro.restauranteId) or "
				+ "@algaSecurityRoles.getUsuarioId() == #filtro.clienteId or "
				+ "hasAuthority('CONSULTAR_PEDIDOS')")
		@Retention(RetentionPolicy.RUNTIME)
		@Target(ElementType.METHOD)
		public @interface PodeListar{}
		
		@PreAuthorize("hasAuthority('CONSULTAR_PEDIDOS')")
		@Retention(RetentionPolicy.RUNTIME)
		@Target(ElementType.METHOD)
		public @interface PodeConsultar{}
		
		@PreAuthorize("hasAuthority('GERENCIAR_PEDIDOS')")
		@Retention(RetentionPolicy.RUNTIME)
		@Target(ElementType.METHOD)
		public @interface PodeEditar{}
		
		@PreAuthorize("hasAuthority('GERENCIAR_PEDIDOS') or "
				+ " @algaSecurityRoles.podeConfirmarEntregarPedido(#codigoPedido)")
		@Retention(RetentionPolicy.RUNTIME)
		@Target(ElementType.METHOD)
		public @interface GerenciarPedido{}
		
		@PreAuthorize("hasAuthority('GERENCIAR_PEDIDOS') or "
				+ " @algaSecurityRoles.podeCancelarPedido(#codigoPedido)")
		@Retention(RetentionPolicy.RUNTIME)
		@Target(ElementType.METHOD)
		public @interface CancelarPedido{}
	}
	
	/** Security restaurantes */
	public @interface Restaurantes{
		
		@PreAuthorize("isAuthenticated()")
		@Retention(RetentionPolicy.RUNTIME)
		@Target(ElementType.METHOD)
		public @interface PodeConsultar{}
		
		@PreAuthorize("hasAuthority('EDITAR_RESTAURANTES')")
		@Retention(RetentionPolicy.RUNTIME)
		@Target(ElementType.METHOD)
		public @interface PodeEditar{}
		
		@PreAuthorize("@algaSecurityRoles.ehResponsavelPeloRestaurante(#restauranteId) or hasAuthority('EDITAR_RESTAURANTES')")
		@Retention(RetentionPolicy.RUNTIME)
		@Target(ElementType.METHOD)
		public @interface UserPodeEditar{}
		
	}
	
}
