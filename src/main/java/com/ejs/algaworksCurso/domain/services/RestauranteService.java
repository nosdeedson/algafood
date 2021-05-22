package com.ejs.algaworksCurso.domain.services;

import static com.ejs.algaworksCurso.infrastructure.repository.spec.RestauranteSpecs.comFreteGratis;
import static com.ejs.algaworksCurso.infrastructure.repository.spec.RestauranteSpecs.comNomeSemelhante;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import com.ejs.algaworksCurso.domain.exception.EntidadeNaoEncontradaException;
import com.ejs.algaworksCurso.domain.model.Cozinha;
import com.ejs.algaworksCurso.domain.model.FormaPagamento;
import com.ejs.algaworksCurso.domain.model.Restaurante;
import com.ejs.algaworksCurso.domain.repository.CozinhaRepository;
import com.ejs.algaworksCurso.domain.repository.FormaPagamentoRepository;
import com.ejs.algaworksCurso.domain.repository.RestauranteRepository;
import com.ejs.algaworksCurso.infrastructure.repository.RestauranteRepositoryCustom;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class RestauranteService {
	
	@Autowired
	private RestauranteRepository restauranteRepository;
	
	@Autowired
	private CozinhaRepository cozinhaRepository;
	
	@Autowired
	private FormaPagamentoRepository formaPagamentoRepository;
	
	@Autowired
	private RestauranteRepositoryCustom restauranteRepoCustom;
	
	@Transactional
	public Restaurante adicionar(Restaurante restaurante) {
		
		Long cozinhaId = restaurante.getCozinha().getId();
		
		Cozinha cozinha = this.cozinhaRepository.findById(cozinhaId)
				.orElseThrow( () -> new EntidadeNaoEncontradaException(
					String.format("Não existe cozinha de código %d", cozinhaId)));
		
		this.validarFormasPagamento(restaurante.getFormasPagamento());	
		restaurante.setCozinha(cozinha);
		restaurante.setDataAtualizacao(LocalDateTime.now());
		restaurante.setDataCadastro(LocalDateTime.now());
		
		return this.restauranteRepository.save(restaurante);
		
	}
	
	@Transactional
	public Restaurante atualizar(Restaurante restaurante, Long restauranteId) {
		
		Restaurante restauranteAtual = this.restauranteRepository.findById(restauranteId)
				.orElseThrow( () -> new EntidadeNaoEncontradaException(
					String.format("Restaurante de código %d não existe.", restauranteId)));
		
		Long cozinhaId = restaurante.getCozinha().getId();
		
		Cozinha cozinhaAtual =  this.cozinhaRepository.findById(cozinhaId)
				.orElseThrow( () -> new EntidadeNaoEncontradaException(
					String.format("Cozinha de código %d não existe", cozinhaId)));
		
		this.validarFormasPagamento(restaurante.getFormasPagamento());
		
		BeanUtils.copyProperties(restaurante, restauranteAtual, "id", "dataCadastro", "formasPagamento", 
				"endereco");
		
		restauranteAtual.setDataAtualizacao(LocalDateTime.now());
		restauranteAtual.setCozinha(cozinhaAtual);
		return this.restauranteRepository.save(restauranteAtual);
	}
	
	public Restaurante atualizarParcial( Map<String, Object> dados, Long id) {
		
		final Restaurante restauranteDestino = this.restauranteRepository.findById(id)
				.orElseThrow( () ->  new EntidadeNaoEncontradaException(
					String.format("Restaurante de código %d não encontrado.", id)));
		
		ObjectMapper objectMapper = new ObjectMapper();
		Restaurante restauranteOrigem = objectMapper.convertValue(dados, Restaurante.class);
		
		dados.forEach((nomeCampo, valorCampo) ->{
			Field field = ReflectionUtils.findField(Restaurante.class, nomeCampo);
			field.setAccessible(true);
			Object nonoValor = ReflectionUtils.getField(field, restauranteOrigem);
			ReflectionUtils.setField(field, restauranteDestino, nonoValor);
		});
		
		 return this.atualizar(restauranteDestino, id);
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
		.orElseThrow(() -> new EntidadeNaoEncontradaException("Nenhum dado encontrado."));
	}
	
	public Restaurante buscar(Long restauranteId) {	
			Restaurante retorno = this.restauranteRepository.findById(restauranteId)
					.orElseThrow( () -> new EntidadeNaoEncontradaException(
					String.format("Restaurante de código %d não encotrado", restauranteId)));
			return retorno;
	}	
	
	public List<Restaurante> listar(){
		return this.restauranteRepository.findAll(Sort.by("nome"));
	}
	
	public List<Restaurante> find(String nome, BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal){
		return restauranteRepoCustom.find(nome, taxaFreteInicial, taxaFreteFinal);
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
			FormaPagamento fp = this.formaPagamentoRepository.findById(formaPagamento.getId())
					.orElseThrow( () -> new EntidadeNaoEncontradaException(
						String.format("Forma pagamento de código %d não existe.", formaPagamento.getId())));
		}
	}
}
