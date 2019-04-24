/**
 * 
 */
package br.com.masterdelivery.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.masterdelivery.dto.EmailBuilderDTO;
import br.com.masterdelivery.dto.EmailDTO;
import br.com.masterdelivery.dto.SenhaDTO;
import br.com.masterdelivery.dto.UsuarioDTO;
import br.com.masterdelivery.entity.Usuario;
import br.com.masterdelivery.mapper.UsuarioMapper;
import br.com.masterdelivery.repository.UsuarioRepository;
import br.com.masterdelivery.security.User;
import br.com.masterdelivery.security.service.UserService;
import br.com.masterdelivery.service.UsuarioService;
import br.com.masterdelivery.service.exception.AuthorizationException;
import br.com.masterdelivery.service.exception.ObjectFoundException;
import br.com.masterdelivery.service.exception.ObjectNotFoundException;
import br.com.masterdelivery.utils.Gerador;

/**
 * @author vitorlour
 *
 */
@Service("usuarioService")
public class UsuarioServiceImpl extends GenericServiceImpl<Usuario, Long> implements UsuarioService {

	private static final String ACESSO_NEGADO_PRECISA_SE_LOGAR_PRIMEIRO = "Acesso negado, precisa se logar primeiro !";
	private static final String E_MAIL_NÃO_ENCONTRADO = "E-mail não encontrado !";
	private static final String TEXTO_RECUPERAR_SENHA = "Olá, sua nova senha é ";
	private static final String DELIMITER = "";
	private static final String NOVA_SENHA = "Sua nova senha do Master Delivery";
	private static final String AVISO_EMAIL_EXISTENTE = "E-mail já cadastrado";

	@Autowired
	private UsuarioRepository repository;

	@Autowired
	private EmailService emailService;

	@Autowired
	private UsuarioMapper mapper;
	
	@Autowired
	private BCryptPasswordEncoder pswEncoder;
	
	@Autowired
	private Gerador gerador;
	
	@Transactional
	public void realizarCadastro(UsuarioDTO dto) {
		Usuario usuario = null;
		
		if (!existeEmailCadastrado(dto.getEmail())) {
			passwordEncoder(dto);
			usuario = mapper.map(dto, Usuario.class);
			salvar(usuario);
		} else {
			throw new ObjectFoundException(AVISO_EMAIL_EXISTENTE);
		}
	}
	
	@Transactional
	public void alterarSenha(SenhaDTO novaSenha) {
		User user = UserService.authenticated();

		Usuario usuario = (Usuario) pesquisarPorId(user.getId());
		
		if (usuario == null) {
			throw new AuthorizationException(ACESSO_NEGADO_PRECISA_SE_LOGAR_PRIMEIRO);
		}
		usuario.setSenha(novaSenha.getSenha());
		passwordEncoder(usuario);
		salvar(usuario);
	}
	
	@Transactional
	public void recuperarSenha(EmailDTO dto) {
		Usuario usuario = encontrarPorEmail(dto.getEmail());
			
		if(usuario == null) {
			throw new ObjectNotFoundException(E_MAIL_NÃO_ENCONTRADO);
		}
		
		String senha = gerador.senhaAleatoria();
		usuario.setSenha(senha);
		passwordEncoder(usuario);
		
		salvar(usuario);
		emailService.enviarEmail(EmailBuilderDTO
					.builder()
					.para(dto.getEmail())
					.assunto(NOVA_SENHA)
					.conteudo(String.join(DELIMITER, TEXTO_RECUPERAR_SENHA, senha))
					.build());
	}

	public void excluirCadastro(UsuarioDTO dto) {}

	@Transactional(readOnly = true)
	public boolean existeEmailCadastrado(String email) {
		return repository.countByEmail(email) == 1;
	}

	@Transactional(readOnly = true)
	private boolean existeUsuarioCadastrado(String email, String senha) {
		return repository.countByEmailAndSenha(email, senha) == 1;
	}

	@Transactional(readOnly = true)
	public Usuario buscarUsuarioPorEmailSenha(String email, String senha) {
		return repository.findByEmailAndSenha(email, senha);
	}
	
	@Transactional(readOnly = true)
	public Usuario encontrarPorEmail(String email) {
		return repository.findByEmail(email);
	}
	
	private Usuario passwordEncoder(Usuario usuario) {
		usuario.setSenha(pswEncoder.encode(usuario.getSenha()));
		return usuario;
	}
	
	private UsuarioDTO passwordEncoder(UsuarioDTO dto) {
		dto.setSenha(pswEncoder.encode(dto.getSenha()));
		return dto;
	}
}
