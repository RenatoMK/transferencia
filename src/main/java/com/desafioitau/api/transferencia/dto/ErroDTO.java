package com.desafioitau.api.transferencia.dto;

import java.time.OffsetDateTime;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ErroDTO {
	private Integer _erroCode;
	private String _mensagem;
	private String _details;
	private OffsetDateTime _timestamp;
}
