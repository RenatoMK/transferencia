package com.desafioitau.api.transferencia.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "TB_TRSA")
public class Transacao {
	
	@GeneratedValue(generator="SEQUENCE_CLIENTE", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name="SEQUENCE_CLIENTE",sequenceName="SEQ_CLIENTE", allocationSize=1)
	@Id
	@Column(name = "ID_CLIENTE")
	private Long idCliente;
	
	@Column(name = "NOME_CLIENTE")
	private String nomeCliente;
	
	@Column(name = "TIPO_CLIENTE")
	private String tipoCliente;
	
	@Column(name = "PERFIL_CLIENTE")
	private Long perfilCliente;

}
