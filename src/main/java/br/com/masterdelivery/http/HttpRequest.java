/**
 * 
 */
package br.com.masterdelivery.http;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import br.com.masterdelivery.dto.UsuarioFakeAppsDTO;
import br.com.masterdelivery.entity.Corrida;
import br.com.masterdelivery.repository.CorridaRepository;
import br.com.masterdelivery.utils.Constantes;

/**
 * @author vitorlour
 *
 */
@Component
public class HttpRequest {

	private static final String APPLICATION_JSON_CHARSET_UTF_8 = "application/json;charset=UTF-8";

	private static final String CONTENT_TYPE = "Content-Type";

	public Corrida[] getCorridaFromFakeApps() {
		Corrida[] arrayCorrida = null;
		HttpResponse response = null;

		try {
			response = get(Constantes.END_POINT_FAKE_APP_CORRIDA);

			if (response.getStatusLine().getStatusCode() == 200) {
				String jsonString = EntityUtils.toString(response.getEntity()).trim();

				arrayCorrida = new Gson().fromJson(jsonString, Corrida[].class);
			}

		} catch (Exception e) {
			e.getMessage();
		}

		return arrayCorrida;
	}

	public String getTokenUsuarioFromFakeApps(UsuarioFakeAppsDTO dto) {
		HttpResponse response = null;
		String token = Constantes.VAZIO;

		try {
			response = post(Constantes.END_POINT_FAKE_APP_ENTREGADOR_TOKEN, dto);

			if (response.getStatusLine().getStatusCode() == 200) {
				token = EntityUtils.toString(response.getEntity()).trim();
			}

		} catch (Exception e) {
			e.getMessage();
		}

		return token;
	}

	public HttpResponse get(String url) throws URISyntaxException, IOException {
		HttpClient httpclient = new DefaultHttpClient();
		URIBuilder builder = new URIBuilder(url);
		URI uri = builder.build();
		HttpGet request = new HttpGet(uri);

		return httpclient.execute(request);

	}

	public HttpResponse post(String url, Object obj) throws URISyntaxException, IOException {
		HttpClient httpclient = new DefaultHttpClient();
		URIBuilder builder = new URIBuilder(url);
		URI uri = builder.build();
		HttpPost request = new HttpPost(uri);

		request.setHeader(CONTENT_TYPE, APPLICATION_JSON_CHARSET_UTF_8);
		String jsonInString = new Gson().toJson(obj);

		StringEntity reqEntity = new StringEntity(jsonInString);

		request.setEntity(reqEntity);

		return httpclient.execute(request);

	}

}
