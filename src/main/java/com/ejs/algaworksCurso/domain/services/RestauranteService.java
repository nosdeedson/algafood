package com.ejs.algaworksCurso.domain.services;

import static com.ejs.algaworksCurso.infrastructure.repository.spec.RestauranteSpecs.comFreteGratis;
import static com.ejs.algaworksCurso.infrastructure.repository.spec.RestauranteSpecs.comNomeSemelhante;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import com.ejs.algaworksCurso.domain.exception.NegocioException;
import com.ejs.algaworksCurso.domain.exception.RestauranteNaoEncontradoException;
import com.ejs.algaworksCurso.domain.model.Cozinha;
import com.ejs.algaworksCurso.domain.model.FormaPagamento;
import com.ejs.algaworksCurso.domain.model.Restaurante;
import com.ejs.algaworksCurso.domain.repository.CozinhaRepository;
import com.ejs.algaworksCurso.domain.repository.FormaPagamentoRepository;
import com.ejs.algaworksCurso.domain.repository.RestauranteRepository;
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
	
	
	@Transactional
	public Restaurante atualizar(Restaurante restaurante, Long restauranteId) {
		
		Restaurante restauranteAtual = this.buscar(restauranteId);
		
		Long cozinhaId = restaurante.getCozinha().getId();
		
		Cozinha cozinhaAtual =  this.cozinhaRepository.findById(cozinhaId)
				.orElseThrow( () -> new NegocioException(
					String.format(COZINHA_NAO_EXISTE, cozinhaId)));
		
		this.validarFormasPagamento(restaurante.getFormasPagamento());
		
		BeanUtils.copyProperties(restaurante, restauranteAtual, "id", "dataCadastro", "formasPagamento", 
				"endereco");
		
		restauranteAtual.setCozinha(cozinhaAtual);
		return this.restauranteRepository.save(restauranteAtual);
	}
	
	public Restaurante atualizarParcial( Map<String, Object> dados, Long id, HttpServletRequest  request) {
		ServletServerHttpRequest  sshr = new ServletServerHttpRequest(request);
		final Restaurante restauranteDestino = this.buscar(id);
		
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
			
			return this.atualizar(restauranteDestino, id);
		} catch (IllegalArgumentException e) {
			Throwable rootCause = ExceptionUtils.getRootCause(e);
			throw new HttpMessageNotReadableException("teste", rootCause, sshr);
		}
		
	}
	
	public Restaurante buscar(Long restauranteId) {	
		Restaurante retorno = this.restauranteRepository.findById(restauranteId)
				.orElseThrow( () -> new RestauranteNaoEncontradoException(restauranteId));
		return retorno;
	}		
	
	public List<Restaurante> encontrarComFreteGratis(String nome){
		/*Exemple com classe*/
//		RestauranteComFreteGratisSpec freteGratis = new RestauranteComFreteGratisSpec();
//		RestauranteComNomeSemelhanteSpec nomeSemelhante = new RestauranteComNomeSemelhanteSpec(nome);
//		
//		return this.restauranteRepository.findAll(nomeSemelhante.and(freteGratis));
		
		/*Exemplo com fabrica de specificatio*/
		
		return this.restauranteRepository.findAll(comFreteGratis().and(comNomeSemelhante(nome)));
	}
	
	public Restaurante encontrarPrimeiro() {
		return this.restauranteRepository.buscarPrimeiro()
		.orElseThrow(() -> new RestauranteNaoEncontradoException("Nenhum restaurante encontrado."));
	}
	
	
	public List<Restaurante> find(String nome, BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal){
		return restauranteRepoCustom.find(nome, taxaFreteInicial, taxaFreteFinal);
	}
	
	
	public List<Restaurante> listar(){
		return this.restauranteRepository.todas(Sort.by("nome"));
	}

	
	@Transactional
	public Restaurante salvar(Restaurante restaurante) {
		
		Long cozinhaId = restaurante.getCozinha().getId();
		
		Cozinha cozinha = this.cozinhaRepository.findById(cozinhaId)
				.orElseThrow( () -> new NegocioException(
					String.format(COZINHA_NAO_EXISTE, cozinhaId)));
		
		this.validarFormasPagamento(restaurante.getFormasPagamento());	
		restaurante.setCozinha(cozinha);
		
		return this.restauranteRepository.save(restaurante);
		
	}
	
	/**
	 * Metodos auxiliares
	 */

	/**
	 * valida as formas de pagamento
	 * @param formasPagamento
	 */
	public void validarFormasPagamento( List<FormaPagamento> formasPagamento) {
		
		for (FormaPagamento formaPagamento : formasPagamento) {
			this.formaPagamentoRepository.findById(formaPagamento.getId())
					.orElseThrow( () -> new NegocioException(
						String.format("Forma pagamento de código %d não existe.", formaPagamento.getId())));
		}
	}
}
