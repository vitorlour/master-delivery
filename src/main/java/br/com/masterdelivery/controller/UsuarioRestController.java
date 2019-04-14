/**
 * 
 */
package br.com.masterdelivery.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.masterdelivery.entity.Usuario;
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
@ApiResponses(value = { @ApiResponse(code = 200, message = "Requisição foi bem sucedida"),
						@ApiResponse(code = 401, message = "Você não está autorizado"),
						@ApiResponse(code = 403, message = "Acesso está proibido"),
						@ApiResponse(code = 404, message = "Não foi encontrado") })
@RestController
@RequestMapping(value = "/usuario/")
public class UsuarioRestController {

	@Autowired
	private UsuarioService service;

	@ApiOperation(value = "Realiza cadastro do usuário", response = ResponseEntity.class)
	@PostMapping(value = "realizarcadastro", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> realizarCadastro(@RequestBody @Valid Usuario usuario) {

		try {
			if (service.realizarCadastro(usuario) == null) {
				return ResponseEntity.ok().body("OK");
			} else {
				return ResponseEntity.badRequest().body("ERRO");
			}

		} catch (Exception e) {
		}

		return ResponseEntity.badRequest().body("ERRO");
	}

	@ApiOperation(value = "Altera a senha do usuário", response = ResponseEntity.class)
	@PostMapping(value = "alterarsenha", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> alterarSenha(@RequestBody @Valid Usuario usuario, String novaSenha) {

		try {
			if (service.alterarSenha(usuario, novaSenha) == null) {
				return ResponseEntity.ok().body("OK");
			} else {
				return ResponseEntity.badRequest().body("ERRO");
			}

		} catch (Exception e) {
		}
		return ResponseEntity.ok().body("ERRO");
	}

	@ApiOperation(value = "Recupera senha do usuário", response = ResponseEntity.class)
	@PostMapping(value = "recuperarsenha", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> recuperarSenha(@RequestBody String email) {

		try {
			if (service.recuperarSenha(email) == null) {
				return ResponseEntity.ok().body("OK");
			} else {
				return ResponseEntity.badRequest().body("ERRO");
			}

		} catch (Exception e) {
		}
		return ResponseEntity.ok().body("ERRO");
	}
	
	@ApiOperation(value = "Exclui o cadastro do usuario", response = ResponseEntity.class)
	@DeleteMapping(value = "excluircadastro", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> excluirCadastro(@RequestBody Usuario usuario) {

		try {
			if (service.excluirCadastro(usuario) == null) {
				return ResponseEntity.ok().body("OK");
			} else {
				return ResponseEntity.badRequest().body("ERRO");
			}

		} catch (Exception e) {
		}
		return ResponseEntity.ok().body("ERRO");
	}

}
