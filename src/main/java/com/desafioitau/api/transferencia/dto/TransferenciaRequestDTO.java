package com.desafioitau.api.transferencia.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransferenciaRequestDTO {
	private String idCliente;
    private double valor;
    private ContaDTO conta;
}
