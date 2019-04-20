/**
 * 
 */
package br.com.masterdelivery.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author vitorlour
 *
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SenhaDTO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1678430103286229996L;
	
	@NotBlank(message = "Por favor preencher a senha !") 
    private String senha;

}
