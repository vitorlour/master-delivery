package br.com.masterdelivery.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * @author vitorlour
 *
 */
@Api(value = "Login")
@ApiResponses(value = { @ApiResponse(code = 200, message = "Requisição foi bem sucedida"),
						@ApiResponse(code = 401, message = "Você não está autorizado"),
						@ApiResponse(code = 403, message = "Acesso está proibido"),
						@ApiResponse(code = 404, message = "Não foi encontrado") })
@RestController
@RequestMapping(value = "/login/")
public class LoginUsuarioRestController {
}
