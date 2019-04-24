/**
 * 
 */
package br.com.masterdelivery.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.com.masterdelivery.entity.Usuario;
import br.com.masterdelivery.mapper.UsuarioMapper;
import br.com.masterdelivery.repository.UsuarioRepository;
import br.com.masterdelivery.security.User;

/**
 * @author vitorlour
 *
 */
public class UsuarioServiceImplTest {

	@InjectMocks
	private UsuarioServiceImpl usuarioService;

	@Spy
	private EmailService mailService;
	
	@Spy
	private BCryptPasswordEncoder pswEncoder;
	
	@Spy
	private UsuarioMapper mapper;
	
	@Mock
	private UsuarioRepository repository;
	
	private static Usuario usuario;
	
	private static User user;

	/**
	 */
	@BeforeEach
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	static {
		usuario = Usuario.builder()
						 .id(1L)
						 .email("vitorlour@hotmail.com")
						 .senha("123456")
						 .build();
		
		user = User.builder()
					 .id(1L)
					 .email("vitorlour@hotmail.com")
					 .senha("123456")
					 .build();
	}

	/**
	 */
	@Test
	public void testRealizarCadastro() {
		/*
		 * when(repository.countByEmail(usuario.getEmail())).thenReturn(0L);
		 * when(usuarioService.existeEmailCadastrado(usuario.getEmail())).thenReturn(
		 * false); doNothing().when(usuarioService).salvar(usuario);
		 */
	}

	/**
	 */
	@Test
	public void testAlterarSenha() {
		/*
		 * when(UserService.authenticated()).thenReturn(user);
		 * when(usuarioService.pesquisarPorId(user.getId())).thenReturn(usuario);
		 * doNothing().when(usuarioService).salvar(usuario);
		 */
	}

	/**
	 */
	@Test
	public void testRecuperarSenha() {

	}

	/**
	 */
	@Test
	public void testExisteEmailCadastrado() {

	}

	/**
	 */
	@Test
	public void testExisteUsuarioCadastrado() {

	}

}
