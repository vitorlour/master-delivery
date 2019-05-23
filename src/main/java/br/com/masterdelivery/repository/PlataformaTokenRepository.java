/**
 * 
 */
package br.com.masterdelivery.repository;

import java.util.Set;

import org.springframework.stereotype.Repository;

import br.com.masterdelivery.entity.PlataformaToken;
import br.com.masterdelivery.entity.Usuario;

/**
 * @author vitorlour
 *
 */
@Repository("plataformaTokenRepository")
public interface PlataformaTokenRepository extends GenericRepository<PlataformaToken, Long> {
	
	Set<PlataformaToken> findByUsua(Usuario usua);
}
