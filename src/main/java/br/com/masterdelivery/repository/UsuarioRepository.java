/**
 * 
 */
package br.com.masterdelivery.repository;

import org.springframework.stereotype.Repository;

import br.com.masterdelivery.entity.Usuario;

/**
 * @author vitorlour
 *
 */
@Repository("usuarioRepository")
public interface UsuarioRepository extends GenericRepository<Usuario, Long> {

}
