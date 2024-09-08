package com.desafioitau.api.transferencia.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.desafioitau.api.transferencia.dto.NotificacaoRequestDTO;
import com.desafioitau.api.transferencia.dto.TransferenciaDTO;

@Mapper
public interface NotificacaoMapper {
	NotificacaoMapper INSTANCE = Mappers.getMapper(NotificacaoMapper.class);
	
	NotificacaoRequestDTO toDTO(TransferenciaDTO transferenciaDTO);
}
