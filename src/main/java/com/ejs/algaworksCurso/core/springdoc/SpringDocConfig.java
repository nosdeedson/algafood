package com.ejs.algaworksCurso.core.springdoc;

import com.ejs.algaworksCurso.api.exceptionHandler.CampoComErro;
import com.ejs.algaworksCurso.api.exceptionHandler.Problem;
import io.swagger.v3.core.converter.ModelConverter;
import io.swagger.v3.core.converter.ModelConverters;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.OAuthFlow;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.media.Content;
import io.swagger.v3.oas.models.media.MediaType;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.responses.ApiResponses;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.tags.Tag;
import org.springdoc.core.customizers.OpenApiCustomiser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Configuration
public class SpringDocConfig {

    private static final String BAD_REQUEST_RESPONSE = "BadRequestResponse";
    private static final String NOT_FOUND_RESPONSE = "NotFoundResponse";
    private static final String NOT_ACCEPTABE_RESPONSE = "NotAcceptabkeResponse";
    private static final String CONFLICT_RESPONSE = "ConflictResponse";
    private static final String INTERNAL_SERVER_ERROR_RESPONSE = "InternalServerErrorResponse";

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(
                        new Info()
                                .title("Alga Food API")
                                .version("v1")
                                .description("REST API ALGAFOOD curso Algaworks")
                                .license(new License()
                                        .name("Apache 2.0")
                                        .url("http://springdoc.com")
                                )
                ).externalDocs(new ExternalDocumentation()
                        .description("Curso AlgaWorks")
                        .url("https://algaworks.com"))
                .components(
                        new Components()
                                .schemas(gerarSchemas())
                                .addSecuritySchemes("bearer-token",
                                        new SecurityScheme()
                                                .type(SecurityScheme.Type.APIKEY)
                                                .scheme("bearer")
                                                .bearerFormat("JWT")))
                .tags(
                        Arrays.asList(
                                new Tag().name("Cidades").description("Gerencia cidades"),
                                new Tag().name("Cozinhas").description("Gerencia cozinhas"),
                                new Tag().name("Usuários").description("Gerencia usuários"),
                                new Tag().name("Usuários/Grupo").description("Vincula Usuários a Grupos"),
                                new Tag().name("Restaurantes").description("Gerencia restaurantes"),
                                new Tag().name("Restaurante/Usuários").description("Vincula restaurantes a usuários"),
                                new Tag().name("Restaurante/Produto").description("Vincula restaurantes a produtos"),
                                new Tag().name("Restaurantes/Foto").description("Vincula Foto a produto do restaurante"),
                                new Tag().name("Restaurantes/Formas Pagamento").description("Vincula restaurantes a formas de pagamento"),
                                new Tag().name("Permissões").description("Gerencia Pemissões"),
                                new Tag().name("Pedidos").description("Gerencia Pedidos"),
                                new Tag().name("Grupos").description("Gerencia Grupos"),
                                new Tag().name("Grupos/Permissões").description("Vincula grupo a permissões"),
                                new Tag().name("Formas Pagamento").description("Gerencia formas de pagamento"),
                                new Tag().name("Estados").description("Gerencia Estados"),
                                new Tag().name("Root Endpoints").description("Retorna os endoints da API")
                        )
                );
    }

    @Bean
    public OpenApiCustomiser openApiCustomiser() {
        return openApi -> {
            openApi.getPaths()
                    .values()
                    .forEach(pathItem -> pathItem.readOperationsMap()
                            .forEach(((httpMethod, operation) -> {
                                ApiResponses responses = operation.getResponses();
                                switch (httpMethod) {
                                    case POST:
                                    case PUT:
                                        responses.addApiResponse("400", new ApiResponse().description("Bad Request."));
                                        responses.addApiResponse("404", new ApiResponse().description("Não encontrado."));
                                        responses.addApiResponse("406", new ApiResponse().description("Não existe representação"));
                                        responses.addApiResponse("409", new ApiResponse().description("Existe conflito na requisião com os dados do banco de dados"));
                                        responses.addApiResponse("500", new ApiResponse().description("Erro interno no servidor"));
                                        break;
                                    case GET:
                                        responses.addApiResponse("400", new ApiResponse().description("Bad Request."));
                                        responses.addApiResponse("404", new ApiResponse().description("Não encontrado."));
                                        responses.addApiResponse("500", new ApiResponse().description("Erro interno no servidor"));
                                        break;
                                    case DELETE:
                                        responses.addApiResponse("400", new ApiResponse().description("Bad Request."));
                                        responses.addApiResponse("404", new ApiResponse().description("Não encontrado."));
                                        responses.addApiResponse("409", new ApiResponse().description("Existe conflito na requisião com os dados do banco de dados"));
                                        responses.addApiResponse("500", new ApiResponse().description("Erro interno no servidor"));
                                        break;
                                }
                            }))
                    );
        };
    }

    private Map<String, Schema> gerarSchemas() {
        Map<String, Schema> schemaMap = new HashMap<>();

        Map<String, Schema> problemSchema = ModelConverters.getInstance().read(Problem.class);
//        Map<String, Schema> campoComErroSchema = ModelConverters.getInstance().read(CampoComErro.class);

        schemaMap.putAll(problemSchema);
//        schemaMap.putAll(campoComErroSchema);
        return schemaMap;
    }

//    private Map<String, ApiResponse> gerarResponses(){
//        Map<String, ApiResponse> apiResponseMap = new HashMap<>();
//        Content content = new Content()
//                .addMediaType(APPLICATION_JSON_VALUE,
//                        new MediaType().schema(new Schema<Problem>().$ref("Problem")));
//
//        apiResponseMap.put(BAD_REQUEST_RESPONSE, new ApiResponse().description("Bad Request.").content(content) ); //400
//        apiResponseMap.put(NOT_FOUND_RESPONSE, new ApiResponse().description("Não encontrado").content(content) ); //404
//        apiResponseMap.put(NOT_ACCEPTABE_RESPONSE, new ApiResponse().description("Não existe representação").content(content) );//406
//        apiResponseMap.put(INTERNAL_SERVER_ERROR_RESPONSE, new ApiResponse().description("Erro interno do servidor").content(content) );//500
//
//        return apiResponseMap;
//    }
}
