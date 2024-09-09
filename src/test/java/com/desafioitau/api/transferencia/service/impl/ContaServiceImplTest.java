package com.desafioitau.api.transferencia.service.impl;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.desafioitau.api.transferencia.dto.ContaResponseDTO;
import com.desafioitau.api.transferencia.dto.NotificacaoRequestDTO;
import com.desafioitau.api.transferencia.exception.BusinessNotFoundException;

public class ContaServiceImplTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private ContaServiceImpl contaService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testValidaDadosContasNok() {
        String idOrigem = "123";
        String origemConta = "contaOrigem";
        String url = null + idOrigem;

        doThrow(new RestClientException("Conta not found")).when(restTemplate)
                .exchange(eq(url), eq(HttpMethod.GET), any(HttpEntity.class), eq(ContaResponseDTO.class));

        assertThrows(BusinessNotFoundException.class, () -> contaService.validaDadosContas(idOrigem, origemConta));
    }

    @Test
    void testValidaDadosContasOk() {
        String idOrigem = "123";
        String origemConta = "contaOrigem";
        String url = null + idOrigem;

        ContaResponseDTO contaResponseDTO = new ContaResponseDTO();
        contaResponseDTO.setAtivo(true);

        when(restTemplate.exchange(eq(url), eq(HttpMethod.GET), any(HttpEntity.class), eq(ContaResponseDTO.class)))
                .thenReturn(ResponseEntity.ok(contaResponseDTO));

        ContaResponseDTO result = contaService.validaDadosContas(idOrigem, origemConta);
        assertTrue(result.isAtivo());
    }
    
    @Test
    void testAtualizaSaldoNok() {
        NotificacaoRequestDTO notificacaoRequestDTO = new NotificacaoRequestDTO();
        String url = null + "saldos";

        doThrow(new RestClientException("Service unavailable")).when(restTemplate)
                .exchange(eq(url), eq(HttpMethod.PUT), any(HttpEntity.class), eq(ContaResponseDTO.class));

        assertThrows(BusinessNotFoundException.class, () -> contaService.atualizaSaldo(notificacaoRequestDTO));

    }
        
}