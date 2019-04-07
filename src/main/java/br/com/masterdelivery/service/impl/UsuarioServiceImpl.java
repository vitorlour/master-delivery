/**
 * 
 */
package br.com.masterdelivery.service.impl;


import org.springframework.stereotype.Service;

import br.com.masterdelivery.entity.Usuario;
import br.com.masterdelivery.service.UsuarioService;

/**
 * @author vitorlour
 *
 */
@Service
public class UsuarioServiceImpl extends GenericServiceImpl<Usuario, Long> implements UsuarioService {
	/*
	 * 
	 * @Autowired private UsuarioRepository repository;
	 * 
	 * public void salvar(Usuario entity) { repository.saveAndFlush(entity); }
	 * 
	 * public void excluir(Usuario entity) { repository.delete(entity); }
	 * 
	 * @Transactional(readOnly = true) public Optional<Optional<Usuario>>
	 * pesquisarPorId(Long id) { return
	 * Optional.ofNullable(repository.findById(id)); }
	 * 
	 * 
	 * @Transactional(readOnly = true) public List<Usuario> pesquisarTodos() {
	 * return repository.findAll(); }
	 */}
