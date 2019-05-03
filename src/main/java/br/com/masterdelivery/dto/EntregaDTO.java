/**
 * 
 */
package br.com.masterdelivery.dto;


import java.io.Serializable;
import java.util.Date;

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
public class EntregaDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3370696408003084403L;

	private Double valorEntrega;
	
	private Double valorGorjeta;
	
	private String duracaoCorrida;
	
	private Date dataEntrega;
	
    private PlataformaDTO plataforma;
    
}
