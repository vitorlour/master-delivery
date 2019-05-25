/**
 * 
 */
package br.com.masterdelivery.service.impl;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.maps.errors.ApiException;
import com.google.maps.model.LatLng;

import br.com.masterdelivery.dto.CoordenadasDTO;
import br.com.masterdelivery.entity.Corrida;
import br.com.masterdelivery.entity.Plataforma;
import br.com.masterdelivery.entity.PlataformaToken;
import br.com.masterdelivery.entity.Usuario;
import br.com.masterdelivery.enu.StatusCorridaEnum;
import br.com.masterdelivery.http.HttpRequest;
import br.com.masterdelivery.repository.CorridaRepository;
import br.com.masterdelivery.repository.PlataformaRepository;
import br.com.masterdelivery.repository.PlataformaTokenRepository;
import br.com.masterdelivery.service.CorridaService;
import br.com.masterdelivery.service.UsuarioService;
import br.com.masterdelivery.utils.GoogleMapsServices;

/**
 * @author vitorlour
 *
 */
@Service("corridaService")
public class CorridaServiceImpl implements CorridaService {

	private static final long QUINZE_MINUTOS = 900;

	@Autowired
	private CorridaRepository repository;

	@Autowired
	private HttpRequest request;

	@Autowired
	private GoogleMapsServices maps;

	@Autowired
	private UsuarioService usuarioService;

	// TODO:precisa fazer corrida por cidade/zona para melhorar a eficiência
	public Set<Corrida> getCorridaPorLocalizacao(CoordenadasDTO dto) {
		Set<Corrida> lstCorrida = null;

		LatLng origin = new LatLng(dto.getLatitude(), dto.getLongitude());

		lstCorrida = repository.findByStatusCorrida(StatusCorridaEnum.PRONTO_PARA_COLETA.status());

		if (!lstCorrida.isEmpty()) {
			for (Corrida corrida : lstCorrida) {
				try {
					if (getDuration(origin, corrida)) {
						lstCorrida.remove(corrida);
					}
				} catch (ApiException | InterruptedException | IOException e) {
				}
			}
			lstCorrida = existeVinculoDeConta(lstCorrida);
		}

		return lstCorrida;
	}

	// TODO: melhorar essa lógica abaixo
	private Set<Corrida> existeVinculoDeConta(Set<Corrida> lstCorrida) {
		Set<Corrida> existeLstCorrida = new HashSet<>();

		if (!lstCorrida.isEmpty()) {
			Usuario usuario = usuarioService.buscaUsuarioLogado();

			if (usuario != null) {
				if (!usuario.getToken().isEmpty()) {
					for (Corrida corrida : lstCorrida) {
						for (PlataformaToken token : usuario.getToken()) {
							if (verificaCorridaComPlataformaCadastrada(token.getPlataforma(), corrida)) {
								existeLstCorrida.add(corrida);
							}
						}
					}
				} else {
					lstCorrida.clear();
				}
			} else {
				lstCorrida.clear();
			}
		}
		
		return existeLstCorrida;
	}

	private boolean verificaCorridaComPlataformaCadastrada(Plataforma plataforma, Corrida corrida) {
		return corrida.getPlataforma().equals(plataforma.getId());
	}

	@Scheduled(fixedDelay = 10000)
	@Transactional
	public void getCorridaFromFakeAppsAndSave() {
		List<Corrida> lstCorrida = null;
		Corrida[] arrayCorrida = request.getCorridaFromFakeApps();

		if (arrayCorrida != null) {
			lstCorrida = Arrays.stream(arrayCorrida).collect(Collectors.toList());
			if (!lstCorrida.isEmpty()) {
				for (Corrida corrida : lstCorrida) {
					if (countByTokenCorrida(corrida)) {
						repository.save(corrida);
					}
				}
			}
		}
	}

	@Transactional(readOnly = true)
	public boolean countByTokenCorrida(Corrida corrida) {
		return repository.countByTokenCorrida(corrida.getTokenCorrida()) == 0;
	}

	private boolean getDuration(LatLng origin, Corrida corrida) throws ApiException, InterruptedException, IOException {
		return maps.getDuration(origin, corrida.getEndCliente()) > QUINZE_MINUTOS;
	}

}
