/**
 * 
 */
package br.com.masterdelivery.dto;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

/**
 * @author vitorlour
 *
 */

@Setter
@Getter
public class CorridaAceitaDTO {
	
	@NotBlank(message = "Por favor preencher o token da corrida desejado")
	private String tokenCorrida;

}
