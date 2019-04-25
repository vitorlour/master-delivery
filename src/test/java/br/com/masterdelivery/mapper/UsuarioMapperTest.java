/**
 * 
 */
package br.com.masterdelivery.mapper;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import br.com.masterdelivery.dto.UsuarioDTO;
import br.com.masterdelivery.entity.Usuario;

/**
 * @author vitorlour
 *
 */
public class UsuarioMapperTest {
	
	private static final String TESTE_POSITIVO_SENHA = "Teste Positivo: Verificar se a senha é igual";

	private static final String TESTE_POSITIVO_EMAIL = "Teste Positivo: Verificar se o email é igual";

	@InjectMocks
	private UsuarioMapper mapper;
	
	private UsuarioDTO dto;
	
	private Usuario usuario;
	
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		
		dto = new UsuarioDTO("masterdelivery@gmail.com", "123456");
	}
	
	/**
	 */
	@Test
	public void testConfigureMapperFactory() {
		 usuario = mapper.map(dto ,Usuario.class);
		 
		 assertThat(TESTE_POSITIVO_EMAIL, usuario.getEmail(), is(dto.getEmail()));
		 assertThat(TESTE_POSITIVO_SENHA, usuario.getSenha(), is(dto.getSenha()));

	}

}
