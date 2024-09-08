package com.desafioitau.api.transferencia.exception;

public class BusinessBadRequestException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8204896827386900923L;
	public BusinessBadRequestException(String mensagem) {
		super(mensagem);
	}
	
	public BusinessBadRequestException(String mensagem, Throwable causa) {
		super(mensagem);
	}

}
