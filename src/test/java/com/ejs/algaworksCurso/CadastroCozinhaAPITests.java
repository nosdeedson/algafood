package com.ejs.algaworksCurso;



import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestPropertySource("/application-test.properties")
public class CadastroCozinhaAPITests {
	
	/*
	 * para adicionar uma expotação static
	 * window->preferences->java->editor->content assist-> favorites
	 * colocar conteudo do import
	 */
	
	@LocalServerPort
	private int port;
	
	@BeforeEach
	public void setUp() {
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
		RestAssured.basePath = "/cozinhas";
		RestAssured.port = port;
	}
	
	@Test
	public void deveRetornarStatus200_QuandoConsultarCozinhas() {
		
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
		
		RestAssured.given()
			.accept(ContentType.JSON)
		.when()
			.get()
		.then()
			.statusCode(HttpStatus.OK.value());
	}
	
	@Test
	public void deveEncontrar15Cozinhas_QuandoConsultarCozinhas() {
		
		RestAssured.given()
			.accept(ContentType.JSON)
		.when()
			.get()
		.then()
			.body("nome", Matchers.hasItems("Vietinamita"));
	}
	
	@Test
	public void deveRetornarStatus200_QuandoConsultarCozinhasPorId() {
		
		
		RestAssured.given()
			.pathParam("id", 5)
			.accept(ContentType.JSON)
		.when()
			.get()
		.then()
			.statusCode(HttpStatus.OK.value());
	}
	
	@Test
	public void deveStatus201_QuandoCadastrarCozinha() {
		
		RestAssured.given()
			.body("{ \"nome\": \"Vietinamita\" }")
			.accept(ContentType.JSON)
			.contentType(ContentType.JSON)
		.when()
			.post()
		.then()
			.statusCode(HttpStatus.CREATED.value());
	}

}
