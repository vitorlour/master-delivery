/**
 * 
 */
package br.com.masterdelivery.service.impl;


import org.springframework.stereotype.Service;

import br.com.masterdelivery.entity.Plataforma;
import br.com.masterdelivery.service.PlataformaService;

/**
 * @author vitorlour
 *
 */
@Service("plataformaService")
public class PlataformaServiceImpl extends GenericServiceImpl<Plataforma, Long> implements PlataformaService{}
