/**
 * 
 */
package br.com.masterdelivery.controller;

import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.masterdelivery.dto.CoordenadasDTO;
import br.com.masterdelivery.dto.CorridaAceitaDTO;
import br.com.masterdelivery.dto.NumeroCorridasDTO;
import br.com.masterdelivery.entity.Corrida;
import br.com.masterdelivery.service.CorridaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * @author vitorlour
 *
 */
@Api(value = "Corrida")
@ApiResponses(value = { @ApiResponse(code = 200, message = "Estas requisição foi bem sucedida."),
		@ApiResponse(code = 201, message = "A requisição foi bem sucessida e um novo recurso foi criado como resultado."),
		@ApiResponse(code = 202, message = "A requisição foi recebida mas nenhuma ação foi tomada sobre ela."),
		@ApiResponse(code = 204, message = "Não há conteúdo para enviar para esta solicitação"),
		@ApiResponse(code = 302, message = "Outro usuário já aceitou essa corrida."),
		@ApiResponse(code = 400, message = "Essa resposta significa que o servidor não entendeu a requisição pois está com uma sintaxe inválida."),
		@ApiResponse(code = 401, message = "Você deve se autenticar para obter a resposta solicitada."),
		@ApiResponse(code = 403, message = "Não tem direito de acesso ao conteúdo"),
		@ApiResponse(code = 418, message = "Location error"),
		@ApiResponse(code = 404, message = "O servidor não pode encontrar o recurso solicitado.") })
@RestController
@RequestMapping(value = "/corrida/")
@Validated
public class CorridaRestController {
	
	@Autowired
	private CorridaService service;
	
	@ApiOperation(value = "Realiza busca de corrida de acordo com a localidade entre o usuário e estabelecimento", response = ResponseEntity.class)
	@PostMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Set<Corrida>> getCorridaPorLocalizacao(@Valid @RequestBody CoordenadasDTO dto) {
		Set<Corrida> corrida = service.getCorridaPorLocalizacao(dto);
		return ResponseEntity.ok().body(corrida);
	}
	
	@ApiOperation(value = "Usuário aceita a corrida", response = ResponseEntity.class)
	@PostMapping(value = "aceitar", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Void> corridaAceita(@Valid @RequestBody CorridaAceitaDTO dto) {
		service.corridaAceita(dto);
		return ResponseEntity.ok().build();
	}
	
	@ApiOperation(value = "Usuário coleta o pedido", response = ResponseEntity.class)
	@PostMapping(value = "pedidocoletado", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Void> pedidoColetado(@Valid @RequestBody CoordenadasDTO dto) {
		service.pedidoColetado(dto);
		return ResponseEntity.ok().build();
	}
	
	@ApiOperation(value = "Usuário entrega o pedido", response = ResponseEntity.class)
	@PostMapping(value = "entregapedidoefetuada", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Void> entregaPedidoEfetuada(@Valid @RequestBody CoordenadasDTO dto) {
		service.entregaPedidoEfetuada(dto);
		return ResponseEntity.ok().build();
	}
	
	@ApiOperation(value = "Obter atualização da corrida em andamento", response = ResponseEntity.class)
	@GetMapping(value = "corridaemandamento", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Corrida> getCorridaAndamento() {
		Corrida corrida = service.getCorridaAndamento();
		return ResponseEntity.ok().body(corrida);
	}
	
	@ApiOperation(value = "Obtem numero de corridas com todos os status para o dashboard", response = ResponseEntity.class)
	@GetMapping(value = "corridasnumerodashboard", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<NumeroCorridasDTO> getNumeroCorridasDashBoard() {
		NumeroCorridasDTO dto = service.getNumeroCorridasDashBoard();
		return ResponseEntity.ok().body(dto);
	}
}
