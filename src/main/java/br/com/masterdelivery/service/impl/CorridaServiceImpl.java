/**
 * 
 */
package br.com.masterdelivery.service.impl;

import java.io.IOException;
import java.time.Instant;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.maps.errors.ApiException;
import com.google.maps.model.LatLng;

import br.com.masterdelivery.DataGenerator;
import br.com.masterdelivery.dto.CoordenadasDTO;
import br.com.masterdelivery.dto.CorridaAceitaDTO;
import br.com.masterdelivery.dto.NumeroCorridasDTO;
import br.com.masterdelivery.entity.Corrida;
import br.com.masterdelivery.entity.Entrega;
import br.com.masterdelivery.entity.Plataforma;
import br.com.masterdelivery.entity.PlataformaToken;
import br.com.masterdelivery.entity.Usuario;
import br.com.masterdelivery.enu.StatusCorridaEnum;
import br.com.masterdelivery.http.HttpRequest;
import br.com.masterdelivery.repository.CorridaRepository;
import br.com.masterdelivery.repository.PlataformaRepository;
import br.com.masterdelivery.service.CorridaService;
import br.com.masterdelivery.service.EntregaService;
import br.com.masterdelivery.service.UsuarioService;
import br.com.masterdelivery.service.exception.AuthorizationException;
import br.com.masterdelivery.service.exception.LocationException;
import br.com.masterdelivery.service.exception.ObjectFoundException;
import br.com.masterdelivery.service.exception.ObjectNotFoundException;
import br.com.masterdelivery.utils.GoogleMapsServices;

/**
 * @author vitorlour
 *
 */
@Service("corridaService")
public class CorridaServiceImpl implements CorridaService {
	
	//private static final SimpleDateFormat formatter = new SimpleDateFormat("dd MM yyyy HH:mm:ss");

	private static final String USUARIO_COM_CORRIDA_EM_ANDAMENTO_POR_FAVOR_ADICIONAR_A_CONTA_NOVAMENTE = "Usuário com corrida em andamento, por favor adicionar a conta novamente !";

	private static final String VOCE_NAO_PODE_ACEITAR_DUAS_CORRIDAS = "Você não pode aceitar duas corridas ao mesmo tempo";

	private static final String VOCE_NAO_CHEGOU_AO_LOCAL = "Você não chegou ao local !";

	private static final long CEM_METROS = 100;

	private static final String A_CORRIDA_PASSOU_DESSE_STATUS = "A corrida passou desse status";

	private static final String USUARIO_NAO_TEM_CORRIDA_NESSE_MOMENTO = "Usuário não tem corrida nesse momento";

	private static final String OUTRO_USUARIO_JA_ACEITOU_ESSA_CORRIDA = "Outro usuário já aceitou essa corrida";

	private static final String CORRIDA_NAO_EXISTE = "Corrida não existe";

	private static final long QUINZE_MINUTOS = 900;
	
	private static final long ZERO = 0;

	@Autowired
	private CorridaRepository repository;

	@Autowired
	private HttpRequest request;

	@Autowired
	private GoogleMapsServices maps;

	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private EntregaService entregaService;
	
	@Autowired
	private PlataformaRepository plataformaRepository;
	
	@Autowired
	private DataGenerator generator;
	
	// TODO:precisa fazer corrida por cidade/zona para melhorar a eficiência, adicionar se existe corrida para o usuário na primeira lógica
	@Transactional
	public Set<Corrida> getCorridaPorLocalizacao(CoordenadasDTO dto) {
		Set<Corrida> lstCorrida = null;

		LatLng origin = new LatLng(dto.getLatitude(), dto.getLongitude());

		lstCorrida = buscarCorridaPorStatusCorrida(StatusCorridaEnum.PRONTO_PARA_COLETA.status());

		if (!lstCorrida.isEmpty()) {
			for (Corrida corrida : lstCorrida) {
				try {
					if (getDuration(origin, corrida)) {
						lstCorrida.remove(corrida);
					}
				} catch (ApiException | InterruptedException | IOException e) {
					e.getMessage();
				}
			}
			lstCorrida = existeVinculoConta(lstCorrida);
		}

		return lstCorrida;
	}

	// TODO: melhorar essa lógica abaixo
	private Set<Corrida> existeVinculoConta(Set<Corrida> lstCorrida) {
		Set<Corrida> existeLstCorrida = new HashSet<>();

		if (!lstCorrida.isEmpty()) {
			Usuario usuario = usuarioService.buscaUsuarioLogado();

			if (usuario != null) {
				if (!usuario.getToken().isEmpty()) {
					for (Corrida corrida : lstCorrida) {
						for (PlataformaToken token : usuario.getToken()) {
							if (verificaCorridaComPlataformaCadastrada(token.getPlataforma(), corrida) && usuario.getCorrida() == null) {
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
	
	@Transactional(readOnly = true)
	public long countByTokenCorrida(Long status) {
		return repository.countByStatusCorrida(status);
	}

	private boolean getDuration(LatLng origin, Corrida corrida) throws ApiException, InterruptedException, IOException {
		long duracaoCorrida = maps.getDuration(origin, corrida.getEndCliente());
		
		if(duracaoCorrida == ZERO) {
			return false;
		}
		
		return duracaoCorrida > QUINZE_MINUTOS;
	}
	
	@Transactional
	public void corridaAceita(CorridaAceitaDTO dto) {
		Usuario usuario = usuarioService.buscaUsuarioLogado();
		
		Corrida corrida = buscarCorridaPorTokenCorrida(dto.getTokenCorrida());
		
		if(corrida == null) {
			throw new ObjectNotFoundException(CORRIDA_NAO_EXISTE);
		}
		
		if(corrida.getUsuario() != null) {
			if(!usuarioCorridaIgualUsuarioLogado(usuario, corrida)) {
				throw new ObjectFoundException(OUTRO_USUARIO_JA_ACEITOU_ESSA_CORRIDA);
			}
		}else if(usuario.getCorrida() == null) {
			corrida.setUsuario(usuario);
			repository.saveAndFlush(corrida);
		}else {
			throw new ObjectFoundException(VOCE_NAO_PODE_ACEITAR_DUAS_CORRIDAS);
		}
	}

	private boolean usuarioCorridaIgualUsuarioLogado(Usuario usuario, Corrida corrida) {
		return corrida.getUsuario().getId().equals(usuario.getId());
	}
	
	@Transactional(readOnly = true)
	public Set<Corrida> buscarCorridaPorStatusCorrida(Long status){
		return repository.findByStatusCorrida(status);
	}
	
	@Transactional(readOnly = true)
	public Corrida buscarCorridaPorTokenCorrida(String tokenCorrida){
		return repository.findByTokenCorrida(tokenCorrida);
	}

	@Transactional
	public void pedidoColetado(CoordenadasDTO dto) {
		Usuario usuario = usuarioService.buscaUsuarioLogado();
		
		Corrida corrida = usuario.getCorrida();
		
		if(corrida == null) {
			throw new ObjectNotFoundException(USUARIO_NAO_TEM_CORRIDA_NESSE_MOMENTO);
		}
		
		if(!corrida.getStatusCorrida().equals(StatusCorridaEnum.PRONTO_PARA_COLETA.status())) {
			throw new ObjectNotFoundException(A_CORRIDA_PASSOU_DESSE_STATUS);
		}
		
		LatLng latLng = new LatLng(dto.getLatitude(), dto.getLongitude());
		
		try {
			if(maps.getDistance(latLng, corrida.getEndEstabelecimento()) > CEM_METROS) {
				throw new LocationException(VOCE_NAO_CHEGOU_AO_LOCAL);
			}
		} catch (ApiException e) {
		} catch (InterruptedException e) {
		} catch (IOException e) {
		}
		
		corrida.setStatusCorrida(StatusCorridaEnum.COLETADO_A_CAMINHO_DA_ENTREGA.status());
		repository.saveAndFlush(corrida);
	}
	
	
	public void entregaPedidoEfetuada(CoordenadasDTO dto) {
		Optional<Plataforma> plataforma = null;
		
		Usuario usuario = usuarioService.buscaUsuarioLogado();
		
		Corrida corrida = usuario.getCorrida();
		
		if(corrida == null) {
			throw new ObjectNotFoundException(USUARIO_NAO_TEM_CORRIDA_NESSE_MOMENTO);
		}
		
		if(!corrida.getStatusCorrida().equals(StatusCorridaEnum.COLETADO_A_CAMINHO_DA_ENTREGA.status())) {
			throw new ObjectNotFoundException(A_CORRIDA_PASSOU_DESSE_STATUS);
		}
		
		LatLng latLng = new LatLng(dto.getLatitude(), dto.getLongitude());
		
		try {
			if(maps.getDistance(latLng, corrida.getEndCliente()) > CEM_METROS) {
				throw new LocationException(VOCE_NAO_CHEGOU_AO_LOCAL);
			}
		} catch (ApiException e) {
		} catch (InterruptedException e) {
		} catch (IOException e) {
		}
		
		plataforma = plataformaRepository.findById(corrida.getPlataforma());

		
		Entrega entrega = Entrega.builder()
							     .nomeEstabelecimento(corrida.getNomeEstabelecimento())
							     .dataEntrega(Date.from(Instant.now()))
							     .pedidoId(corrida.getId())
							     .plataforma(plataforma.get())
							     .tokenCorrida(corrida.getTokenCorrida())
							     .valorEntrega(corrida.getValorEntrega())
							     .usuario(usuario)
							     .build();
		
		entregaService.salvar(entrega);
		
		if(request.corridaConcluida(corrida.getId())) {
			try {
				usuario.setCorrida(null);
				repository.deleteById(corrida.getId());
			} catch (Exception e) {
				e.getMessage();
			}
				
			
		}
		
	}

	public Corrida getCorridaAndamento() {
		Usuario usuario = usuarioService.buscaUsuarioLogado();
		
		Corrida corrida = usuario.getCorrida();
		
		if(corrida == null) {
			throw new ObjectNotFoundException(USUARIO_NAO_TEM_CORRIDA_NESSE_MOMENTO);
		}
		int contadorPlataforma = 0;
		
		for (PlataformaToken token : usuario.getToken()) {
			if(token.getPlataforma().getId().equals(corrida.getPlataforma())) {
				contadorPlataforma++;
			}
		}
		
		if(contadorPlataforma == 0) {
			throw new AuthorizationException(USUARIO_COM_CORRIDA_EM_ANDAMENTO_POR_FAVOR_ADICIONAR_A_CONTA_NOVAMENTE);
		}
		
		return corrida;
	}

	@Transactional
	public NumeroCorridasDTO getNumeroCorridasDashBoard() {
		return NumeroCorridasDTO.builder()
								.prontoParaColeta(countByTokenCorrida(StatusCorridaEnum.PRONTO_PARA_COLETA.status()))
								.coletaCaminhoEntrega(countByTokenCorrida(StatusCorridaEnum.COLETADO_A_CAMINHO_DA_ENTREGA.status()))
								.entregue(countByTokenCorrida(StatusCorridaEnum.ENTREGUE.status()))
								.problemaCaminhoDaEntrega(countByTokenCorrida(StatusCorridaEnum.PROBLEMA_A_CAMINHO_DA_ENTREGA.status()))
								.problemaColeta(countByTokenCorrida(StatusCorridaEnum.PROBLEMA_COLETA.status()))
								.problemaComUsuario(countByTokenCorrida(StatusCorridaEnum.PROBLEMA_COM_O_USUARIO.status()))
								.problemaEntrega(countByTokenCorrida(StatusCorridaEnum.PROBLEMA_NA_ENTREGA.status()))
								.build();
		
	}

}
