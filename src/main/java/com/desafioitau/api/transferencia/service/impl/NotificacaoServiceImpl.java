package com.desafioitau.api.transferencia.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.retry.RetryCallback;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.desafioitau.api.transferencia.dto.ClienteResponseDTO;
import com.desafioitau.api.transferencia.dto.NotificacaoRequestDTO;
import com.desafioitau.api.transferencia.exception.BusinessNotFoundException;
import com.desafioitau.api.transferencia.service.NotificacaoService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class NotificacaoServiceImpl implements NotificacaoService {

	@Autowired
    private RestTemplate restTemplate;
    
    @Autowired
    private RetryTemplate retryTemplate;

    @Value("${external.api.notificacao}")
    private String urlnotificacao;

    public void notificaBacen(NotificacaoRequestDTO notificacaoRequestDTO) {
        HttpHeaders httpHeaders = new HttpHeaders();
        HttpEntity<String> httpEntity = new HttpEntity<>(httpHeaders);
        try {
			retryTemplate.execute((RetryCallback<Void, RestClientException>) context -> {
				log.info("Tentando notificar o BACEN...");
				restTemplate.exchange(urlnotificacao, HttpMethod.POST, httpEntity, ClienteResponseDTO.class);
				return null;
			});
        } catch (RestClientException e) {
            // Isso captura erros como "Connection refused"
            log.error("Erro ao conectar com o BACEN: {}", e.getMessage());
            throw new BusinessNotFoundException("Falha ao tentar notificar o BACEN: " + e.getMessage());
        }
    }

    public void fallbackNotificaBacen(NotificacaoRequestDTO notificacaoRequestDTO, Throwable t) {
        log.error("Fallback ativado. Erro ao tentar notificar o BACEN: {}", t.getMessage());
        throw new BusinessNotFoundException("Operação realizada, mas falhamos em notificar o BACEN após várias tentativas.");
    }
}