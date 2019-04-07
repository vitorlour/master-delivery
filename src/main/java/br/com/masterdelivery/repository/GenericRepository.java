/**
 * 
 */
package br.com.masterdelivery.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import br.com.masterdelivery.entity.BaseObject;

/**
 * @author vitorlour
 * @param <T>
 *
 */
@NoRepositoryBean
public interface GenericRepository<T extends BaseObject, ID extends Serializable> extends JpaRepository<T, ID> {}
