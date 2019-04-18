/**
 * 
 */
package br.com.masterdelivery.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

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
@Table (name = "TB_USUARIO")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Usuario extends BaseObject implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -339397926917915685L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="native")
    @GenericGenerator(name="native", strategy="native")
	@Column(name="id")  
	private Long id;
	
    @Column(name = "email", nullable = false)
    @Email(message = "E-mail precisa ser valido !")
	private String email;
	
    @Column(name = "senha", nullable = false)
    @NotBlank(message = "Por favor preencher a senha !") 
    private String senha;
    
    @Column(name = "nr_token_access_usuario")
    private String tokenUsuario;
    
    @OneToMany(fetch = FetchType.EAGER)
	private Set<PlataformaToken> token;
    
    @OneToMany(fetch = FetchType.LAZY)
    private Set<Entrega> entrega;
    
    @Version
    private Long version;
    
}
