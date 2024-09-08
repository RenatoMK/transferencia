package com.desafioitau.api.transferencia.service;

import com.desafioitau.api.transferencia.dto.ContaResponseDTO;
import com.desafioitau.api.transferencia.dto.NotificacaoRequestDTO;

public interface ContaService {
	ContaResponseDTO validaDadosContas(String idOrigem, String origemConta);
	
	void atualizaSaldo(NotificacaoRequestDTO notificacaoRequestDTO);
}
