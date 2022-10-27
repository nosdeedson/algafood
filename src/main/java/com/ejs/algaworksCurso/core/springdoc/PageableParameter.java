package com.ejs.algaworksCurso.core.springdoc;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/*
As anotations usadas aqui podem ser usadas na própria classe, criando uma anotation o uso fica global
 */
@Parameter(
        in = ParameterIn.QUERY,
        name = "page",
        example = "1",
        description = "número da página ex 0...n",
        schema = @Schema(type = "integer", defaultValue = "0")
)

@Parameter(
        in = ParameterIn.QUERY,
        name = "size",
        example = "10",
        description = "Quantidade de objetos a serem retornados",
        schema = @Schema(type = "integer", defaultValue = "10")
)

@Parameter(
        in = ParameterIn.QUERY,
        name = "sort",
        description = "Critérios de ordenação para os resultados",
        examples = {
                @ExampleObject("nome"),
                @ExampleObject("nome,asc"),
                @ExampleObject("nome,desc")
        }
)
@Target({ElementType.METHOD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface PageableParameter {
}
