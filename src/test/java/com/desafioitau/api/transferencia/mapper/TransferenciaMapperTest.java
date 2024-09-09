package com.desafioitau.api.transferencia.mapper;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import com.desafioitau.api.transferencia.dto.ContaDTO;
import com.desafioitau.api.transferencia.dto.TransferenciaDTO;
import com.desafioitau.api.transferencia.dto.TransferenciaRequestDTO;

class TransferenciaMapperTest {

    private TransferenciaMapper transferenciaMapper = TransferenciaMapper.INSTANCE;

    @Test
    void testToDTO() {
        TransferenciaRequestDTO transferenciaRequestDTO = new TransferenciaRequestDTO();
        ContaDTO contaDTO = new ContaDTO();
        transferenciaRequestDTO.setConta(contaDTO);
        transferenciaRequestDTO.setIdCliente("12345");
        transferenciaRequestDTO.setValor(200.0);
        transferenciaRequestDTO.getConta().setIdDestino("1111");
        transferenciaRequestDTO.getConta().setIdOrigem("11111");

        TransferenciaDTO transferenciaDTO = transferenciaMapper.toDTO(transferenciaRequestDTO);
        assertNotNull(transferenciaDTO);
    }
}