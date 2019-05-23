package br.com.masterdelivery.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * @author vitorlour
 *
 */
@Getter
@Setter
@Builder
public class CorridaDTO {
	
	private Long id;
	
	private String nomeEstabelecimento;
	
	private String endEstabelecimento;
	
	private String enderecoEstabelecimentoCompleto;
	
	private String cepEstabelecimento;
	
	private String nomeCliente;
	
	private String endCliente;
	
	private String enderecoClienteCompleto;
	
	private String cepCliente;
	
	private Double valorEntrega;
	
	private Long statusCorrida;
	
	private Long plataforma;
	
	private String tokenCorrida;
	
	
}
