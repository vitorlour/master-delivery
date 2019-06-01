/**
 * 
 */
package br.com.masterdelivery.service;

import java.util.Set;

import br.com.masterdelivery.dto.CoordenadasDTO;
import br.com.masterdelivery.dto.CorridaAceitaDTO;
import br.com.masterdelivery.entity.Corrida;

/**
 * @author vitorlour
 *
 */
public interface CorridaService {
	
	Set<Corrida> getCorridaPorLocalizacao(CoordenadasDTO dto);

	void corridaAceita(CorridaAceitaDTO dto);
}
