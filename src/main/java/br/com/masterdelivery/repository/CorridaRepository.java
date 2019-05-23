/**
 * 
 */
package br.com.masterdelivery.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.masterdelivery.entity.Corrida;


/**
 * @author vitorlour
 *
 */
@Repository("corridaRepository")
public interface CorridaRepository extends JpaRepository<Corrida, Long> {
	
	long countByTokenCorrida(String tokenCorrida);
	
	Set<Corrida> findByStatusCorrida(Long statusCorrida);
	
}
