package com.ejs.algaworksCurso.core.springdoc;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.security.core.parameters.P;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Parameter(
        in = ParameterIn.QUERY,
        name = "clienteId",
        description = "id do cliente do pedido",
        example = "1",
        schema = @Schema(type = "integer")
)

@Parameter(
    in = ParameterIn.QUERY,
    name = "restauranteId",
    example = "1",
    schema = @Schema(type = "integer")
)

@Parameter(
        in = ParameterIn.QUERY,
        name = "dataCriacaoInicio",
        description = "data inicial para pesquisa entre datas"
)

@Parameter(
        in = ParameterIn.QUERY,
        name = "dataCriacaoFim",
        description = "data final para pesquisa entre datas"
)
@Target({ElementType.METHOD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface PedidoFilterParameter {
}
