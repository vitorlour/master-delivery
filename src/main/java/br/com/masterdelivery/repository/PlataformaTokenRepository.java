/**
 * 
 */
package br.com.masterdelivery.repository;

import org.springframework.stereotype.Repository;

import br.com.masterdelivery.entity.PlataformaToken;

/**
 * @author vitorlour
 *
 */
@Repository("plataformaTokenRepository")
public interface PlataformaTokenRepository extends GenericRepository<PlataformaToken, Long> {
}
