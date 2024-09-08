package com.desafioitau.api.transferencia.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.desafioitau.api.transferencia.dto.TransferenciaDTO;
import com.desafioitau.api.transferencia.dto.TransferenciaRequestDTO;
import com.desafioitau.api.transferencia.dto.TransferenciaResponseDTO;
import com.desafioitau.api.transferencia.mapper.TransferenciaMapper;
import com.desafioitau.api.transferencia.service.TransferenciaService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/transferencia")
@Slf4j
public class TransferenciaController {

	@Autowired
	private TransferenciaService transferenciaService;
	
	@PostMapping
	public ResponseEntity<TransferenciaResponseDTO> efetuarTransferencia(
			@RequestBody TransferenciaRequestDTO transferenciaRequestDTO) {

		log.info("Inicio do servicoTransferencia");
		TransferenciaDTO transferenciaDTO = TransferenciaMapper.INSTANCE.toDTO(transferenciaRequestDTO);
		UUID uuid = UUID.randomUUID();
		transferenciaService.efetuarTransferencia(transferenciaDTO, uuid);
		TransferenciaResponseDTO transferenciaResponseDTO = new TransferenciaResponseDTO();
		transferenciaResponseDTO.setIdTransferencia(uuid);
		log.info("Fim do servicoTransferencia");
		return ResponseEntity.ok().body(transferenciaResponseDTO);
	}
}
