/**
 * 
 */
package br.com.masterdelivery.entity;

import java.io.Serializable;

import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import lombok.Getter;
import lombok.Setter;

/**
 * @author vitorlour
 * @Class 
 */
@Getter
@Setter
@Inheritance( strategy = InheritanceType.TABLE_PER_CLASS)
public class BaseObject implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7599335562550237657L;
}
