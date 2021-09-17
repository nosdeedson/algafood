package com.ejs.algaworksCurso.api.v1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.hateoas.PagedModel;
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

import com.ejs.algaworksCurso.api.helper.ResourceUriHelper;
import com.ejs.algaworksCurso.api.v1.model.in.pedido.PedidoIn;
import com.ejs.algaworksCurso.api.v1.model.out.formaPagamento.FormaPagamentoOut;
import com.ejs.algaworksCurso.api.v1.model.out.pedido.PedidoOut;
import com.ejs.algaworksCurso.api.v1.model.out.pedido.PedidoResumidoDTO;
import com.ejs.algaworksCurso.api.v1.openApi.controller.PedidoControllerOpenApi;
import com.ejs.algaworksCurso.domain.model.filter.PedidoFilter;
import com.ejs.algaworksCurso.domain.services.PedidoService;

@RestController
@RequestMapping("pedidos")
public class PedidoController implements PedidoControllerOpenApi {

	@Autowired
	private PedidoService pedidoService;
	

	@Override
	@DeleteMapping("{codigoPedido}/cancela")
	public ResponseEntity<Void> cancelarPedido(@PathVariable String codigoPedido) {
		this.pedidoService.cancelarPedido(codigoPedido);
		return ResponseEntity.noContent().build();
	}
	
	@Override
	@PutMapping("{codigoPedido}/confirmacao")
	public ResponseEntity<Void> confirmarPedido(@PathVariable String codigoPedido) {
		this.pedidoService.confirmarPedido(codigoPedido);
		return ResponseEntity.noContent().build();
	}
	
	@Override
	@GetMapping("{codigoPedido}")
	public ResponseEntity<PedidoOut> buscar(@PathVariable String codigoPedido){
		PedidoOut out = this.pedidoService.buscar(codigoPedido);
		return ResponseEntity.ok(out);
	}
	
	@Override
	@GetMapping("/forma-pagamento/{codigoPedido}")
	public ResponseEntity<FormaPagamentoOut> buscarFormaPagamento(@PathVariable String codigoPedido){
		FormaPagamentoOut out = this.pedidoService.buscarFormaPagamento(codigoPedido);
		return ResponseEntity.ok(out);
	}
	
	@Override
	@PutMapping("{codigoPedido}/entrega")
	public ResponseEntity<Void> entregarPedido(@PathVariable String codigoPedido) {
		this.pedidoService.entregarPedido(codigoPedido);
		return ResponseEntity.noContent().build();
	}

	@Override
	@GetMapping
	public ResponseEntity<PagedModel<PedidoResumidoDTO>> listar(@PageableDefault(size = 10) Pageable pageable,  PedidoFilter filtro){
		PagedModel<PedidoResumidoDTO> pedidos = this.pedidoService.listar(pageable, filtro);
		return ResponseEntity.ok(pedidos);
	}
	
	@Override
	@PostMapping
	public ResponseEntity<PedidoOut> salvar(@RequestBody PedidoIn pedidoIn){
		PedidoOut out = this.pedidoService.salvar(pedidoIn);
		ResourceUriHelper.addUriHeaderSave(out.getCodigo());
		return ResponseEntity.status(HttpStatus.CREATED).body(out);
	}
	
	/* para receber filtros getmapping 2*/
//	@GetMapping
//	public ResponseEntity<?> listar(@RequestParam(required = false) String campos){
//		List<PedidoResumidoDTO> pedidos = this.pedidoService.listar();
//		
//		MappingJacksonValue mappingPedidos = new MappingJacksonValue(pedidos);
//		SimpleFilterProvider simpleProvider = new SimpleFilterProvider();
//		simpleProvider.addFilter("pedidosFilter", SimpleBeanPropertyFilter.serializeAll());
//		
//		if ( StringUtils.isNotBlank(campos)) {
//			simpleProvider.addFilter("pedidosFilter", SimpleBeanPropertyFilter.filterOutAllExcept(campos.split(",")));
//		}
//		
//		mappingPedidos.setFilters(simpleProvider);
//		return ResponseEntity.ok(mappingPedidos);
//	}
	
	
}
