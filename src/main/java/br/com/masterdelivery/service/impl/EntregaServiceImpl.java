/**
 * 
 */
package br.com.masterdelivery.service.impl;


import org.springframework.stereotype.Service;

import br.com.masterdelivery.entity.Entrega;
import br.com.masterdelivery.service.EntregaService;

/**
 * @author vitorlour
 *
 */
@Service("entregaService")
public class EntregaServiceImpl extends GenericServiceImpl<Entrega, Long> implements EntregaService {
	
}
