package com.ejs.algaworksCurso.api.core.openapi;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.ejs.algaworksCurso.api.exceptionHandler.Problem;
import com.ejs.algaworksCurso.api.model.out.cozinha.CozinhaOut;
import com.ejs.algaworksCurso.api.openApi.model.CozinhasModelOpenApi;
import com.ejs.algaworksCurso.api.openApi.model.PageableModelOpenApi;
import com.fasterxml.classmate.TypeResolver;

import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.AlternateTypeRules;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@Import(BeanValidatorPluginsConfiguration.class)
public class SwaggerConfiguration implements WebMvcConfigurer {
	
	TypeResolver typeResolver = new TypeResolver();
	
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.ejs.algaworksCurso.api.controller"))
				.build()
				.useDefaultResponseMessages(false)
				.globalResponseMessage(RequestMethod.GET, this.globalGetResponseMessage())
				.globalResponseMessage(RequestMethod.POST, this.globalPostAndPutResponseMessage())
				.globalResponseMessage(RequestMethod.PUT, this.globalPostAndPutResponseMessage())
				.globalResponseMessage(RequestMethod.DELETE, this.globalDeleteResponseMessage())
//				.globalOperationParameters(
//							Arrays.asList(
//									new ParameterBuilder()
//										.name("campos")
//										.description("Nome dos atributos a serem devolvidos na resposta, separados por vírgulas.")
//										.parameterType("query")
//										.modelRef(new ModelRef("Strig"))
//										.build()))
				.apiInfo(this.apiInfo())
				.ignoredParameterTypes(ServletWebRequest.class)
				.directModelSubstitute(Pageable.class, PageableModelOpenApi.class)
				.alternateTypeRules(AlternateTypeRules
						.newRule(typeResolver.resolve(Page.class, CozinhaOut.class), CozinhasModelOpenApi.class))
				.tags( 
						new Tag("Cidades", "Gerencia cidades"), 
						new Tag("Grupos", "Gerencia grupos"),
						new Tag("Estados", "Gerencia estados"),
						new Tag("Cozinhas", "Gerencia cozinhas"), 
						new Tag("Estatísticas", "Gerencia estatísticas"),
						new Tag("Forma de Pagamentos", "Gerencia formas de pagamento"),
						new Tag("Grupo Permissão", "Gerencia a relação entre grupos e permissão"), 
						new Tag("Pedidos", "Gerencia pedidos"),
						new Tag("Restaurantes", "Gerencia restaurantes"),
						new Tag("Restaurante Formas Pagamento", "Gerencia a relação entre restaurantes e formas de pagamento"), 
						new Tag("Foto Produto", "Faz upload de foto de produto"),
						new Tag("Restaurantes Produtos", "Gerencia a relação entre restaurantes e produtos"),
						new Tag("Restaurante Proprietários", "Gerencia a relação entre restaurantes e proprietários"), 
						new Tag("Usuário", "Gerencia usuários"),
						new Tag("Usuário Grupos", "Gerencia a relação entre usuários e grupos")
					)
				.additionalModels(typeResolver.resolve(Problem.class));
	}
	
	private List<ResponseMessage> globalGetResponseMessage(){
		return Arrays.asList(
					new ResponseMessageBuilder()
						.code(HttpStatus.INTERNAL_SERVER_ERROR.value())
						.message("Erro interno do servidor (Sistema)")
						.responseModel(new ModelRef("Problema"))
						.build(),
					new ResponseMessageBuilder()
						.code(HttpStatus.NOT_ACCEPTABLE.value())
						.message("Servidor não atende a repesentação solicitada")
						.build(),
					new ResponseMessageBuilder()
						.code(HttpStatus.NOT_FOUND.value())
						.message("Recurso não encontrado")
						.build(),
					new ResponseMessageBuilder()
						.code(HttpStatus.BAD_REQUEST.value())
						.responseModel(new ModelRef("Problema"))
						.message("Requisição mal formada")
						.build()
				);
	}
	
	private List<ResponseMessage> globalPostAndPutResponseMessage(){
		return Arrays.asList(
					new ResponseMessageBuilder()
						.code(HttpStatus.INTERNAL_SERVER_ERROR.value())
						.message("Erro interno do servidor (Sistema)")
						.responseModel(new ModelRef("Problema"))
						.build(),
					new ResponseMessageBuilder()
						.code(HttpStatus.NOT_ACCEPTABLE.value())
						.message("Servidor não atende a repesentação solicitada")
						.build(),
					new ResponseMessageBuilder()
						.code(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value())
						.message("Media Type não suportada")
						.build(),
					new ResponseMessageBuilder()
						.code(HttpStatus.BAD_REQUEST.value())
						.message("Requisição mal formada")
						.responseModel(new ModelRef("Problema"))
						.responseModel(new ModelRef("Problema"))
						.build(),
					new ResponseMessageBuilder()
						.code(HttpStatus.NOT_FOUND.value())
						.message("Resurso depende de objeto não encontrado")
						.build()
				);
	}
	
	private List<ResponseMessage> globalDeleteResponseMessage(){
		return Arrays.asList(
					new ResponseMessageBuilder()
						.code(HttpStatus.INTERNAL_SERVER_ERROR.value())
						.message("Erro interno do servidor (Sistema)")
						.responseModel(new ModelRef("Problema"))
						.build(),
					new ResponseMessageBuilder()
						.code(HttpStatus.BAD_REQUEST.value())
						.message("Requisição mal formada")
						.responseModel(new ModelRef("Problema"))
						.build(),
					new ResponseMessageBuilder()
						.code(HttpStatus.NOT_FOUND.value())
						.message("Resurso depende de objeto não encontrado")
						.build()
				);
	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("swagger-ui.html")
		.addResourceLocations("classpath:/META-INF/resources/");
		
		registry.addResourceHandler("webjars/**")
		.addResourceLocations("classpath:/META-INF/resources/webjars/");
	}
	
	private ApiInfo apiInfo() {
//		return new ApiInfo("API do curso Realizado na AlgaWorks",
//				"Esta API é utilizada", "Versão 1.0",
//				"Curso Spring",
//				new Contact("Edson Jose de Souza", "AlgaWorks", "nosdejs32@gmail.com"),
//				"Permitido uso para estudantes", "teste", 
//				Collections.emptyList() 
//		);
		
		return new ApiInfoBuilder()
				.title("API do curso Realizado na AlgaWorks")
				.description("Api para delevery de comida tipo Ifood")
				.contact( new Contact("Edson José de Souza", "https://nosdeedson.github.io/", 
						"nosdejs32@gmail.com"))
				.version("1")
				.build();
	}
}
