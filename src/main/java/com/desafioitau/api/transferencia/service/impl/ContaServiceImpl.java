package com.desafioitau.api.transferencia.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.desafioitau.api.transferencia.dto.ContaResponseDTO;
import com.desafioitau.api.transferencia.dto.NotificacaoRequestDTO;
import com.desafioitau.api.transferencia.exception.BusinessBadRequestException;
import com.desafioitau.api.transferencia.exception.BusinessNotFoundException;
import com.desafioitau.api.transferencia.service.ContaService;

@Service
public class ContaServiceImpl implements ContaService {

	private RestTemplate restTemplate = new RestTemplate();
	
	@Value("${external.api.conta}")
	private String urlConta;
	
	@Override
	public ContaResponseDTO validaDadosContas(String idOrigem, String origemConta) {
		HttpHeaders httpHeaders = new HttpHeaders();
		HttpEntity<String> httoEntity = new HttpEntity<>(httpHeaders);
		ResponseEntity<ContaResponseDTO> contaResponse;
		try {
			contaResponse = restTemplate.exchange(urlConta + idOrigem, HttpMethod.GET,
					httoEntity, ContaResponseDTO.class);
			validaResponseContas(contaResponse.getBody(), origemConta);
		} catch (RestClientException e) {
			throw new BusinessNotFoundException(
					"Conta " + origemConta + " " + idOrigem + " não encontrado na base de contas");
		}
		return contaResponse.getBody();
	}
	
	private void validaResponseContas(ContaResponseDTO contaResponseDTO, String origemConta) {
		if(contaResponseDTO.isAtivo()==false) {
			throw new BusinessBadRequestException("Conta " + origemConta + " não esta ativa.");
		}
	}

	@Override
	public void atualizaSaldo(NotificacaoRequestDTO notificacaoRequestDTO) {
		HttpHeaders httpHeaders = new HttpHeaders();
		HttpEntity<String> httoEntity = new HttpEntity<>(httpHeaders);
		try {
			restTemplate.exchange(urlConta + "saldos", HttpMethod.PUT,
					httoEntity, ContaResponseDTO.class);
		} catch (RestClientException e) {
				throw new BusinessNotFoundException("Serviço indisponível, por favor tentar novamente.");
		}
	}
}
