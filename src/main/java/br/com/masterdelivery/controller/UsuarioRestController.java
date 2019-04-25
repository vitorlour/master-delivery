/**
 * 
 */
package br.com.masterdelivery.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.masterdelivery.dto.EmailDTO;
import br.com.masterdelivery.dto.SenhaDTO;
import br.com.masterdelivery.dto.UsuarioDTO;
import br.com.masterdelivery.service.UsuarioService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * @author vitorlour
 *
 */
@Api(value = "Usuário")
@ApiResponses(value = { @ApiResponse(code = 200, message = "Estas requisição foi bem sucedida."),
		@ApiResponse(code = 201, message = "A requisição foi bem sucessida e um novo recurso foi criado como resultado."),
		@ApiResponse(code = 202, message = "A requisição foi recebida mas nenhuma ação foi tomada sobre ela."),
		@ApiResponse(code = 204, message = "Não há conteúdo para enviar para esta solicitação"),
		@ApiResponse(code = 400, message = "Essa resposta significa que o servidor não entendeu a requisição pois está com uma sintaxe inválida."),
		@ApiResponse(code = 401, message = "Você deve se autenticar para obter a resposta solicitada."),
		@ApiResponse(code = 403, message = "Não tem direito de acesso ao conteúdo"),
		@ApiResponse(code = 404, message = "O servidor não pode encontrar o recurso solicitado.") })
@RestController
@RequestMapping(value = "/usuario/")
@Validated
public class UsuarioRestController {

	@Autowired
	private UsuarioService service;

	@ApiOperation(value = "Realiza o cadastro do usuário", response = ResponseEntity.class)
	@PostMapping(value = "realizarcadastro", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Void> realizarCadastro(@Valid @RequestBody UsuarioDTO usuario) {
		service.realizarCadastro(usuario);
		return ResponseEntity.ok().build();
	}

	@ApiOperation(value = "Altera a senha do usuário", response = ResponseEntity.class)
	@PutMapping(value = "alterarsenha", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Void> alterarSenha(@Valid @RequestBody SenhaDTO novaSenha) {
		service.alterarSenha(novaSenha);
		return ResponseEntity.ok().build();
	}

	@ApiOperation(value = "Recupera senha do usuário", response = ResponseEntity.class)
	@PostMapping(value = "recuperarsenha", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Void> recuperarSenha(@Valid @RequestBody EmailDTO email) {
		service.recuperarSenha(email);
		return ResponseEntity.ok().build();
	}
}
