/**
 * 
 */
package br.com.masterdelivery.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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
    
    
    @Query(value = "select count(e) from Entrega e where year(e.dataEntrega) =:year and month(e.dataEntrega) =:month")
    Long getByPlataformaAndYearAndMonth(@Param("year")int year, @Param("month")int month);
}
