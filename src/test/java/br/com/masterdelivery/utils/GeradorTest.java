/**
 * 
 */
package br.com.masterdelivery.utils;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

/**
 * @author vitorlour
 *
 */
public class GeradorTest {
	
	private static final String TESTE_POSITIVO = "Teste Positivo: Verificar se o object de retorno não é nullo";
	
	@InjectMocks
	private Gerador gerador;
	
	/**
	 */
	@BeforeEach
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	/**
	 */
	@Test
	public void testSenhaAleatoria() {
		assertNotNull(TESTE_POSITIVO, gerador.senhaAleatoria());
	}

}
