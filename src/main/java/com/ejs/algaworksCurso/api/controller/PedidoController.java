package com.ejs.algaworksCurso.api.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.ejs.algaworksCurso.api.model.dto.in.PedidoIn;
import com.ejs.algaworksCurso.api.model.dto.out.pedido.PedidoOut;
import com.ejs.algaworksCurso.api.model.dto.out.pedido.PedidoResumidoDTO;
import com.ejs.algaworksCurso.domain.services.PedidoService;

@RestController
@RequestMapping("pedidos")
public class PedidoController {

	@Autowired
	private PedidoService pedidoService;
	

	@DeleteMapping("{codigoPedido}/cancela")
	public ResponseEntity<?> cancelarPedido(@PathVariable String codigoPedido) {
		this.pedidoService.cancelarPedido(codigoPedido);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("{codigoPedido}/confirmacao")
	public ResponseEntity<?> confirmarPedido(@PathVariable String codigoPedido) {
		this.pedidoService.confirmarPedido(codigoPedido);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("{codigoPedido}")
	public ResponseEntity<?> buscar(@PathVariable String codigoPedido){
		PedidoOut out = this.pedidoService.buscar(codigoPedido);
		return ResponseEntity.ok(out);
	}
	
	@PutMapping("{codigoPedido}/entrega")
	public ResponseEntity<?> entregarPedido(@PathVariable String codigoPedido) {
		this.pedidoService.entregarPedido(codigoPedido);
		return ResponseEntity.noContent().build();
	}

	@GetMapping
	public ResponseEntity<?> listar(){
		List<PedidoResumidoDTO> pedidos = this.pedidoService.listar();
		return ResponseEntity.ok(pedidos);
	}
	
	@PostMapping
	public ResponseEntity<?> salvar(@RequestBody PedidoIn pedidoIn){
		PedidoOut out = this.pedidoService.salvar(pedidoIn);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
					.path("/{codigoPedido}").buildAndExpand(out.getCodigo())
					.toUri();
		return ResponseEntity.status(HttpStatus.CREATED).body(uri);
	}
}
