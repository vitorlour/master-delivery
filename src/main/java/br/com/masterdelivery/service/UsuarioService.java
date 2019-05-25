/**
 * 
 */
package br.com.masterdelivery.service;


import java.util.Set;

import br.com.masterdelivery.dto.EmailDTO;
import br.com.masterdelivery.dto.SairContaFakeAppsDTO;
import br.com.masterdelivery.dto.SenhaDTO;
import br.com.masterdelivery.dto.UsuarioDTO;
import br.com.masterdelivery.dto.UsuarioFakeAppsDTO;
import br.com.masterdelivery.entity.Usuario;

/**
 * @author vitorlour
 *
 */
public interface UsuarioService extends GenericService<Usuario, Long> {
	
	void realizarCadastro(UsuarioDTO usuario);
	
	void alterarSenha(SenhaDTO novaSenha);
	
	void recuperarSenha(EmailDTO email);
	
	Usuario encontrarPorEmail(String email);

	void vincularContasApps(UsuarioFakeAppsDTO usuario);
	
	Usuario buscaUsuarioLogado();

	Set<UsuarioFakeAppsDTO> getContasCadastradasApp();

	void sairContaApp(SairContaFakeAppsDTO dto);
	
}
