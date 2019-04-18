/**
 * 
 */
package br.com.masterdelivery.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;

import org.hibernate.annotations.GenericGenerator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author vitorlour
 *
 */
@Entity
@Table(name = "TB_PLATAFORMA_TOKEN")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlataformaToken extends BaseObject implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 701149125068016586L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	@Column(name = "id")
	private Long id;

	@Column(name = "nr_token_access_plataforma")
	private String plataformaToken;

	@Column(name = "ie_chamada_ativa")
	private Boolean chamadaAtiva;

	@ManyToOne(fetch = FetchType.LAZY)
	private Plataforma plataforma;

	@ManyToOne(fetch = FetchType.EAGER)
	private Usuario usuario;

	@Version
	private Long version;

}
