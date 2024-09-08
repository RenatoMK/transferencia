package com.desafioitau.api.transferencia.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.desafioitau.api.transferencia.dto.TransferenciaDTO;
import com.desafioitau.api.transferencia.dto.TransferenciaRequestDTO;

@Mapper
public interface TransferenciaMapper {
	TransferenciaMapper INSTANCE = Mappers.getMapper(TransferenciaMapper.class);
	
	TransferenciaDTO toDTO(TransferenciaRequestDTO transferenciaRequestDTO);
}
