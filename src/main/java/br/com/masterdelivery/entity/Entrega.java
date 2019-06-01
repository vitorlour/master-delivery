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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

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
@ToString
public class Entrega extends BaseObject implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6952821309368861462L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="native")
    @GenericGenerator(name="native", strategy="native")
	@Column(name="id")  
	@JsonIgnore
	private Long id;

    @Column(name = "vlr_entrega")
	private Double valorEntrega;
	
    @Column(name = "vlr_gorjeta")
	private Double valorGorjeta;
	
    @Column(name = "hr_duracao_corrida")
	private String duracaoCorrida;
	
    @Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
    @Column(name = "dt_entrega")
	private Date dataEntrega;
    
    @ManyToOne(fetch = FetchType.LAZY)
    private Plataforma plataforma;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="usuario_id")
    @JsonIgnore
    private Usuario usuario;
    
    @Version
    @JsonIgnore
    private Long version;
    
}
