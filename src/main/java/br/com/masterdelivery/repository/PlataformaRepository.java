/**
 * 
 */
package br.com.masterdelivery.repository;

import org.springframework.stereotype.Repository;

import br.com.masterdelivery.entity.Plataforma;

/**
 * @author vitorlour
 *
 */
@Repository("plataformaRepository")
public interface PlataformaRepository extends GenericRepository<Plataforma, Long> {
}
