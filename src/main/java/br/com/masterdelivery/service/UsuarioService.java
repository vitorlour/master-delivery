/**
 * 
 */
package br.com.masterdelivery.service;

import br.com.masterdelivery.dto.EmailDTO;
import br.com.masterdelivery.dto.UsuarioDTO;
import br.com.masterdelivery.entity.Usuario;

/**
 * @author vitorlour
 *
 */
public interface UsuarioService extends GenericService<Usuario, Long> {
	
	void realizarCadastro(UsuarioDTO usuario);
	
	void alterarSenha(UsuarioDTO usuario, String novaSenha);
	
	void recuperarSenha(EmailDTO email);
	
	void excluirCadastro(UsuarioDTO usuario);
	
}
