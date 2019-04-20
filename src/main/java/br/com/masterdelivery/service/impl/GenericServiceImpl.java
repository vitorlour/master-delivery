/**
 * 
 */
package br.com.masterdelivery.service.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.masterdelivery.entity.BaseObject;
import br.com.masterdelivery.repository.GenericRepository;
import br.com.masterdelivery.service.GenericService;
import br.com.masterdelivery.service.exception.DataIntegrityException;
import br.com.masterdelivery.service.exception.ObjectNotFoundException;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * @author vitorlour
 *
 */
@Service
@Transactional
@NoArgsConstructor
@AllArgsConstructor
public abstract class GenericServiceImpl<T extends BaseObject, ID extends Serializable>
		implements GenericService<T, ID> {

	private static final String ERRO_NÃO_FOI_POSSÍVEL_EXCLUIR_POR_ID = "ERRO ! Não foi possível excluir por ID :";

	private static final String ERRO_NÃO_FOI_POSSÍVEL_EXCLUIR = "ERRO ! Não foi possível excluir";

	private static final String ERRO_NÃO_FOI_POSSIVEL_SALVAR = "ERRO ! Não foi possível salvar";

	private static final String NÃO_FOI_ENCONTRADO_ID = "Não foi encontrado ! ID: ";

	@Autowired
	private GenericRepository<T, ID> repository;

	@Transactional
	public void salvar(T entity) {
		try {
			if (entity != null) {
				repository.saveAndFlush(entity);
			}
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException(ERRO_NÃO_FOI_POSSIVEL_SALVAR);
		}
	}

	@Transactional
	public void excluir(T entity) {
		try {
			if (entity != null) {
				repository.delete(entity);
			}
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException(ERRO_NÃO_FOI_POSSÍVEL_EXCLUIR);
		}
	}

	@Transactional
	public void excluirPorId(ID id) {
		try {
			if (id != null) {
				repository.deleteById(id);
			}
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException(ERRO_NÃO_FOI_POSSÍVEL_EXCLUIR_POR_ID + id);
		}
	}

	@Transactional(readOnly = true)
	public Object pesquisarPorId(ID id) {
		Optional<T> obj = null;
		if (id != null) {
			obj = repository.findById(id);
		}
		return obj.orElseThrow(() -> new ObjectNotFoundException(NÃO_FOI_ENCONTRADO_ID + id));
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
