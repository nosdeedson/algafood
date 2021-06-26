package com.ejs.algaworksCurso.domain.services;

import static com.ejs.algaworksCurso.infrastructure.repository.spec.RestauranteSpecs.comFreteGratis;
import static com.ejs.algaworksCurso.infrastructure.repository.spec.RestauranteSpecs.comNomeSemelhante;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ReflectionUtils;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.SmartValidator;

import com.ejs.algaworksCurso.api.model.dto.in.FormaPagamentoResumidoDTO;
import com.ejs.algaworksCurso.api.model.dto.in.RestauranteIn;
import com.ejs.algaworksCurso.api.model.dto.out.RestauranteOut;
import com.ejs.algaworksCurso.domain.exception.NegocioException;
import com.ejs.algaworksCurso.domain.exception.RestauranteNaoEncontradoException;
import com.ejs.algaworksCurso.domain.exception.ValidacaoException;
import com.ejs.algaworksCurso.domain.model.Cozinha;
import com.ejs.algaworksCurso.domain.model.FormaPagamento;
import com.ejs.algaworksCurso.domain.model.Restaurante;
import com.ejs.algaworksCurso.domain.repository.CozinhaRepository;
import com.ejs.algaworksCurso.domain.repository.FormaPagamentoRepository;
import com.ejs.algaworksCurso.domain.repository.RestauranteRepository;
import com.ejs.algaworksCurso.helper.restaurante.RestauranteAssembler;
import com.ejs.algaworksCurso.helper.restaurante.RestauranteDisAssembler;
import com.ejs.algaworksCurso.infrastructure.repository.RestauranteRepositoryCustom;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class RestauranteService {
	
	private static final String COZINHA_NAO_EXISTE = "Não existe cozinha de código %d.";

	@Autowired
	private RestauranteRepository restauranteRepository;
	
	@Autowired
	private CozinhaRepository cozinhaRepository;
	
	@Autowired
	private FormaPagamentoRepository formaPagamentoRepository;
	
	@Autowired
	private RestauranteRepositoryCustom restauranteRepoCustom;
	
	@Autowired
	private SmartValidator smartValidator;
	
	@Autowired
	private RestauranteAssembler restauranteAssembler;
	
	@Autowired
	private RestauranteDisAssembler restauranteDisAssembler;
	
	@Transactional
	public void abrir(Long restauranteId) {
		Restaurante restauranteAtual = this.buscarOuFalhar(restauranteId);
		restauranteAtual.abrir();
	}
	
	@Transactional 
	public void ativar(Long restauranteId) {
		Restaurante restauranteAtual = this.buscarOuFalhar(restauranteId);
		restauranteAtual.ativar();
	}
	
	@Transactional
	public RestauranteOut atualizar(RestauranteIn restauranteIn, Long restauranteId) {
		
		Restaurante restauranteAtual = this.buscarOuFalhar(restauranteId);
		
		Long cozinhaId = restauranteIn.getCozinha().getId();
		
		this.cozinhaRepository.findById(cozinhaId)
				.orElseThrow( () -> new NegocioException(
					String.format(COZINHA_NAO_EXISTE, cozinhaId)));
		
		restauranteAtual.setCozinha(new Cozinha());
		restauranteAtual.setFormasPagamento(Arrays.asList(new FormaPagamento()));
		restauranteAssembler.restauranteInToRestaurante(restauranteIn, restauranteAtual);
		
		restauranteAtual = this.restauranteRepository.save(restauranteAtual);
		
		return this.restauranteDisAssembler.restauranteToRestauranteOut(restauranteAtual);
	}
	
	@Transactional
	public Restaurante atualizarParcial( Map<String, Object> dados, Long id, HttpServletRequest  request) {
		ServletServerHttpRequest  sshr = new ServletServerHttpRequest(request);
		final Restaurante restauranteDestino = this.buscarOuFalhar(id);
		
		if (dados.containsKey("cozinha")) {
			Long cozinhaId = Long.parseLong( dados.get("cozinha").toString());
			this.cozinhaRepository.findById(cozinhaId)
				.orElseThrow( () -> new NegocioException(
						String.format(COZINHA_NAO_EXISTE, cozinhaId)));
		}
		
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, true);
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);
		try {
			Restaurante restauranteOrigem = objectMapper.convertValue(dados, Restaurante.class);			
			dados.forEach((nomeCampo, valorCampo) ->{
				Field field = ReflectionUtils.findField(Restaurante.class, nomeCampo);
				field.setAccessible(true);
				Object nonoValor = ReflectionUtils.getField(field, restauranteOrigem);
				ReflectionUtils.setField(field, restauranteDestino, nonoValor);
			});
			this.validarRestaurante(restauranteDestino);
			return null;
//			return this.atualizar(restauranteDestino, id);
		} catch (IllegalArgumentException e) {
			Throwable rootCause = ExceptionUtils.getRootCause(e);
			throw new HttpMessageNotReadableException("teste", rootCause, sshr);
		}
		
	}
	
	public RestauranteOut buscar(Long restauranteId) {	
		Restaurante retorno = this.buscarOuFalhar(restauranteId);
		
		return this.restauranteDisAssembler.restauranteToRestauranteOut(retorno);
	}		
	
	public List<RestauranteOut> encontrarComFreteGratis(String nome){
		/*Exemplo com classe*/
//		RestauranteComFreteGratisSpec freteGratis = new RestauranteComFreteGratisSpec();
//		RestauranteComNomeSemelhanteSpec nomeSemelhante = new RestauranteComNomeSemelhanteSpec(nome);
//		
//		return this.restauranteRepository.findAll(nomeSemelhante.and(freteGratis));
		
		/*Exemplo com fabrica de specificatio*/
		List<Restaurante> restaurantes = this.restauranteRepository.findAll(comFreteGratis().and(comNomeSemelhante(nome)));
		return restaurantes.stream()
				.map(item -> this.restauranteDisAssembler.restauranteToRestauranteOut(item))
				.collect(Collectors.toList());
	}
	
	public RestauranteOut encontrarPrimeiro() {
		Restaurante restaurante = this.restauranteRepository.buscarPrimeiro()
		.orElseThrow(() -> new RestauranteNaoEncontradoException("Nenhum restaurante encontrado."));
		
		return this.restauranteDisAssembler.restauranteToRestauranteOut(restaurante);
	}
	
	@Transactional
	public void fechar(Long restauranteId) {
		Restaurante restauranteAtual = this.buscarOuFalhar(restauranteId);
		restauranteAtual.fechar();
	}
	
	public List<RestauranteOut> find(String nome, BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal){
		List<Restaurante> restaurantes = restauranteRepoCustom.find(nome, taxaFreteInicial, taxaFreteFinal);
		return restaurantes.stream()
				.map(item -> this.restauranteDisAssembler.restauranteToRestauranteOut(item))
				.collect(Collectors.toList());
	}
	
	@Transactional
	public void inativar(Long restauranteId) {
		Restaurante restauranteAtual = this.buscarOuFalhar(restauranteId);
		restauranteAtual.inativar();
	}
	
	public List<RestauranteOut> listar(){
		List<Restaurante> restaurantes = this.restauranteRepository.todas(Sort.by("nome"));
		
		List<RestauranteOut> restauranteOuts = restaurantes.stream()
				.map(rest -> 
				restauranteDisAssembler.restauranteToRestauranteOut(rest))
				.collect(Collectors.toList());
		return restauranteOuts;
	}

	
	@Transactional
	public RestauranteOut salvar(RestauranteIn restauranteIn) {
		
		Long cozinhaId = restauranteIn.getCozinha().getId();
		
		Cozinha cozinha = this.cozinhaRepository.findById(cozinhaId)
				.orElseThrow( () -> new NegocioException(
					String.format(COZINHA_NAO_EXISTE, cozinhaId)));
			
		Restaurante restaurante = restauranteAssembler.restauranteInToRestaurante(restauranteIn);
		
		restaurante.setCozinha(cozinha);
		
		restaurante = this.restauranteRepository.save(restaurante);
		
		return this.restauranteDisAssembler.restauranteToRestauranteOut(restaurante);
		
	}
	
	/**
	 * Metodos auxiliares
	 */

	/**
	 * valida as formas de pagamento
	 * @param formasPagamento
	 */
	private void validarFormasPagamento( List<FormaPagamentoResumidoDTO> formasPagamento) {
		
		for (FormaPagamentoResumidoDTO formaPagamentoResumidoDTO : formasPagamento) {
			this.formaPagamentoRepository.findById(formaPagamentoResumidoDTO.getId())
			.orElseThrow( () -> new NegocioException(
					String.format("Forma pagamento de código %d não existe.", formaPagamentoResumidoDTO.getId())));
		}
		

	}
	
	private void validarRestaurante(Restaurante restauranteDestino) {
		BeanPropertyBindingResult bindingResults = new BeanPropertyBindingResult(restauranteDestino, "restaurante");
		smartValidator.validate(restauranteDestino, bindingResults);
		if( bindingResults.hasErrors()) {
			throw new ValidacaoException(bindingResults);
		}
	}
	
	private Restaurante buscarOuFalhar( Long restauranteId) {
		Restaurante retorno = this.restauranteRepository.findById(restauranteId)
				.orElseThrow( () -> new RestauranteNaoEncontradoException(restauranteId));
		return retorno;
	}
}
