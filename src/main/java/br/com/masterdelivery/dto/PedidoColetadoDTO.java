/**
 * 
 */
package br.com.masterdelivery.dto;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

/**
 * @author vitorlour
 *
 */
@Setter
@Getter
public class PedidoColetadoDTO {
	
	@NotNull(message = "Por favor preencher !") 
	private boolean coletado;
	
	@NotNull
	private CoordenadasDTO coordenadasDTO;
	
	

}
