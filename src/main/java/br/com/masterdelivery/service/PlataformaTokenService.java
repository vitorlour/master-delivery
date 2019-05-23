/**
 * 
 */
package br.com.masterdelivery.service;

import java.util.Set;

import br.com.masterdelivery.entity.PlataformaToken;
import br.com.masterdelivery.entity.Usuario;

/**
 * @author vitorlour
 *
 */
public interface PlataformaTokenService extends GenericService<PlataformaToken, Long> {
	
	Set<PlataformaToken> buscarPorUsuario(Usuario usuario);
}
