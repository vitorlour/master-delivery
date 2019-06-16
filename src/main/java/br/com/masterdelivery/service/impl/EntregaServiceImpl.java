/**
 * 
 */
package br.com.masterdelivery.service.impl;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.masterdelivery.DataGenerator;
import br.com.masterdelivery.entity.Entrega;
import br.com.masterdelivery.entity.Usuario;
import br.com.masterdelivery.repository.EntregaRepository;
import br.com.masterdelivery.security.User;
import br.com.masterdelivery.security.service.UserService;
import br.com.masterdelivery.service.EntregaService;
import br.com.masterdelivery.service.UsuarioService;
import br.com.masterdelivery.service.exception.AuthorizationException;
import br.com.masterdelivery.utils.Constantes;

/**
 * @author vitorlour
 *
 */
@Service("entregaService")
public class EntregaServiceImpl extends GenericServiceImpl<Entrega, Long> implements EntregaService {
	
	@Autowired
	private EntregaRepository repository;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private DataGenerator data;
	
	
	//A fazer: Voltar um DTO
	@Transactional(readOnly=true)
	public Page<Entrega> entregaPorPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		User user = UserService.authenticated();
		if (user == null) {
			throw new AuthorizationException(Constantes.ACESSO_NEGADO_PRECISA_SE_LOGAR_PRIMEIRO);
		}
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		
		Usuario usuario = (Usuario) usuarioService.pesquisarPorId(user.getId());
		
		return repository.findByUsuario(usuario, pageRequest);
	}

}
