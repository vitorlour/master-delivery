/**
 * 
 */
package br.com.masterdelivery.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import br.com.masterdelivery.entity.Entrega;
import br.com.masterdelivery.entity.Usuario;

/**
 * @author vitorlour
 *
 */
@Repository("entregaRepository")
public interface EntregaRepository extends GenericRepository<Entrega, Long> {
	
    Page<Entrega> findByUsuario(Usuario usuario, Pageable pageRequest);
}
