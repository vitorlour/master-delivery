/**
 * 
 */
package br.com.masterdelivery.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.masterdelivery.entity.Entrega;
import br.com.masterdelivery.service.EntregaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * @author vitorlour
 *
 */
@Api(value = "Entrega")
@ApiResponses(value = { @ApiResponse(code = 200, message = "Estas requisição foi bem sucedida."),
		@ApiResponse(code = 201, message = "A requisição foi bem sucessida e um novo recurso foi criado como resultado."),
		@ApiResponse(code = 202, message = "A requisição foi recebida mas nenhuma ação foi tomada sobre ela."),
		@ApiResponse(code = 204, message = "Não há conteúdo para enviar para esta solicitação"),
		@ApiResponse(code = 400, message = "Essa resposta significa que o servidor não entendeu a requisição pois está com uma sintaxe inválida."),
		@ApiResponse(code = 401, message = "Você deve se autenticar para obter a resposta solicitada."),
		@ApiResponse(code = 403, message = "Não tem direito de acesso ao conteúdo"),
		@ApiResponse(code = 404, message = "O servidor não pode encontrar o recurso solicitado.") })
@RestController
@RequestMapping(value = "/entrega/")
public class EntregaRestController {
	
	@Autowired
	private EntregaService service;	
	
	@ApiOperation(value = "Retorna as entregas por página, valor default por página é 24", response = ResponseEntity.class)
	@GetMapping(value = "pagina/", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Page<Entrega>> findPage(
			@RequestParam(value="page", defaultValue="0") Integer page, 
			@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage, 
			@RequestParam(value="orderBy", defaultValue="id") String orderBy, 
			@RequestParam(value="direction", defaultValue="DESC") String direction) {
		Page<Entrega> dto = service.entregaPorPage(page, linesPerPage, orderBy, direction);
		return ResponseEntity.ok().body(dto);
	}
}
