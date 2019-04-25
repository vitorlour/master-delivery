/**
 * 
 */
package br.com.masterdelivery.service.impl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import br.com.masterdelivery.dto.UsuarioDTO;

/**
 * @author vitorlour
 *
 */
@SpringBootTest
@TestPropertySource("classpath:application-test.properties")
public class UsuarioServiceImplTest {

	@Autowired
	private UsuarioServiceImpl usuarioService;

	private static UsuarioDTO dto;
	
	static {
		dto = UsuarioDTO.builder()
						 .email("masterdelivery@hotmail.com")
						 .senha("123456")
						 .build();
	}

	/**
	 */
	@Test
	public void testRealizarCadastro() {
		usuarioService.realizarCadastro(dto);
		 
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
	public void testExisteEmailCadastrado() {

	}

	/**
	 */
	@Test
	public void testExisteUsuarioCadastrado() {

	}

}
