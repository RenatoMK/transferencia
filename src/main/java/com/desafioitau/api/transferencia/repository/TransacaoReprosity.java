package com.desafioitau.api.transferencia.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.desafioitau.api.transferencia.entity.Transacao;

public interface TransacaoReprosity  extends JpaRepository<Transacao, Long>{

}
