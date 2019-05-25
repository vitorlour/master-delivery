/**
 * 
 */
package br.com.masterdelivery.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author vitorlour
 *
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SairContaFakeAppsDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8806175403977456363L;
	
	@NotBlank(message = "Por favor preencher o e-mail") 
	private String email;
	
	@NotNull(message = "Por favor preencher a plataforma !")
	private Long plataforma;

}
