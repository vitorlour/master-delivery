/**
 * 
 */
package br.com.masterdelivery.service;

import java.util.List;

import org.springframework.data.domain.Page;

import br.com.masterdelivery.dto.AnoDTO;
import br.com.masterdelivery.dto.EntregasPorAnoDTO;
import br.com.masterdelivery.entity.Entrega;

/**
 * @author vitorlour
 *
 */
public interface EntregaService extends GenericService<Entrega, Long> {
	
	Page<Entrega> entregaPorPage(Integer page, Integer linesPerPage, String orderBy, String direction);

}
