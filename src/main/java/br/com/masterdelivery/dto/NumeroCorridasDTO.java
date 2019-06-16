/**
 * 
 */
package br.com.masterdelivery.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * @author vitorlour
 *
 */
@Getter
@Builder
@Setter
public class NumeroCorridasDTO {
	
	private long prontoParaColeta; 
	private long coletaCaminhoEntrega;
	private long entregue;
	private long problemaColeta;
	private long problemaCaminhoDaEntrega;
	private long problemaEntrega;
	private long problemaComUsuario;
	
}
