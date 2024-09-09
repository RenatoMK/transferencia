package com.desafioitau.api.transferencia.service.impl;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.desafioitau.api.transferencia.dto.NotificacaoRequestDTO;
import com.desafioitau.api.transferencia.entity.Transacao;
import com.desafioitau.api.transferencia.exception.BusinessNotFoundException;
import com.desafioitau.api.transferencia.repository.TransacaoReprosity;
import com.fasterxml.jackson.core.JsonProcessingException;

public class NotificacaoServiceImplTest {

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private RetryTemplate retryTemplate;

    @Mock
    private TransacaoReprosity transacaoReprosity;

    @InjectMocks
    private NotificacaoServiceImpl notificacaoService;

    private UUID uuid = UUID.randomUUID();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testNotificaBacenOK() throws RestClientException {
        NotificacaoRequestDTO notificacaoRequestDTO = new NotificacaoRequestDTO();

        try {
			when(retryTemplate.execute(any())).thenReturn(null);
		} catch (Throwable e) {
			e.printStackTrace();
		}
        notificacaoService.notificaBacen(notificacaoRequestDTO, uuid);
    }

    @Test
    void testNotificaBacenNok() throws RestClientException, JsonProcessingException {
        NotificacaoRequestDTO notificacaoRequestDTO = new NotificacaoRequestDTO();
        doThrow(new RestClientException("BACEN não disponível")).when(retryTemplate).execute(any());
        assertThrows(BusinessNotFoundException.class, () -> notificacaoService.notificaBacen(notificacaoRequestDTO, uuid));
    }

    @Test
    void testNotificaBacenRetry() throws RestClientException, JsonProcessingException {
        NotificacaoRequestDTO notificacaoRequestDTO = new NotificacaoRequestDTO();
        doThrow(new RestClientException("BACEN não disponível")).when(retryTemplate).execute(any());
        assertThrows(BusinessNotFoundException.class, () -> notificacaoService.notificaBacen(notificacaoRequestDTO, uuid));
    }
}