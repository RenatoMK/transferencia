package com.desafioitau.api.transferencia.service.impl;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.desafioitau.api.transferencia.dto.ContaDTO;
import com.desafioitau.api.transferencia.dto.ContaResponseDTO;
import com.desafioitau.api.transferencia.dto.NotificacaoRequestDTO;
import com.desafioitau.api.transferencia.dto.TransferenciaDTO;
import com.desafioitau.api.transferencia.exception.BusinessBadRequestException;
import com.desafioitau.api.transferencia.service.ClienteService;
import com.desafioitau.api.transferencia.service.ContaService;
import com.desafioitau.api.transferencia.service.NotificacaoService;

public class TransferenciaServiceImplTest {

    @Mock
    private ClienteService clienteService;

    @Mock
    private ContaService contaService;

    @Mock
    private NotificacaoService notificacaoService;

    @InjectMocks
    private TransferenciaServiceImpl transferenciaServiceImpl;

    private TransferenciaDTO transferenciaDTO;
    private UUID uuid;
    private ContaResponseDTO contaResponseDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        uuid = UUID.randomUUID();
    }

    @Test
    void testEfetuarTransferenciaNok() {
    	transferenciaDTO = new TransferenciaDTO();
    	assertThrows(BusinessBadRequestException.class, () -> {
        	transferenciaServiceImpl.efetuarTransferencia(transferenciaDTO, uuid);
    	});
    }
    
    @Test
    void testEfetuarTransferenciaOk() {
    	contaResponseDTO = new ContaResponseDTO();
    	contaResponseDTO.setLimiteDiario(50.0);
    	contaResponseDTO.setAtivo(true);
    	contaResponseDTO.setSaldo(1000);
    	when(contaService.validaDadosContas(any(), any())).thenReturn(contaResponseDTO);
    	
    	transferenciaDTO = new TransferenciaDTO();
    	ContaDTO contaDTO = new ContaDTO();
    	contaDTO.setIdDestino("1212312313232456");
    	contaDTO.setIdOrigem("446546445646");
    	transferenciaDTO.setConta(contaDTO);
    	transferenciaDTO.setIdCliente("4565615");
    	transferenciaDTO.setValor(10);
    	transferenciaServiceImpl.efetuarTransferencia(transferenciaDTO, uuid);
    }

}