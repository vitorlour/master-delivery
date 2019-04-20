/**
 * 
 */
package br.com.masterdelivery.service.impl;

import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import br.com.masterdelivery.entity.Usuario;

/**
 * @author vitorlour
 *
 */
public class UsuarioServiceImplTest {

	@InjectMocks
	private UsuarioServiceImpl usuarioService;

	@Spy
	private EmailService mailService;

	private static Usuario usuario;

	/**
	 */
	@BeforeEach
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	static {
		usuario = new Usuario();

		usuario.setEmail("vitorlour@hotmail.com");
		usuario.setSenha("123456");
		//usuario.setTokenUsuario("8bca56e1-3f3b-4751-9093-5fca816ecbaa");
	}

	/**
	 */
	@Test
	public void testRealizarCadastro() {
		//when(usuarioService.existeEmailCadastrado(usuario.getEmail())).thenReturn(false);
		/* assertNull("", usuarioService.realizarCadastro(usuario)); */
	}

	/**
	 */
	@Test
	public void testAlterarSenha() {

	}

	/**
	 */
	@Test
	public void testRecuperarSenha() {

	}

	/**
	 */
	@Test
	public void testExcluirCadastro() {

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
