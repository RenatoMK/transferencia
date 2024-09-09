package com.desafioitau.api.transferencia.service;

import java.util.UUID;

import com.desafioitau.api.transferencia.dto.NotificacaoRequestDTO;

public interface NotificacaoService {

	void notificaBacen(NotificacaoRequestDTO notificacaoRequestDTO, UUID uuid);

}
