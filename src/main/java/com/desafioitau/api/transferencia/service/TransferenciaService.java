package com.desafioitau.api.transferencia.service;

import java.util.UUID;

import com.desafioitau.api.transferencia.dto.TransferenciaDTO;

public interface TransferenciaService {
	void efetuarTransferencia(TransferenciaDTO transferenciaDTO, UUID uuid);
}
