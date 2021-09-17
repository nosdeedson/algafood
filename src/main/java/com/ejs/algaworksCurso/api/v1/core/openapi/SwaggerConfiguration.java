package com.ejs.algaworksCurso.api.v1.core.openapi;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.LinkRelation;
import org.springframework.hateoas.Links;
import org.springframework.hateoas.PagedModel;
import org.springframework.hateoas.PagedModel.PageMetadata;
import org.springframework.hateoas.UriTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.ejs.algaworksCurso.api.exceptionHandler.Problem;
import com.ejs.algaworksCurso.api.v1.model.out.cidade.CidadeOut;
import com.ejs.algaworksCurso.api.v1.model.out.cozinha.CozinhaOut;
import com.ejs.algaworksCurso.api.v1.model.out.estado.EstadoOut;
import com.ejs.algaworksCurso.api.v1.model.out.pedido.PedidoOut;
import com.ejs.algaworksCurso.api.v1.openApi.model.CidadesModelOpenapi;
import com.ejs.algaworksCurso.api.v1.openApi.model.CozinhaModelHateoasOpenApi;
import com.ejs.algaworksCurso.api.v1.openApi.model.EstadosModelOpenApi;
import com.ejs.algaworksCurso.api.v1.openApi.model.FormasPagamentoModelOpenApi;
import com.ejs.algaworksCurso.api.v1.openApi.model.GruposModelOpenApi;
import com.ejs.algaworksCurso.api.v1.openApi.model.LinksModelOpenApi;
import com.ejs.algaworksCurso.api.v1.openApi.model.PageableModelOpenApi;
import com.ejs.algaworksCurso.api.v1.openApi.model.PedidosModelHateoasOpenApi;
import com.ejs.algaworksCurso.api.v1.openApi.model.PermissoesModelOpenApi;
import com.ejs.algaworksCurso.api.v1.openApi.model.ProdutosModelOpenApin;
import com.ejs.algaworksCurso.api.v1.openApi.model.RestaurantesModelOpenApi;
import com.ejs.algaworksCurso.api.v1.openApi.model.UsuariosModelOpenApi;
import com.ejs.algaworksCurso.api.v2.model.out.cidade.CidadeOutV2;
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
	public Docket apiV1() {
		return new Docket(DocumentationType.SWAGGER_2)
					.groupName("V1")
					.select()
					.apis(RequestHandlerSelectors.basePackage("com.ejs.algaworksCurso.api.v1"))
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
				.ignoredParameterTypes(ServletWebRequest.class, URI.class, ModelAndView.class, UriTemplate.class, Link.class, LinkRelation.class, 
						PageMetadata.class)
				.directModelSubstitute(Pageable.class, PageableModelOpenApi.class)
				.directModelSubstitute(Links.class, LinksModelOpenApi.class)
// 				.alternateTypeRules(AlternateTypeRules usado para corrigir a documentação sem o Hateoas
//						.newRule(typeResolver.resolve(Page.class, CozinhaOut.class), CozinhasModelOpenApi.class))
				
				
				.alternateTypeRules(AlternateTypeRules 
						.newRule(typeResolver.resolve(PagedModel.class, CozinhaOut.class), CozinhaModelHateoasOpenApi.class))
				
				.alternateTypeRules(AlternateTypeRules 
						.newRule(typeResolver.resolve(ResponseEntity.class, CollectionModel.class), ProdutosModelOpenApin.class))
	
				
				.alternateTypeRules(AlternateTypeRules 
						.newRule(typeResolver.resolve(PagedModel.class, PedidoOut.class), PedidosModelHateoasOpenApi.class))
				
				.alternateTypeRules(AlternateTypeRules
						.newRule(typeResolver.resolve(CollectionModel.class, CidadeOut.class), CidadesModelOpenapi.class))
				
				.alternateTypeRules(AlternateTypeRules
						.newRule(typeResolver.resolve(CollectionModel.class, EstadoOut.class), EstadosModelOpenApi.class))
				
				.alternateTypeRules(AlternateTypeRules
						.newRule(typeResolver.resolve(ResponseEntity.class, CollectionModel.class), UsuariosModelOpenApi.class))
				
				.alternateTypeRules(AlternateTypeRules
						.newRule(typeResolver.resolve(ResponseEntity.class, CollectionModel.class), RestaurantesModelOpenApi.class))
				
				.alternateTypeRules(AlternateTypeRules
						.newRule(typeResolver.resolve(ResponseEntity.class, CollectionModel.class), FormasPagamentoModelOpenApi.class))
				
				.alternateTypeRules(AlternateTypeRules
						.newRule(typeResolver.resolve(ResponseEntity.class, CollectionModel.class), GruposModelOpenApi.class))
				
				.alternateTypeRules(AlternateTypeRules
						.newRule(typeResolver.resolve(ResponseEntity.class, CollectionModel.class), PermissoesModelOpenApi.class))
				
				.tags( 
						new Tag("Cidades", "Gerencia cidades"), 
						new Tag("Grupos", "Gerencia grupos"),
						new Tag("Estados", "Gerencia estados"),
						new Tag("Cozinhas", "Gerencia cozinhas"), 
						new Tag("Estatisticas", "Gerencia estatísticas"),
						new Tag("Forma Pagamentos", "Gerencia formas de pagamento"),
						new Tag("Grupo Permissão", "Gerencia a relação entre grupos e permissão"), 
						new Tag("Pedidos", "Gerencia pedidos"),
						new Tag("Restaurantes", "Gerencia restaurantes"),
						new Tag("Restaurante Formas Pagamento", "Gerencia a relação entre restaurantes e formas de pagamento"), 
						new Tag("Foto Produto", "Faz upload de foto de produto"),
						new Tag("Restaurantes Produtos", "Gerencia a relação entre restaurantes e produtos"),
						new Tag("Restaurante Proprietários", "Gerencia a relação entre restaurantes e proprietários"), 
						new Tag("Usuário", "Gerencia usuários"),
						new Tag("Usuário Grupos", "Gerencia a relação entre usuários e grupos"),
						new Tag("Home", "Home"),
						new Tag("Permissões", "Lista as permissões"),
						new Tag("RootEntryPoint", "RootEntryPoint")
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
	
	
	@Bean
	public Docket apiV2() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("v2")
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.ejs.algaworksCurso.api.v2"))
//				.paths(PathSelectors.ant("v2/**"))
				.build()
				.useDefaultResponseMessages(false)
				.globalResponseMessage(RequestMethod.GET, this.globalGetResponseMessage())
				.globalResponseMessage(RequestMethod.POST, this.globalPostAndPutResponseMessage())
				.globalResponseMessage(RequestMethod.PUT, this.globalPostAndPutResponseMessage())
				.globalResponseMessage(RequestMethod.DELETE, this.globalDeleteResponseMessage())

				.apiInfo(this.apiInfoV2());

	}
	
	private ApiInfo apiInfoV2() {
		
		return new ApiInfoBuilder()
				.title("API do curso Realizado na AlgaWorks")
				.description("Api para delevery de comida tipo Ifood. <br> "
						+ "Exemplo de como é feito o versionamento de uma API.")
				.contact( new Contact("Edson José de Souza", "https://nosdeedson.github.io/", 
						"nosdejs32@gmail.com"))
				.version("2")
				.build();
	}
}
