package com.desafioitau.api.transferencia.entity;

import java.sql.Timestamp;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "TB_ENVO_BCEN")
public class Transacao {
	
	@GeneratedValue(generator="SEQUENCE_TB_ENVO_BCEN", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name="SEQUENCE_TB_ENVO_BCEN",sequenceName="SEQ_TB_ENVO_BCEN", allocationSize=1)
	@Id
	@Column(name = "ID")
	private Long id;
	
	@Column(name = "UUID_TRSA")
	private UUID uuidTransacao;
	
	@Column(name = "DS_CORPO_MENSAGEM")
	private String descricaoCorpoMensagem;
	
	@Column(name = "ST_ENVO")
	private String statusEnvio;
	
	@Column(name = "DH_CRCO")
	private Timestamp dataHoraCriacao;
	
	@Column(name = "DH_ATLO")
	private Timestamp dataHoraAtualizacao;

}
