/**
 * 
 */
package br.com.masterdelivery.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * @author vitorlour
 *
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class Constantes {
	
	public static final String AUTHORIZATION = "Authorization";
	
	public static final String BEARER = "Bearer ";
	
	public static final String ACCESS_CONTROL_EXPOSE_HEADERS = "access-control-expose-headers";
	
	public static final String APPLICATION_JSON = "application/json";
	
	public static final String ACESSO_NEGADO_PRECISA_SE_LOGAR_PRIMEIRO = "Acesso negado, precisa se logar primeiro !";
															
	public static final String END_POINT_FAKE_APP_CORRIDA = "http://localhost:8081/corrida";
	
	public static final String END_POINT_FAKE_APP_ENTREGADOR_TOKEN = "http://localhost:8081/entregador";
	
	public static final String GOOGLE_MAPS_KEY_API = "AIzaSyBV6wlNuYvIPd5QoX-MY4xqF10U5gI7qJg";
	
	public static final String VAZIO = "";

	
}
