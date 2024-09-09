package com.desafioitau.api.transferencia.service.impl;

import java.sql.Timestamp;
import java.util.UUID;

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
import com.desafioitau.api.transferencia.entity.Transacao;
import com.desafioitau.api.transferencia.exception.BusinessNotFoundException;
import com.desafioitau.api.transferencia.repository.TransacaoReprosity;
import com.desafioitau.api.transferencia.service.NotificacaoService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class NotificacaoServiceImpl implements NotificacaoService {

	@Autowired
    private RestTemplate restTemplate;
    
    @Autowired
    private RetryTemplate retryTemplate;
    
    @Autowired
    private TransacaoReprosity transacaoReprosity;

    @Value("${external.api.notificacao}")
    private String urlnotificacao;

    public void notificaBacen(NotificacaoRequestDTO notificacaoRequestDTO, UUID uuid) {
        HttpHeaders httpHeaders = new HttpHeaders();
        HttpEntity<String> httpEntity = new HttpEntity<>(httpHeaders);
        try {
			retryTemplate.execute((RetryCallback<Void, RestClientException>) context -> {
				log.info("Tentando notificar o BACEN...");
				restTemplate.exchange(urlnotificacao, HttpMethod.POST, httpEntity, ClienteResponseDTO.class);
				return null;
			});
        } catch (RestClientException e) {
            Transacao transacao = new Transacao();
            transacao.setDataHoraAtualizacao(new Timestamp(System.currentTimeMillis()));
            transacao.setDataHoraCriacao(new Timestamp(System.currentTimeMillis()));
            try {
				transacao.setDescricaoCorpoMensagem(new ObjectMapper().writeValueAsString(notificacaoRequestDTO));
			} catch (JsonProcessingException e1) {
				log.info("Erro ao grava na base de dados " + uuid);
				e1.printStackTrace();
			}
            transacao.setStatusEnvio("PENDENTE");
            transacao.setUuidTransacao(uuid);
            transacaoReprosity.save(transacao);
            throw new BusinessNotFoundException("Falha ao tentar notificar o BACEN, Em breve ele será notificado");
        }
    }
}