/**
 * 
 */
package br.com.masterdelivery.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author vitorlour
 *
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsuarioDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5648969106251392268L;
	
    @NotBlank(message = "Por favor preencher o E-mail !") 
    @Email(message = "E-mail precisa ser valido !")
	private String email;
	
    @NotBlank(message = "Por favor preencher a senha !") 
    private String senha;
    
}
