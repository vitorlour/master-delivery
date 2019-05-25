/**
 * 
 */
package br.com.masterdelivery.service.impl;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.masterdelivery.dto.EmailBuilderDTO;
import br.com.masterdelivery.dto.EmailDTO;
import br.com.masterdelivery.dto.SairContaFakeAppsDTO;
import br.com.masterdelivery.dto.SenhaDTO;
import br.com.masterdelivery.dto.UsuarioDTO;
import br.com.masterdelivery.dto.UsuarioFakeAppsDTO;
import br.com.masterdelivery.entity.Plataforma;
import br.com.masterdelivery.entity.PlataformaToken;
import br.com.masterdelivery.entity.Usuario;
import br.com.masterdelivery.http.HttpRequest;
import br.com.masterdelivery.mapper.UsuarioMapper;
import br.com.masterdelivery.repository.PlataformaRepository;
import br.com.masterdelivery.repository.PlataformaTokenRepository;
import br.com.masterdelivery.repository.UsuarioRepository;
import br.com.masterdelivery.security.User;
import br.com.masterdelivery.security.service.UserService;
import br.com.masterdelivery.service.UsuarioService;
import br.com.masterdelivery.service.exception.AuthorizationException;
import br.com.masterdelivery.service.exception.ObjectFoundException;
import br.com.masterdelivery.service.exception.ObjectNotFoundException;
import br.com.masterdelivery.utils.Constantes;
import br.com.masterdelivery.utils.Gerador;

/**
 * @author vitorlour
 *
 */
@Service("usuarioService")
public class UsuarioServiceImpl extends GenericServiceImpl<Usuario, Long> implements UsuarioService {

	private static final String USUARIO_JA_CADASTRADO = "Usuário já cadastrado";
	private static final String INCORRETO = "incorreto";
	private static final String E_MAIL_E_SENHA_INVALIDOS = "E-mail e senha inválidos";
	private static final String E_MAIL_NAO_ENCONTRADO = "E-mail não encontrado !";
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

	@Autowired
	private HttpRequest request;

	@Autowired
	private PlataformaRepository plataformaRepository;
	
	@Autowired
	private PlataformaTokenRepository plataformaTokenRepository;
	
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
			throw new AuthorizationException(Constantes.ACESSO_NEGADO_PRECISA_SE_LOGAR_PRIMEIRO);
		}
		usuario.setSenha(novaSenha.getSenha());
		passwordEncoder(usuario);
		salvar(usuario);
	}

	@Transactional
	public void recuperarSenha(EmailDTO dto) {
		Usuario usuario = encontrarPorEmail(dto.getEmail());

		if (usuario == null) {
			throw new ObjectNotFoundException(E_MAIL_NAO_ENCONTRADO);
		}

		String senha = gerador.senhaAleatoria();
		usuario.setSenha(senha);
		passwordEncoder(usuario);

		salvar(usuario);
		emailService.enviarEmail(EmailBuilderDTO.builder()
												.para(dto.getEmail())
												.assunto(NOVA_SENHA)
												.conteudo(String.join(DELIMITER, TEXTO_RECUPERAR_SENHA, senha))
												.build());
	}

	@Transactional
	public void vincularContasApps(UsuarioFakeAppsDTO dto) {
		Optional<Plataforma> plataforma = null;

		Usuario usuario = buscaUsuarioLogado();
		
		if(existePlataformaCadastrada(usuario, dto)) {
			throw new ObjectFoundException(USUARIO_JA_CADASTRADO);
		}

		String token = request.getTokenUsuarioFromFakeApps(dto);

		if (token.isEmpty() || token.equals(INCORRETO)) {
			throw new ObjectNotFoundException(E_MAIL_E_SENHA_INVALIDOS);
		}

		plataforma = plataformaRepository.findById(dto.getPlataforma());

		if (plataforma.isPresent()) {
			PlataformaToken plataformaToken = PlataformaToken.builder()
															.numeroPlataformaToken(token)
															.emailApp(dto.getEmail())
															.usua(usuario)
															.build();
			if (plataformaToken != null) {
				plataformaToken.addPlataforma(plataforma.get());
				usuario.addPlataformaToken(plataformaToken);
				salvar(usuario);;

			}

		}

	}

	private boolean existePlataformaCadastrada(Usuario usuario, UsuarioFakeAppsDTO dto) {
		boolean existe = false;
		
		if(!usuario.getToken().isEmpty()) {
			for (PlataformaToken token : usuario.getToken()) {
				if(token.getEmailApp().equals(dto.getEmail())) {
					if(token.getPlataforma().getId().equals(dto.getPlataforma())) {
						existe = true;
					}
				}
			}
		}
		return existe;
	}

	@Transactional(readOnly = true)
	public boolean existeEmailCadastrado(String email) {
		return repository.countByEmail(email) == 1;
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

	@Override
	public Usuario buscaUsuarioLogado() {
		User user = UserService.authenticated();

		Usuario usuario = (Usuario) pesquisarPorId(user.getId());

		if (usuario == null) {
			throw new AuthorizationException(Constantes.ACESSO_NEGADO_PRECISA_SE_LOGAR_PRIMEIRO);
		}

		return usuario;
	}

	@Override
	public Set<UsuarioFakeAppsDTO> getContasCadastradasApp() {
		Set<UsuarioFakeAppsDTO> dto = new HashSet<>();

		Usuario usuario = buscaUsuarioLogado();

		if (!usuario.getToken().isEmpty()) {
			for (PlataformaToken plataformaToken : usuario.getToken()) {
				UsuarioFakeAppsDTO app = UsuarioFakeAppsDTO.builder()
														   .email(plataformaToken.getEmailApp())
														   .plataforma(plataformaToken.getPlataforma().getId())
			  										       .build();
				if(app != null){										   
					dto.add(app);
				}
			}
		}

		return dto;
	}

	@Transactional
	public void sairContaApp(SairContaFakeAppsDTO dto) {
		Usuario usuario = buscaUsuarioLogado();
		
		if(!usuario.getToken().isEmpty()){
			for (PlataformaToken token : usuario.getToken()) {
				if(buscaContaFakeApps(dto, token)) {
					usuario.removePlataformaToken(token);
					salvar(usuario);
				}
			}
		}
	}

	private boolean buscaContaFakeApps(SairContaFakeAppsDTO dto, PlataformaToken token) {
		return token.getEmailApp().equals(dto.getEmail()) && token.getPlataforma().getId().equals(dto.getPlataforma());
	}

}
