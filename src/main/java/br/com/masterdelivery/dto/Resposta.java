/**
 * 
 */
package br.com.masterdelivery.dto;

import lombok.AllArgsConstructor;
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
public class Resposta {
	
	private String mensagem;
	private boolean erro;
}
