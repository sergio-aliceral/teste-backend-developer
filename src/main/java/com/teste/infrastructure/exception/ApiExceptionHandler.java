package com.teste.infrastructure.exception;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(BusinessException.class)
	public ResponseEntity<Object> businessError(BusinessException ex, WebRequest request) {
		return handleExceptionInternal(ex, ex.getMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}

	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<Object> genericExcpetion(NotFoundException ex, WebRequest request) {
		return handleExceptionInternal(ex, ex.getMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}
	
	@Override
	public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		Map<String, List<String>> body = new HashMap<>();
		body.put("errors", builMensagens(ex));
		return handleExceptionInternal(ex, body, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}
	
	@Override
	public ResponseEntity<Object> handleBindException(BindException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		Map<String, List<String>> body = new HashMap<>();
		body.put("errors", builMensagens(ex));
		return handleExceptionInternal(ex, body, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> genericExcpetion(Exception ex, WebRequest request) {
		return handleExceptionInternal(ex, "Erro interno", new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
	}	
	
	@ExceptionHandler(MaxUploadSizeExceededException.class)
	public ResponseEntity<Object> handleMaxUploadSizeExceededException(MaxUploadSizeExceededException ex, WebRequest request) {
		return handleExceptionInternal(ex, "O documento enviado não deve possuir mais de 2MB", new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}
	
	private List<String> builMensagens(BindException ex) {
		return ex.getFieldErrors().stream().map(e -> formataMensagemErro(e.getDefaultMessage(), e.getField())).collect(Collectors.toList());
	}

	private String formataMensagemErro(String msg, Object... args) {
		MessageFormat fmt = new MessageFormat(msg);
		return fmt.format(args);
	}
	
}