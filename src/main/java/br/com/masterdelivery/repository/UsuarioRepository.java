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

	Usuario findByEmail(String email);
	
	Usuario findByEmailAndSenha(String email, String senha);

	long countByEmailAndSenha(String email, String senha);

	long countByEmail(String email);

}
