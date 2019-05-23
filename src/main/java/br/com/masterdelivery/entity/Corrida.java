package br.com.masterdelivery.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.Getter;
import lombok.Setter;

/**
 * @author vitorlour
 *
 */
@Entity
@Table(name = "TB_CORRIDA")
@Getter
@Setter
public class Corrida {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	@Column(name = "id")
	private Long id;
	
	@Column(name = "nm_estabelecimento")
	private String nomeEstabelecimento;
	
	@Column(name = "end_estabelecimento")
	private String endEstabelecimento;
	
	@Column(name = "cep_estabelecimento")
	private String cepEstabelecimento;
	
	@Column(name = "nm_cliente")
	private String nomeCliente;
	
	@Column(name = "end_cliente")
	private String endCliente;
	
	@Column(name = "cep_cliente")
	private String cepCliente;
	
	@Column(name = "vl_entrega")
	private Double valorEntrega;
	
	@Column(name = "status_corrida")
	private Long statusCorrida;
	
	@Column(name = "nr_plataforma")
	private Long plataforma;
	
	@Column(name = "nr_token_corrida")
	private String tokenCorrida;
	
}
