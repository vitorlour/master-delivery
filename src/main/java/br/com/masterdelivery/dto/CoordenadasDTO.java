/**
 * 
 */
package br.com.masterdelivery.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

/**
 * @author vitorlour
 *
 */
@Getter
@Setter
public class CoordenadasDTO {
	
	@NotNull(message = "Por favor preencher a latitude !") 
	private Double latitude;
	
	@NotNull(message = "Por favor preencher a longitude !") 
	private Double longitude;

}
