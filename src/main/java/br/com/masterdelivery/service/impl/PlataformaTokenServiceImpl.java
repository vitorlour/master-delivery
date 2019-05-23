/**
 * 
 */
package br.com.masterdelivery.service.impl;

import java.util.Set;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.masterdelivery.entity.PlataformaToken;
import br.com.masterdelivery.entity.Usuario;
import br.com.masterdelivery.repository.PlataformaTokenRepository;
import br.com.masterdelivery.service.PlataformaTokenService;

/**
 * @author vitorlour
 *
 */
@Service("plataformaTokenService")
public class PlataformaTokenServiceImpl extends GenericServiceImpl<PlataformaToken, Long>
		implements PlataformaTokenService {

	@Autowired
	private PlataformaTokenRepository repository;
	
	@Transactional(readOnly = true)
	public Set<PlataformaToken> buscarPorUsuario(Usuario usuario) {
		return repository.findByUsua(usuario);
	}

}
