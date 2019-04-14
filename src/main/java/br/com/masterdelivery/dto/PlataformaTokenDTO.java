/**
 * 
 */
package br.com.masterdelivery.dto;

import java.io.Serializable;

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
public class PlataformaTokenDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7809194255861041271L;

	private String plataformaToken;

	private Boolean chamadaAtiva;

	private PlataformaDTO plataforma;

	private UsuarioDTO usuario;


}
