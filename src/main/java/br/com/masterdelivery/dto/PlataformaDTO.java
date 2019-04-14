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
public class PlataformaDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2637060295571764782L;

	private String nome;

	private String cnplataforma;
	

}
