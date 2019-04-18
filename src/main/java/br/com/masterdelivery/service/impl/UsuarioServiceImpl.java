/**
 * 
 */
package br.com.masterdelivery.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.masterdelivery.dto.EmailBuilder;
import br.com.masterdelivery.dto.EmailDTO;
import br.com.masterdelivery.dto.UsuarioDTO;
import br.com.masterdelivery.entity.Usuario;
import br.com.masterdelivery.mapper.UsuarioMapper;
import br.com.masterdelivery.repository.UsuarioRepository;
import br.com.masterdelivery.service.UsuarioService;
import br.com.masterdelivery.service.exception.ObjectFoundException;
import br.com.masterdelivery.service.exception.ObjectNotFoundException;
import br.com.masterdelivery.utils.Gerador;

/**
 * @author vitorlour
 *
 */
@Service("usuarioService")
public class UsuarioServiceImpl extends GenericServiceImpl<Usuario, Long> implements UsuarioService {

	private static final String E_MAIL_NÃO_ENCONTRADO = "E-mail não encontrado !";
	private static final String TEXTO_RECUPERAR_SENHA = "Olá, sua nova senha é ";
	private static final String DELIMITER = "";
	private static final String NOVA_SENHA = "Sua nova senha do Master Delivery";
	private static final String USUARIO_NÃO_ENCONTRADO = "Usuario não encontrado !";
	private static final String AVISO_EMAIL_EXISTENTE = "E-mail já cadastrado";

	@Autowired
	private UsuarioRepository repository;

	@Autowired
	private EmailService emailService;

	@Autowired
	private UsuarioMapper mapper;

	public void realizarCadastro(UsuarioDTO dto) {
		if (!existeEmailCadastrado(dto.getEmail())) {
			salvar(mapper.map(dto, Usuario.class));
		} else {
			throw new ObjectFoundException(AVISO_EMAIL_EXISTENTE);
		}
	}

	public void alterarSenha(UsuarioDTO dto, String novaSenha) {
		Usuario usuario = null;

		if (existeUsuarioCadastrado(dto.getEmail(), dto.getSenha())) {
			usuario = buscarUsuarioPorEmailSenha(dto.getEmail(), dto.getSenha());
			usuario.setSenha(novaSenha);
			salvar(usuario);
		} else {
			throw new ObjectNotFoundException(USUARIO_NÃO_ENCONTRADO);
		}
	}

	public void recuperarSenha(EmailDTO dto) {
		Usuario usuario = null;

		if (existeEmailCadastrado(dto.getEmail())) {
			usuario = repository.findByEmail(dto.getEmail());
			usuario.setSenha(Gerador.geraSenhaAleatoria());
			salvar(usuario);
			emailService.enviarEmail(EmailBuilder
					.builder()
					.para(dto.getEmail())
					.assunto(NOVA_SENHA)
					.conteudo(String.join(DELIMITER, TEXTO_RECUPERAR_SENHA, usuario.getSenha()))
					.build());

		} else {
			throw new ObjectNotFoundException(E_MAIL_NÃO_ENCONTRADO);
		}

	}

	public void excluirCadastro(UsuarioDTO dto) {
		Usuario usuario = null;

		if (existeUsuarioCadastrado(dto.getEmail(), dto.getSenha())) {
			usuario = buscarUsuarioPorEmailSenha(dto.getEmail(), dto.getSenha());
			excluir(usuario);
		} else {
			throw new ObjectNotFoundException(USUARIO_NÃO_ENCONTRADO);
		}
	}

	@Transactional(readOnly = true)
	private boolean existeEmailCadastrado(String email) {
		return repository.countByEmail(email) == 1;
	}

	@Transactional(readOnly = true)
	private boolean existeUsuarioCadastrado(String email, String senha) {
		return repository.countByEmailAndSenha(email, senha) == 1;
	}

	@Transactional
	private Usuario buscarUsuarioPorEmailSenha(String email, String senha) {
		return repository.findByEmailAndSenha(email, senha);
	}
}
