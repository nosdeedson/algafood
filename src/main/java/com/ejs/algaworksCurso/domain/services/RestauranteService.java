package com.ejs.algaworksCurso.domain.services;

import static com.ejs.algaworksCurso.infrastructure.repository.spec.RestauranteSpecs.comFreteGratis;
import static com.ejs.algaworksCurso.infrastructure.repository.spec.RestauranteSpecs.comNomeSemelhante;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ejs.algaworksCurso.api.model.in.restaurante.RestauranteIn;
import com.ejs.algaworksCurso.api.model.out.restautante.RestauranteOut;
import com.ejs.algaworksCurso.domain.exception.RestauranteNaoEncontradoException;
import com.ejs.algaworksCurso.domain.model.Cidade;
import com.ejs.algaworksCurso.domain.model.Cozinha;
import com.ejs.algaworksCurso.domain.model.Restaurante;
import com.ejs.algaworksCurso.domain.repository.RestauranteRepository;
import com.ejs.algaworksCurso.helper.restaurante.RestauranteAssembler;
import com.ejs.algaworksCurso.helper.restaurante.RestauranteDisAssembler;
import com.ejs.algaworksCurso.infrastructure.repository.RestauranteRepositoryCustom;

@Service
public class RestauranteService {
	
	@Autowired
	private RestauranteRepository restauranteRepository;
	
	@Autowired
	private CozinhaService cozinhaService;
	
	@Autowired
	private CidadeService cidadeService;
	
	@Autowired
	private RestauranteRepositoryCustom restauranteRepoCustom;
		
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
	public void ativarMultiplos(List<Long> restauranteIds) {
		restauranteIds.forEach(this::ativar);
	}
	
	@Transactional
	public RestauranteOut atualizar(RestauranteIn restauranteIn, Long restauranteId) {
		
		Restaurante restauranteAtual = this.buscarOuFalhar(restauranteId);
		
		Long cozinhaId = restauranteIn.getCozinha().getId();
		Long cidadeId = restauranteIn.getEndereco().getCidade().getId();
		
		Cozinha cozinha = this.cozinhaService.buscarOuFalhar(cozinhaId);
		Cidade cidade = this.cidadeService.buscarOuFalhar(cidadeId);
		restauranteAtual.setCozinha(cozinha);
		restauranteAtual.getEndereco().setCidade(cidade);
//		restauranteAtual.setFormasPagamento(Arrays.asList(new FormaPagamento()));
		restauranteAssembler.restauranteInToRestaurante(restauranteIn, restauranteAtual);
		
		restauranteAtual = this.restauranteRepository.save(restauranteAtual);
		
		return this.restauranteDisAssembler.restauranteToRestauranteOut(restauranteAtual);
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
	
	@Transactional
	public void inativarMultiplos(List<Long> restauranteIds) {
		restauranteIds.forEach(this::inativar);
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
		Long cidadeId = restauranteIn.getEndereco().getCidade().getId();
		
		Cozinha cozinha = this.cozinhaService.buscarOuFalhar(cozinhaId);
		Cidade cidade = this.cidadeService.buscarOuFalhar(cidadeId);
		Restaurante restaurante = restauranteAssembler.restauranteInToRestaurante(restauranteIn);
		
		restaurante.setCozinha(cozinha);
		restaurante.getEndereco().setCidade(cidade);
		restaurante = this.restauranteRepository.save(restaurante);
		
		return this.restauranteDisAssembler.restauranteToRestauranteOut(restaurante);
		
	}
	
	/**
	 * Metodos auxiliares
	 */
	
	public Restaurante buscarOuFalhar( Long restauranteId) {
		Restaurante retorno = this.restauranteRepository.findById(restauranteId)
				.orElseThrow( () -> new RestauranteNaoEncontradoException(restauranteId));
		return retorno;
	}
}
