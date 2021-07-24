package com.ejs.algaworksCurso;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import javax.validation.ConstraintViolationException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ejs.algaworksCurso.api.model.in.cozinha.CozinhaIn;
import com.ejs.algaworksCurso.domain.exception.EntidadeEmUsoException;
import com.ejs.algaworksCurso.domain.exception.EntidadeNaoEncontradaException;
import com.ejs.algaworksCurso.domain.model.Cozinha;
import com.ejs.algaworksCurso.domain.repository.CozinhaRepository;
import com.ejs.algaworksCurso.domain.services.CozinhaService;

@SpringBootTest
class CadastroCozinhaIntegracaoTestsIT {
	
	/*
	 * Classes para teste de integração devem terminar com o sufixo IT
	 * quando estiver usando o plugin abaixo
	 * <plugin><artifactId>maven-failsafe-plugin</artifactId></plugin>
	 * 
	 * 
	 */
	
	@Autowired
	CozinhaService cozinhaService;

	@Autowired
	CozinhaRepository repository;
	
	@Test
	public void deveAtribuirId_QuandoOsDadosEstaoCorretos() {
		
		//cenário
		Cozinha novaCozinha = new Cozinha();
		novaCozinha.setNome("Americana");
		//Ação
		novaCozinha = repository.save(novaCozinha);
		//validação
		assertThat(novaCozinha).isNotNull();
		assertThat(novaCozinha.getId()).isNotNull();
		
	}
	
	@Test 
	public void deveFalhar_QuandoTentarCadastrarCozinhaSemNome() {
		//cenário
		CozinhaIn novaCozinha = new CozinhaIn();
		novaCozinha.setNome(null);
		//Ação
		assertThrows(ConstraintViolationException.class, () -> { cozinhaService.salvar(novaCozinha);});
	}
	
	@Test
	public void deveFalhar_QuandoExcluirUmaCozinhaEmUso() {
		Long teste = Long.valueOf("1");
		assertThrows(EntidadeEmUsoException.class, () -> {cozinhaService.remover(teste);});
	}
	
	@Test
	public void deveFalhar_QuandoExcluirUmaCozinhaInexistente() {
		assertThrows(EntidadeNaoEncontradaException.class, () -> {cozinhaService.remover(100L);});
	}

}
