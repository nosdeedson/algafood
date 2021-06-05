package com.ejs.algaworksCurso.api.exceptionHandler;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.ejs.algaworksCurso.domain.exception.EntidadeEmUsoException;
import com.ejs.algaworksCurso.domain.exception.EntidadeNaoEncontradaException;
import com.ejs.algaworksCurso.domain.exception.NegocioException;

@ControllerAdvice
public class ApiHandlerException extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(EntidadeNaoEncontradaException.class)
	public ResponseEntity<?> tratarEntidadeNaoEncontradaException( EntidadeNaoEncontradaException ex, WebRequest resquest){
		
		HttpStatus status = HttpStatus.NOT_FOUND;
		Problem problem = this.createProblem(status, ex.getMessage(), ProblemType.ENTIDADE_NAO_ENCONTRADA).build();
		return this.handleExceptionInternal(ex, problem, new HttpHeaders(), HttpStatus.NOT_FOUND, resquest);
	}


	@ExceptionHandler(EntidadeEmUsoException.class)
	public ResponseEntity<?> tratarEntidadeEmUsoException( EntidadeEmUsoException ex, WebRequest resquest ){
		HttpStatus status = HttpStatus.CONFLICT;
		Problem problem = this.createProblem(status, ex.getMessage(), ProblemType.ENTIDADE_EM_USO).build();
		
		return this.handleExceptionInternal(ex, problem, new HttpHeaders(), HttpStatus.CONFLICT, resquest);
	}
	
	@ExceptionHandler(NegocioException.class)
	public ResponseEntity<?> tratarNegocioException( NegocioException ex, WebRequest resquest ){
		HttpStatus status = HttpStatus.BAD_REQUEST;
		Problem problem = this.createProblem(status, ex.getMessage(), ProblemType.ERRO_NEGOCIO).build();
		return this.handleExceptionInternal(ex, problem, new HttpHeaders(), HttpStatus.BAD_REQUEST, resquest);
	}
	
	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		
		if ( body instanceof String) {
			body = new Problem.Builder().status(status.value()).title(body.toString()).build();
		} else if ( body == null ) {
			body = new Problem.Builder().status(status.value()).title(status.getReasonPhrase());
		}
		
		return super.handleExceptionInternal(ex, body, headers, status, request);
	}
	
	
	private Problem.Builder createProblem(HttpStatus status, String detail, ProblemType problemType) {
		return new  Problem.Builder().detail(detail).status(status.value()).title(problemType.getTitle()).type(problemType.getUri());
	}
}
