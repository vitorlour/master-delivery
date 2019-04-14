/**
 * 
 */
package br.com.masterdelivery.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.masterdelivery.dto.Resposta;
import br.com.masterdelivery.entity.Usuario;
import br.com.masterdelivery.repository.UsuarioRepository;
import br.com.masterdelivery.service.UsuarioService;
import br.com.masterdelivery.utils.Gerador;

/**
 * @author vitorlour
 *
 */
@Service
public class UsuarioServiceImpl extends GenericServiceImpl<Usuario, Long> implements UsuarioService {

	private static final String SENHA_INVALIDA = "Senha invalida !";
	private static final String E_MAIL_EM_BRANCO = "E-mail em branco";
	private static final String AVISO_E_MAIL_INEXISTENTE = "E-mail inexistente";
	private static final String TEXTO_RECUPERAR_SENHA = "Olá, sua nova senha é ";
	private static final String DELIMITER = "";
	private static final String NOVA_SENHA = "Sua nova senha do Master Delivery";
	private static final String USUARIO_NÃO_ENCONTRADO = "Usuario não encontrado !";
	private static final String OBJETO_NULLO = "Objeto nullo";
	private static final String AVISO_EMAIL_EXISTENTE = "E-mail já cadastrado";

	@Autowired
	private UsuarioRepository repository;

	@Autowired
	private EmailService emailService;

	public Resposta realizarCadastro(Usuario usuario) {
		Resposta resposta = null;

		if (usuario != null) {
			if (!existeEmailCadastrado(usuario.getEmail())) {
				repository.saveAndFlush(usuario);
			} else {
				return new Resposta(AVISO_EMAIL_EXISTENTE, true);
			}
		} else {
			return new Resposta(OBJETO_NULLO, true);
		}

		return resposta;
	}

	public Resposta alterarSenha(Usuario usuario, String novaSenha) {
		Resposta resposta = null;

		if (usuario != null) {
			if (!novaSenha.isBlank()) {
				if (existeUsuarioCadastrado(usuario.getEmail(), usuario.getSenha())) {
					usuario.setSenha(novaSenha);
					repository.saveAndFlush(usuario);
				} else {
					return new Resposta(USUARIO_NÃO_ENCONTRADO, true);
				}
			} else {
				return new Resposta(SENHA_INVALIDA, true);
			}
		} else {
			return new Resposta(OBJETO_NULLO, true);
		}
		return resposta;
	}

	public Resposta recuperarSenha(String email) {
		Resposta resposta = null;
		Usuario usuario = null;

		if (!email.isBlank()) {
			if (repository.countByEmail(email) == 1) {
				usuario = repository.findByEmail(email);
				usuario.setSenha(Gerador.geraSenhaAleatoria());

				repository.saveAndFlush(usuario);

				emailService.enviarEmail(email, NOVA_SENHA,
						String.join(DELIMITER, TEXTO_RECUPERAR_SENHA, usuario.getSenha()));

			} else {
				return new Resposta(AVISO_E_MAIL_INEXISTENTE, true);
			}
		} else {
			return new Resposta(E_MAIL_EM_BRANCO, true);
		}

		return resposta;
	}

	public Resposta excluirCadastro(Usuario usuario) {
		Resposta resposta = null;

		if (usuario != null) {
			repository.delete(usuario);
		} else {
			return new Resposta(OBJETO_NULLO, true);
		}
		return resposta;
	}

	public boolean existeEmailCadastrado(String email) {
		return repository.countByEmail(email) == 1;
	}

	// trocar senha por TOKEN futuramente.
	public boolean existeUsuarioCadastrado(String email, String senha) {
		return repository.countByEmailAndSenha(email, senha) == 1;
	}

}
