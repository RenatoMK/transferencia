package com.desafioitau.api.transferencia.exception;

public class BusinessException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8849787625182024926L;
	
	public BusinessException(String mensagem) {
		super(mensagem);
	}

}
