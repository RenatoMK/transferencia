package com.desafioitau.api.transferencia.service.impl;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doThrow;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.desafioitau.api.transferencia.dto.ClienteResponseDTO;
import com.desafioitau.api.transferencia.exception.BusinessNotFoundException;

class ClienteServiceImplTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private ClienteServiceImpl clienteServiceImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testValidaDadosCliente() {
        String idCliente = "123";
        String url = null + idCliente;

        doThrow(new RestClientException("Client not found"))
                .when(restTemplate).exchange(eq(url), eq(HttpMethod.GET), any(HttpEntity.class), eq(ClienteResponseDTO.class));

        assertThrows(BusinessNotFoundException.class, () -> clienteServiceImpl.validaDadosCliente(idCliente));
    }
}