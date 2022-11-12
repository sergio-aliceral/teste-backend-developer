package com.teste.infrastructure.exception;

import org.springframework.http.HttpStatus;

public class BusinessException extends HigiaException {
	private static final long serialVersionUID = 1L;

	public BusinessException(String message) {
		super(message);
	}

	@Override
	public HttpStatus getStatusCode() {
		// TODO Auto-generated method stub
		return null;
	}

}
