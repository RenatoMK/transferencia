package com.desafioitau.api.transferencia.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SaldoRequestDTO {
    private double valor;
    private String nomeDestino;
    private ContaDTO conta;
}
