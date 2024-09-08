package com.desafioitau.api.transferencia.exception;

public class BusinessNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6893993468608338682L;

	public BusinessNotFoundException(String mensagem) {
		super(mensagem);
	}

}
