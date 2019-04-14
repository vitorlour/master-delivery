/**
 * 
 */
package br.com.masterdelivery.entity;


import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
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
@Table (name = "TB_ENTREGA")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Entrega extends BaseObject implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6952821309368861462L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="native")
    @GenericGenerator(name="native", strategy="native")
	@Column(name="id")  
	private Long id;

    @Column(name = "vl_entrega")
	private Double valorEntrega;
	
    @Column(name = "vl_gorjeta")
	private Double valorGorjeta;
	
    @Column(name = "hr_duracao_corrida")
	private Date duracaoCorrida;
	
    @Temporal(TemporalType.DATE)
    @Column(name = "dt_entrega")
	private Date dataEntrega;
    
    @OneToOne(fetch = FetchType.LAZY)
    private Plataforma plataforma;
    
    @ManyToOne(fetch = FetchType.EAGER)
    private Usuario usuario;
    
    @Version
    private Long version;
    
}
