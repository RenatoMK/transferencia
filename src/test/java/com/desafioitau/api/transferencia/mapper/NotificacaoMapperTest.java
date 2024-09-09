package com.desafioitau.api.transferencia.mapper;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import com.desafioitau.api.transferencia.dto.ContaDTO;
import com.desafioitau.api.transferencia.dto.NotificacaoRequestDTO;
import com.desafioitau.api.transferencia.dto.TransferenciaDTO;

class NotificacaoMapperTest {

    private NotificacaoMapper notificacaoMapper = NotificacaoMapper.INSTANCE;

    @Test
    void testToDTO() {
        TransferenciaDTO transferenciaDTO = new TransferenciaDTO();
        ContaDTO contaDTO = new ContaDTO();
        transferenciaDTO.setConta(contaDTO);
        transferenciaDTO.setIdCliente("12345");
        transferenciaDTO.setValor(100.0);
        transferenciaDTO.getConta().setIdDestino("0111");
        transferenciaDTO.getConta().setIdOrigem("002");

        NotificacaoRequestDTO notificacaoRequestDTO = notificacaoMapper.toDTO(transferenciaDTO);

        assertNotNull(notificacaoRequestDTO);
    }
}