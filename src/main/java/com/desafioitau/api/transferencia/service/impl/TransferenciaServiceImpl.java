package com.desafioitau.api.transferencia.service.impl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.desafioitau.api.transferencia.dto.ContaResponseDTO;
import com.desafioitau.api.transferencia.dto.NotificacaoRequestDTO;
import com.desafioitau.api.transferencia.dto.TransferenciaDTO;
import com.desafioitau.api.transferencia.exception.BusinessBadRequestException;
import com.desafioitau.api.transferencia.mapper.NotificacaoMapper;
import com.desafioitau.api.transferencia.service.ClienteService;
import com.desafioitau.api.transferencia.service.ContaService;
import com.desafioitau.api.transferencia.service.NotificacaoService;
import com.desafioitau.api.transferencia.service.TransferenciaService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TransferenciaServiceImpl implements TransferenciaService{

	@Autowired
	ClienteService clienteService;
	@Autowired
	ContaService contaService;
	@Autowired
	NotificacaoService notificacaoService;
	
	@Override
	public void efetuarTransferencia(TransferenciaDTO transferenciaDTO, UUID uuid) {
		//Metodo para validar os dados da transferencia
		log.info("validaDadosTransferencia");
		validaDadosTransferencia(transferenciaDTO);
		//Metodo para validar o cliente
		log.info("validaDadosCliente");
		validaDadosCliente(transferenciaDTO.getIdCliente());
		//Metodo para validar a conta destino
		log.info("validaDadosContas");
		validaDadosContas(transferenciaDTO.getConta().getIdDestino(), "idDestino");
		//Metodo para validar a conta origem
		log.info("validaDadosContas");
		ContaResponseDTO contaOrigem = validaDadosContas(transferenciaDTO.getConta().getIdOrigem(), "idOrigem");
		//Metodo para validar os valores
		log.info("validaValores");
		validaValores(transferenciaDTO, contaOrigem);
		NotificacaoRequestDTO notificacaoRequestDTO = NotificacaoMapper.INSTANCE.toDTO(transferenciaDTO);
		log.info("atualizaSaldo");
		contaService.atualizaSaldo(notificacaoRequestDTO);
		log.info("notificaBacen");
		notificacaoService.notificaBacen(notificacaoRequestDTO, uuid);
		
	}
	
	private void validaValores(TransferenciaDTO transferenciaDTO, ContaResponseDTO contaOrigem) {
		if(contaOrigem.getLimiteDiario()<transferenciaDTO.getValor()) {
			throw new BusinessBadRequestException("Limite diário excedido");
		}
		if(contaOrigem.getSaldo()<transferenciaDTO.getValor()) {
			throw new BusinessBadRequestException("Saldo insuficiente");
		}
	}

	private ContaResponseDTO validaDadosContas(String idOrigem, String origemConta) {
		return contaService.validaDadosContas(idOrigem, origemConta);
	}

	private void validaDadosCliente(String idCliente) {
		clienteService.validaDadosCliente(idCliente);
	}

	private void validaDadosTransferencia(TransferenciaDTO transferenciaDTO) {
		if(transferenciaDTO.getIdCliente()==null || transferenciaDTO.getIdCliente().isBlank()) {
			throw new BusinessBadRequestException("O campo idCliente não pode ser nulo ou vazio.");
		}
		if(transferenciaDTO.getValor() <= 0) {
			throw new BusinessBadRequestException("O campo valor tem que ser maior que 0.");
		}
		if(transferenciaDTO.getConta().getIdDestino() == null || transferenciaDTO.getConta().getIdDestino().isBlank()) {
			throw new BusinessBadRequestException("O campo IdDestino não pode ser nulo ou vazio.");
		}
		if(transferenciaDTO.getConta().getIdOrigem() == null || transferenciaDTO.getConta().getIdOrigem().isBlank()) {
			throw new BusinessBadRequestException("O campo IdOrigem não pode ser nulo ou vazio.");
		}
	}
}
