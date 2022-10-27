package com.ejs.algaworksCurso.api.exceptionHandler;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.ejs.algaworksCurso.core.security.JWTUtil;
import com.ejs.algaworksCurso.domain.exception.EntidadeEmUsoException;
import com.ejs.algaworksCurso.domain.exception.EntidadeNaoEncontradaException;
import com.ejs.algaworksCurso.domain.exception.NegocioException;
import com.ejs.algaworksCurso.domain.exception.TokenExpiradoException;
import com.ejs.algaworksCurso.domain.exception.ValidacaoException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.exc.PropertyBindingException;

@ControllerAdvice
public class ApiHandlerException extends ResponseEntityExceptionHandler {
	
	private static final String MSG_GENERICA_SISTEMA = "Ocorreu um erro inesperado no sistema. Tente novamente se o erro persistir, entre em contato com o administrador do sistema.";

	private final Logger logger = LoggerFactory.getLogger(ApiHandlerException.class);
	
	@Autowired
	private MessageSource messageSource;
	
	@Autowired
	private JWTUtil jwtUtil;
	
	@ExceptionHandler(AccessDeniedException .class)
	public ResponseEntity<?> trataAcessoNegado(AccessDeniedException ex, WebRequest request) {
		HttpStatus status = HttpStatus.FORBIDDEN;
		CampoComErro erro = null;
		ProblemType type =  ProblemType.ACESSO_NEGADO_AO_RECURSO;
		String motivo = "";
		String header = request.getHeader("Authorization");
	 	if ( header != null && header.startsWith("Bearer ")) {
			 erro = new CampoComErro();
	 		if(!this.jwtUtil.tokenIsValid(header.substring(7))) {
	 			status = HttpStatus.UNAUTHORIZED;
	 			erro.setNome("Token Expirado");
	 			type = ProblemType.TOKEN_EXPIRADO;
	 			motivo = "Token expirado. Faça Login novamente";
	 		}else {	 		
		 		status = HttpStatus.FORBIDDEN;
		 		erro.setNome("Permissão de Acesso usuário");
		 		type = ProblemType.ACESSO_NEGADO_AO_RECURSO;
		 		motivo = "Usuário não tem acesso ao método solicitado";
		 	}
	 	}
		
		Problem problem = this.createProblem(status, motivo,
							type)
							.timeStamp()
							.camposComErro(Arrays.asList(erro))
							.userMessage("Acesso negado")
							.build();
		return this.handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);			
 
	}
	
	@ExceptionHandler( TokenExpiradoException.class)
	public ResponseEntity<?> tokenExpiradoException(Exception ex, WebRequest request){
		HttpStatus status = HttpStatus.CONFLICT;
		CampoComErro erro = new CampoComErro();
		erro.setMensagem(ex.getMessage());
		erro.setNome("Token inválido.");
		Problem problem = this.createProblem(status, "Token Expirado", ProblemType.TOKEN_EXPIRADO)
							.timeStamp()
							.camposComErro(Arrays.asList(erro))
							.userMessage("Token Expirado.")
							.build();
		return this.handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
	}
	
	@ExceptionHandler( BadCredentialsException.class)
	public ResponseEntity<?> badCredentialsException(Exception ex, WebRequest request){
		HttpStatus status = HttpStatus.CONFLICT;
		CampoComErro erro = new CampoComErro();
		erro.setMensagem(ex.getMessage());
		erro.setNome("Credenciais inválidas.");
		Problem problem = this.createProblem(status, "Credenciais inválidas", ProblemType.DADOS_INVALIDOS)
							.timeStamp()
							.camposComErro(Arrays.asList(erro))
							.userMessage("Credenciais usadas inválidas.")
							.build();
		return this.handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> capturaExceptionsNaoTratadas(Exception ex, WebRequest resquest ){
		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
		String detail = MSG_GENERICA_SISTEMA;
		Problem problem = this.createProblem(status, detail, ProblemType.ERRO_DE_SISTEMA)
				.userMessage(MSG_GENERICA_SISTEMA)
				.timeStamp()
				.build();
		logger.error(problem.getType(), ex);
		return this.handleExceptionInternal(ex, problem, new HttpHeaders(), status, resquest);		
	}

	@ExceptionHandler(EntidadeNaoEncontradaException.class)
	public ResponseEntity<?> tratarEntidadeNaoEncontradaException( EntidadeNaoEncontradaException ex, WebRequest resquest){
		HttpStatus status = HttpStatus.NOT_FOUND;
		Problem problem = this.createProblem(status, ex.getMessage(), ProblemType.RECURSO_NAO_ENCONTRADO)
				.userMessage(ex.getMessage())
				.timeStamp()
				.build();
		return this.handleExceptionInternal(ex, problem, new HttpHeaders(), HttpStatus.NOT_FOUND, resquest);
	}


	@ExceptionHandler(EntidadeEmUsoException.class)
	public ResponseEntity<?> tratarEntidadeEmUsoException( EntidadeEmUsoException ex, WebRequest resquest ){
		HttpStatus status = HttpStatus.CONFLICT;
		Problem problem = this.createProblem(status, ex.getMessage(), ProblemType.ENTIDADE_EM_USO)
				.timeStamp()
				.userMessage(ex.getMessage())
				.build();
		
		return this.handleExceptionInternal(ex, problem, new HttpHeaders(), HttpStatus.CONFLICT, resquest);
	}
	
	@ExceptionHandler(NegocioException.class)
	public ResponseEntity<?> tratarNegocioException( NegocioException ex, WebRequest resquest ){
		HttpStatus status = HttpStatus.BAD_REQUEST;
		Problem problem = this.createProblem(status, ex.getMessage(), ProblemType.ERRO_NEGOCIO)
				.timeStamp()
				.userMessage(ex.getMessage())
				.build();
		return this.handleExceptionInternal(ex, problem, new HttpHeaders(), HttpStatus.BAD_REQUEST, resquest);
	}
	
	@ExceptionHandler(ValidacaoException.class)
	public ResponseEntity<?> tratarValidaException(ValidacaoException ex, WebRequest request){
		HttpStatus status = HttpStatus.BAD_REQUEST;
		List<CampoComErro> erros = this.adicionaErros(ex.getBindingResult());
		Problem problem =  this.createProblem(status, "Existe campos que não atendem as regras de negócio.", ProblemType.DADOS_INVALIDOS)
			.timeStamp()
			.camposComErro(erros)
			.userMessage(ex.getMessage()).build();
		return this.handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
	}
		
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		Throwable rootCause = ExceptionUtils.getRootCause(ex);
		
		if ( rootCause instanceof InvalidFormatException) {
			return this.handleInvalidFormatException((InvalidFormatException) rootCause, headers, status, request);
		} else if ( rootCause instanceof PropertyBindingException) {
			return this.handlePropertyBindingException( (PropertyBindingException) rootCause, headers, status, request);
		}
		
		Problem problem = this.createProblem(status, "Sua requisição tem erro de sintaxe",
				ProblemType.REQUISICAO_MAL_FORMADA)
				.timeStamp()
				.userMessage("Formulário preenchido com dados inválidos.")
				.build();
		return super.handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
	}
	
	@Override
	protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		
		if ( ex instanceof MethodArgumentTypeMismatchException) {
			return this.handleMethodArgumentTypeMismatchException( (MethodArgumentTypeMismatchException) ex, headers, status, request);
		} else {
			return this.handleConversionNotSuportedException( (ConversionNotSupportedException) ex, headers, status, request);
		}
		
	}	
	
	@Override
	protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		
		String detail = String.format("O recurso '%s', que você tentou acessar, não existe.", 
				ex.getRequestURL());
		
		Problem problem = this.createProblem(status, detail,
				ProblemType.RECURSO_NAO_ENCONTRADO)
				.timeStamp()
				.userMessage("esta url não existe")
				.build();
		return super.handleExceptionInternal(ex, problem, headers, status, request);
	}
	
	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		
		if ( body instanceof String) {
			body = new Problem.Builder().status(status.value()).title(body.toString())
					.timeStamp().userMessage(MSG_GENERICA_SISTEMA).build();
		} else if ( body == null) {
			body = new Problem.Builder().status(status.value()).title(status.getReasonPhrase())
					.timeStamp()
					.userMessage(MSG_GENERICA_SISTEMA).build();
		}
		
		return super.handleExceptionInternal(ex, body, headers, status, request);
	}
	
	@Override
	protected ResponseEntity<Object> handleBindException(BindException ex, HttpHeaders headers, HttpStatus status,
			WebRequest request) {
		// TODO Auto-generated method stub
		return this.handleValidationInternal(ex, headers, status, request, ex.getBindingResult());
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		return handleValidationInternal(ex, headers, status, request, ex.getBindingResult());
	}

	private ResponseEntity<Object> handleValidationInternal(Exception ex, HttpHeaders headers,
			HttpStatus status, WebRequest request, BindingResult bindingResults) {
		String detail = "Um ou mais campos estão inválidos, corrija, e tente novamente.";
	
		List<CampoComErro> camposComErro = this.adicionaErros(bindingResults);
				
		Problem problem = this.createProblem(status, detail, ProblemType.DADOS_INVALIDOS)
				.timeStamp()
				.userMessage(detail)
				.camposComErro(camposComErro)
				.build();
		return handleExceptionInternal(ex, problem, headers, status, request);
	}
	
	@Override
	protected ResponseEntity<Object> handleHttpMediaTypeNotAcceptable(HttpMediaTypeNotAcceptableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		return ResponseEntity.status(status).headers(headers).build();
	}
	
		
	private Problem.Builder createProblem(HttpStatus status, String detail, ProblemType problemType) {
		return new  Problem.Builder().detail(detail).status(status.value()).title(problemType.getTitle()).type(problemType.getUri());
	}

	private List<CampoComErro> adicionaErros(BindingResult bindingResult) {
		List<CampoComErro> erros = bindingResult.getFieldErrors().stream().map(erro -> {
			String message = messageSource.getMessage(erro, LocaleContextHolder.getLocale());
			return new CampoComErro.Builder().nome(erro.getField()).mensagem(erro.getDefaultMessage()).build();
		}).collect(Collectors.toList());
		return erros;
	}
	
	private ResponseEntity<Object> handlePropertyBindingException( PropertyBindingException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request){
		
		String path = ex.getPath().stream().map(ref -> ref.getFieldName())
				.collect(Collectors.joining("."));
		
		String detail = String.format("A propriedade '%s' não deve ser informada nesta requisição.", path);
		
		Problem problem = this.createProblem(status, detail, ProblemType.REQUISICAO_MAL_FORMADA)
				.timeStamp()
				.userMessage(detail)
				.build();
		
		return handleExceptionInternal(ex, problem, headers, status, request);
	}

	private ResponseEntity<Object> handleInvalidFormatException(InvalidFormatException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		String path = ex.getPath().stream().map( ref ->  ref.getFieldName())
				.collect(Collectors.joining("."));
		
		String detail = String.format("A propriedade '%s' recebeu o valor '%s', "
				+ "que é um valor inválido. Corrija e informe um valor compatível com %s.",
				path, ex.getValue(), ex.getTargetType().getSimpleName());
		
		Problem problem = this.createProblem(status, detail, ProblemType.REQUISICAO_MAL_FORMADA).build();
		
		return handleExceptionInternal(ex, problem, headers, status, request);
	}
	
	private ResponseEntity<Object> handleConversionNotSuportedException(ConversionNotSupportedException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		String detail = String.format("O parâmetro de URL '%s' recebeu o valor '%s' que não pode ser convertido."
				+ " corrija e informe um valor compatível com '%s'.",
				ex.getValue(), ex.getRequiredType().getSimpleName());
		
		Problem problem = this.createProblem(status, detail, ProblemType.PARAMETRO_INVALIDO)
				.timeStamp()
				.userMessage("Valor invalido informado.")
				.build();
		
		return handleExceptionInternal(ex, problem, headers, status, request);
	}

	private ResponseEntity<Object> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		String detail = String.format("O parâmetro de URL '%s' recebeu o valor '%s' que é inválido,"
				+ " corrija e informe um valor compatível com '%s'.",
				ex.getName(), ex.getValue(), ex.getRequiredType().getSimpleName());
		
		Problem problem = this.createProblem(status, detail, ProblemType.PARAMETRO_INVALIDO)
				.timeStamp()
				.userMessage(detail)
				.build();
		
		return handleExceptionInternal(ex, problem, headers, status, request);
	}
}
