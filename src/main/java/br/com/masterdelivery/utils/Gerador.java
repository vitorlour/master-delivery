/**
 * 
 */
package br.com.masterdelivery.utils;

import java.security.SecureRandom;
import java.util.Random;

import org.springframework.stereotype.Component;

/**
 * @author vitorlour
 *
 */
@Component
public class Gerador {

	private static final String VAZIO = "";

	private static final Random RANDOM = new SecureRandom();

	public static final int TAMANHO_DA_SENHA = 8;

	public static final String LETRAS = "abcdefghjkmnpqrstuvwxyzABCDEFGHJKMNPQRSTUVWXYZ23456789+@";

	public String senhaAleatoria() {

		String senha = VAZIO;
		for (int i = 0; i < TAMANHO_DA_SENHA; i++) {
			int index = (int) (RANDOM.nextDouble() * LETRAS.length());
			senha += LETRAS.substring(index, index + 1);
		}
		return senha;
	}

}
