package com.desafioitau.api.transferencia.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.desafioitau.api.transferencia.dto.ClienteResponseDTO;
import com.desafioitau.api.transferencia.exception.BusinessNotFoundException;
import com.desafioitau.api.transferencia.service.ClienteService;

@Service
public class ClienteServiceImpl implements ClienteService {

	private RestTemplate restTemplate = new RestTemplate();

	@Value("${external.api.cliente}")
	private String urlCliente;

	@Override
	public void validaDadosCliente(String idCliente) {
		HttpHeaders httpHeaders = new HttpHeaders();
		HttpEntity<String> httoEntity = new HttpEntity<>(httpHeaders);
		try {
			restTemplate.exchange(urlCliente + idCliente, HttpMethod.GET,
					httoEntity, ClienteResponseDTO.class);
		} catch (RestClientException e) {
			throw new BusinessNotFoundException("Cliente " +idCliente + " não encontrado na base de clientes");
		}
	}
}
