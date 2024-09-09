package com.desafioitau.api.transferencia.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.desafioitau.api.transferencia.dto.TransferenciaDTO;
import com.desafioitau.api.transferencia.dto.TransferenciaRequestDTO;
import com.desafioitau.api.transferencia.dto.TransferenciaResponseDTO;
import com.desafioitau.api.transferencia.mapper.TransferenciaMapper;
import com.desafioitau.api.transferencia.service.TransferenciaService;

class TransferenciaControllerTest {

    @InjectMocks
    private TransferenciaController transferenciaController;

    @Mock
    private TransferenciaService transferenciaService;

    @Mock
    private TransferenciaMapper transferenciaMapper;

    private TransferenciaRequestDTO transferenciaRequestDTO;
    private TransferenciaDTO transferenciaDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        transferenciaRequestDTO = new TransferenciaRequestDTO();
        transferenciaDTO = new TransferenciaDTO();
    }

    @Test
    void testEfetuarTransferencia() {
        when(transferenciaMapper.toDTO(transferenciaRequestDTO)).thenReturn(transferenciaDTO);
        ResponseEntity<TransferenciaResponseDTO> responseEntity = transferenciaController.efetuarTransferencia(transferenciaRequestDTO);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }
}
