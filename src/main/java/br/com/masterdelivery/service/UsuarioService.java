/**
 * 
 */
package br.com.masterdelivery.service;

import br.com.masterdelivery.dto.Resposta;
import br.com.masterdelivery.entity.Usuario;

/**
 * @author vitorlour
 *
 */
public interface UsuarioService extends GenericService<Usuario, Long> {
	
	Resposta realizarCadastro(Usuario usuario);
	
	Resposta alterarSenha(Usuario usuario, String novaSenha);
	
	Resposta recuperarSenha(String email);
	
	Resposta excluirCadastro(Usuario usuario);
	
}
