package com.desafioitau.api.transferencia.exception;

import java.time.OffsetDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.desafioitau.api.transferencia.dto.ErroDTO;

@ControllerAdvice
public class HttpErroException extends ResponseEntityExceptionHandler {

	@ExceptionHandler(BusinessNotFoundException.class)
	public ResponseEntity<?> businessNotFound(BusinessNotFoundException e) {

		ErroDTO erroDTO = erroResponse(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.toString(),
				e.getMessage(), OffsetDateTime.now()).build();
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erroDTO);
	}

	@ExceptionHandler(BusinessBadRequestException.class)
	public ResponseEntity<?> businessBadRequest(BusinessBadRequestException e) {

		ErroDTO erroDTO = erroResponse(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.toString(),
				e.getMessage(), OffsetDateTime.now()).build();
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erroDTO);
	}
	
	private ErroDTO.ErroDTOBuilder erroResponse(Integer erreCode, String detalhe, String mensagem,
			OffsetDateTime timestamp) {
		return ErroDTO.builder()._erroCode(erreCode)._details(detalhe)._mensagem(mensagem)._timestamp(timestamp);
	}
}
