/**
 * 
 */
package br.com.masterdelivery.service.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.masterdelivery.entity.BaseObject;
import br.com.masterdelivery.repository.GenericRepository;
import br.com.masterdelivery.service.GenericService;

/**
 * @author vitorlour
 *
 */
@Service
@Transactional
public abstract class GenericServiceImpl<T extends BaseObject, ID extends Serializable> implements GenericService<T, ID>{
	
	@Autowired
	private GenericRepository<T, ID> repository;
	
	public GenericServiceImpl(GenericRepository<T, ID> repository) {
		this.repository = repository;
	}
	
	public GenericServiceImpl() {}
	
	@Transactional
	public void salvar(T entity) {
		repository.saveAndFlush(entity);
	}
	
	@Transactional
	public void excluir(T entity) {
		repository.delete(entity);
	}
	
	@Transactional
	public void excluirPorId(ID id) {
		repository.deleteById(id);
	}

	@Transactional(readOnly = true)
	public Optional<Optional<T>> pesquisarPorId(ID id) {
		return Optional.ofNullable(repository.findById(id));
	}

	@Transactional(readOnly = true)
	public List<T> pesquisarTodos() {
		return (List<T>) repository.findAll();
	}
	
	@Transactional(readOnly = true)
	public long count() {
		return repository.count();
	}
}
