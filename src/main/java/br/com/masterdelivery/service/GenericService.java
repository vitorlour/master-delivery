/**
 * 
 */
package br.com.masterdelivery.service;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import br.com.masterdelivery.entity.BaseObject;

/**
 * @author vitorlour
 *
 */
public interface GenericService<T extends BaseObject, ID extends Serializable> {

	void salvar(T entity);

	void excluir(T entity);

	void excluirPorId(ID id);

	Optional<Optional<T>> pesquisarPorId(ID id);

	List<T> pesquisarTodos();

	long count();

}
