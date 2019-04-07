/**
 * 
 */
package br.com.masterdelivery.repository;

import org.springframework.stereotype.Repository;

import br.com.masterdelivery.entity.Entrega;

/**
 * @author vitorlour
 *
 */
@Repository("entregaRepository")
public interface EntregaRepository extends GenericRepository<Entrega, Long> {
}
