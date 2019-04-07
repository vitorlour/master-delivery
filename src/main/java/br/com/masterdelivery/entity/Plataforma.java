/**
 * 
 */
package br.com.masterdelivery.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

import org.hibernate.annotations.GenericGenerator;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author vitorlour
 *
 */
@Entity
@Table(name = "TB_PLATAFORMA")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Plataforma extends BaseObject implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5393655708814246301L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	@Column(name = "id")
	private Long id;

	@Column(name = "nm_plataforma")
	private String nome;

	@Column(name = "cnpj_plataforma")
	private String cnplataforma;
	
	@Version
    private Long version;

}
